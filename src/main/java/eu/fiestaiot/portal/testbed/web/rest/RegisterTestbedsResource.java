package eu.fiestaiot.portal.testbed.web.rest;

import com.codahale.metrics.annotation.Timed;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.service.ITestbedsRegisterService;
import eu.fiestaiot.portal.testbed.service.OpenAMSecurityHelper;
import eu.fiestaiot.portal.testbed.service.RegisterTestbedsService;
import eu.fiestaiot.portal.testbed.service.dto.ExternalServiceResponse;
import eu.fiestaiot.portal.testbed.service.dto.GetAllTestbedsByUserIDDTO;
import eu.fiestaiot.portal.testbed.service.dto.OntologyValidatorBody;
import eu.fiestaiot.portal.testbed.service.dto.OntologyValidatorResponse;
import eu.fiestaiot.portal.testbed.service.dto.RegisterTestbedsDTO;
import eu.fiestaiot.portal.testbed.web.rest.util.HeaderUtil;
import eu.fiestaiot.portal.testbed.web.rest.util.PaginationUtil;
import eu.fiestaiot.portal.testbed.web.rest.vm.ServiceResponse;
import io.swagger.annotations.ApiParam;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RegisterTestbeds.
 */
@RestController
@RequestMapping("/api")
public class RegisterTestbedsResource {

    private final Logger log = LoggerFactory
            .getLogger(RegisterTestbedsResource.class);

    @Inject
    private RegisterTestbedsService registerTestbedsService;
    @Inject
    private ITestbedsRegisterService testbedsRegisterService;
    @Inject
    private OpenAMSecurityHelper openAMSecurityHelper;

    /**
     * POST /register-testbeds : Create a new registerTestbeds.
     *
     * @param registerTestbeds the registerTestbeds to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new registerTestbeds, or with status 400 (Bad Request) if the
     * registerTestbeds has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException
     * @throws JSONException
     */
    @PostMapping("/register-testbeds")
    @Timed
    public ResponseEntity<?> createRegisterTestbeds(
            @Valid @RequestBody RegisterTestbedsDTO registerTestbeds,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException {
        log.info("REST request to save RegisterTestbed : {}", registerTestbeds);

        String token = openAMSecurityHelper.getToken(request);
        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);

        if (userID.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "registerTestbed",
                                            "Register testbed fail with invalid userID"))
                    .body(new ServiceResponse("Register testbed fail with invalid userID"));

        }
        log.info("REST request with cookie userID : {}", userID);
        RegisterTestbeds testbed = new RegisterTestbeds();

        registerTestbeds.setUserID(userID);
        registerTestbeds.setRegisterID(registerTestbeds.getIri() + "#"
                + registerTestbeds.getName());

        if (!registerTestbeds.getAnnotatedObservation().isEmpty()
                && !registerTestbeds.getAnnotatedResourceDescription()
                        .isEmpty()
                && testbedsRegisterService.isAnnotatedResourceDescriptionValid(new OntologyValidatorBody(registerTestbeds.getAnnotatedResourceDescription(), "", registerTestbeds.getResourceDescriptionContentType()), token)
                && testbedsRegisterService.isAnnotatedObservationValueValid(new OntologyValidatorBody(registerTestbeds.getAnnotatedObservation(), "", registerTestbeds.getResourceObservationContentType()), token)) {
            ExternalServiceResponse response = testbedsRegisterService
                    .registerTestbed(registerTestbeds, token);
            if (response.getStatusCode() == 201) {

                testbed.setUserID(userID);
                testbed.setRegisterID(registerTestbeds.getIri());
                testbed.setResourceType(response.getResponseType());
                testbed.setIri(registerTestbeds.getIri());
                testbed.setLatitude(registerTestbeds.getLatitude());
                testbed.setLongitude(registerTestbeds.getLongitude());
                testbed.setName(registerTestbeds.getName());
                testbed.setPushApiKey(registerTestbeds.getPushApiKey());
                testbed.setGetApiKey(registerTestbeds.getGetApiKey());
                testbed.setAnnotatedObservation(registerTestbeds.getAnnotatedObservation());
                testbed.setAnnotatedResourceDescription(registerTestbeds.getAnnotatedResourceDescription());
                testbed.setGetLastObservationsURL(registerTestbeds.getGetLastObservationsURL());
                testbed.setGetObservationsURL(registerTestbeds.getGetObservationsURL());
                testbed.setPushLastObservationsURL(registerTestbeds.getPushLastObservationsURL());
                testbed.setPushObservationsURL(registerTestbeds.getPushObservationsURL());
                testbed.setResourceID(response.getResponseId());

                RegisterTestbeds result = registerTestbedsService
                        .save(testbed);

                return ResponseEntity
                        .created(
                                new URI("/api/register-testbeds/"
                                        + result.getId()))
                        .headers(
                                HeaderUtil.createEntityCreationAlert(
                                        "registerTestbed", result.getId()
                                                .toString())).body(result);
            } else {
                 log.info("REST request with cookie userID : {}", "Register testbed to IoT registry fail with statusCode:" + response.getStatusCode());
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .headers(
                                HeaderUtil.createEntityUpdateAlert(
                                        "registerTestbed",
                                        "Register testbed fail with statusCode:"
                                        + response.getStatusCode()))
                        
                        .body(new ServiceResponse("Register testbed to IoT registry fail with statusCode:" + response.getStatusCode()));
            }
        } else {
             log.info("Register testbed fail with invalid resource descrition or resource observation");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "registerTestbed",
                                            "Register testbed fail with invalid resource descrition or resource observation:"))
                    
                     .body(new ServiceResponse("Register testbed fail with invalid resource descrition or resource observation" ));
        }

    }

    @GetMapping("/register-testbeds")
    @Timed
    public ResponseEntity<List<RegisterTestbeds>> getAllRegisterTestbeds(
            @ApiParam Pageable pageable, HttpServletRequest request) throws URISyntaxException {
        log.info("REST request to get a page of getAllRegisterTestbeds");
        String token = openAMSecurityHelper.getToken(request);
        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);
        
        if (userID.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "getAllRegisterTestbeds",
                                            "getAllRegisterTestbeds fail with invalid userID"))
                    .body(null);

        }
        
        if (openAMSecurityHelper.isAdmin(userID, token)) {
            Page<RegisterTestbeds> page = registerTestbedsService.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
                    page, "/api/register-testbeds");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        } else {
            GetAllTestbedsByUserIDDTO dto = new GetAllTestbedsByUserIDDTO();
            dto.setUserID(userID);
            Page<RegisterTestbeds> page = registerTestbedsService.findByUserID(
                    pageable, dto);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
                    page, "/api/register-testbeds");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }

    }

    /**
     * GET /register-testbeds/:id : get the "id" registerTestbeds.
     *
     * @param id the id of the registerTestbeds to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * registerTestbeds, or with status 404 (Not Found)
     */
    @GetMapping("/register-testbeds/{id}")
    @Timed
    public ResponseEntity<RegisterTestbeds> getRegisterTestbeds(@PathVariable Long id, HttpServletRequest request) {
        log.info("REST request to get RegisterTestbeds : {}", id);
        RegisterTestbeds registerTestbeds = registerTestbedsService.findOne(id);
        return Optional.ofNullable(registerTestbeds)
                .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/register-testbeds/validateAnnotatedResource")
    @Timed
    public ResponseEntity<OntologyValidatorResponse> validateAnnotatedResource(
            @Valid @RequestBody OntologyValidatorBody body,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException {
        log.info("REST request to validateAnnotatedResource : {}", body);
        OntologyValidatorResponse result = new OntologyValidatorResponse();

        if (!body.getAnnotatedResourceDescription().isEmpty()) {
            Boolean rs = testbedsRegisterService
                    .isAnnotatedResourceDescriptionValid(
                            body, "");
            if (rs) {
                result.setMessage("Resource Description validation is valid!");
                result.setResult(rs);
            } else {
                result.setMessage("Resource Description validation is not valid!");
                result.setResult(false);
            }

            return new ResponseEntity<>(result, null, HttpStatus.OK);

        } else {
            result.setMessage("Resource Description validation is not valid!");
            result.setResult(false);
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }

    }

    @PostMapping("/register-testbeds/validateAnnotatedObseration")
    @Timed
    public ResponseEntity<OntologyValidatorResponse> validateAnnotatedObseration(
            @Valid @RequestBody OntologyValidatorBody ontologyValidatorBody,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException {
        log.info("REST request to validateAnnotatedObseration : {}", ontologyValidatorBody);
        OntologyValidatorResponse result = new OntologyValidatorResponse();

        if (!ontologyValidatorBody.getAnnotatedObservation().isEmpty()) {
            Boolean rs = testbedsRegisterService
                    .isAnnotatedObservationValueValid(ontologyValidatorBody, "");

            if (rs) {
                result.setMessage("Resource Obseration validation is valid!");
                result.setResult(rs);
            } else {
                result.setMessage("Resource Obseration validation is not valid!");
                result.setResult(false);
            }

            return new ResponseEntity<>(result, null, HttpStatus.OK);

        } else {
            result.setMessage("Resource Obseration validation is not valid!");
            result.setResult(false);
            return new ResponseEntity<>(result, null, HttpStatus.OK);
        }

    }

}
