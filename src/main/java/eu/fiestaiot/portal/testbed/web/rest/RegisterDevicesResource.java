package eu.fiestaiot.portal.testbed.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.fiestaiot.portal.testbed.domain.RegisterDevices;
import eu.fiestaiot.portal.testbed.service.ITestbedsRegisterService;
import eu.fiestaiot.portal.testbed.service.OpenAMSecurityHelper;
import eu.fiestaiot.portal.testbed.service.RegisterDevicesService;
import eu.fiestaiot.portal.testbed.service.dto.Device;
import eu.fiestaiot.portal.testbed.service.dto.DeviceDTO;
import eu.fiestaiot.portal.testbed.service.dto.ExternalServiceResponse;
import eu.fiestaiot.portal.testbed.service.dto.Location;
import eu.fiestaiot.portal.testbed.service.dto.OntologyValidatorBody;
import eu.fiestaiot.portal.testbed.service.dto.RegisterTestbedResourceManualDTO;
import eu.fiestaiot.portal.testbed.service.dto.RegisterTestbedResourceWithTextDTO;
import eu.fiestaiot.portal.testbed.service.dto.RegisterTestbedResourceWithUpload;
import eu.fiestaiot.portal.testbed.service.dto.TestbedResourceSensorRegisterDTO;
import eu.fiestaiot.portal.testbed.web.rest.util.HeaderUtil;
import eu.fiestaiot.portal.testbed.web.rest.util.PaginationUtil;
import eu.fiestaiot.portal.testbed.web.rest.vm.ServiceResponse;
import io.swagger.annotations.ApiParam;

import org.codehaus.jackson.JsonGenerationException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RegisterDevices.
 */
@RestController
@RequestMapping("/api")
public class RegisterDevicesResource {

    private final Logger log = LoggerFactory.getLogger(RegisterDevicesResource.class);

    @Inject
    private RegisterDevicesService registerDevicesService;

    @Inject
    private ITestbedsRegisterService testbedsRegisterService;

    @Inject
    private OpenAMSecurityHelper openAMSecurityHelper;


    /**
     *
     * @param registerTestbdResourceWithUpload
     * @param request
     * @return
     * @throws URISyntaxException
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws JSONException
     */
    @PostMapping("/register-devices/upload")
    @Timed
    public ResponseEntity<?> createRegisterDevicesByUpload(@Valid @RequestBody RegisterTestbedResourceWithUpload registerTestbdResourceWithUpload, HttpServletRequest request) throws URISyntaxException, JsonParseException, JsonMappingException, IOException, JSONException {
        log.info("REST request to save RegisterDevices : {}", registerTestbdResourceWithUpload);

        String token = openAMSecurityHelper.getToken(request);
        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);
        if(userID.isEmpty()) {
          return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "RegisterDevices",
                                            "Register devices fail with invalid userID"))
                    .body(new ServiceResponse("Register testbed resource fail with invalid userID"));
        }

        log.info("REST request with cookie userID : {}", userID);

        String contentUpload = new String(registerTestbdResourceWithUpload.getUploadContent(), "UTF-8");

        Boolean rs = testbedsRegisterService
                .isAnnotatedResourceDescriptionValid(
                        new OntologyValidatorBody("", contentUpload, registerTestbdResourceWithUpload.getContentType()), "");

        if (!rs) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                    "registerDevices",
                                    "Upload content validation is not valid"
                            ))
                    .body(new ServiceResponse("Upload content resource validation is not valid"));
        }

        RegisterDevices registerDevices = new RegisterDevices();
        registerDevices.setAnnotatedResourceDescription(new String(registerTestbdResourceWithUpload.getUploadContent(), "UTF-8"));
        registerDevices.setUploadContent(registerTestbdResourceWithUpload.getUploadContent());

        ExternalServiceResponse response = testbedsRegisterService.registerResources(registerDevices.getAnnotatedResourceDescription(),registerTestbdResourceWithUpload.getContentType(), token);

        if (response.getStatusCode() == 201) {
            registerDevices.setUserID(userID);
            registerDevices.setServiceResponse(response.getResponse());
            RegisterDevices result = registerDevicesService.save(registerDevices);
            return ResponseEntity.created(new URI("/api/register-devices/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert("registerDevices", result.getId().toString()))
                    .body(result);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                    "registerDevices",
                                    "Register testbed resource fail with statusCode:"
                                    + response.getStatusCode()))
                   .body(new ServiceResponse("Register testbed resource to IoT registry fail with statusCode:"+ response.getStatusCode()));
        }

    }

    /**
     *
     * @param registerTestbdResourceWithText
     * @param request
     * @return
     * @throws URISyntaxException
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     * @throws JSONException
     */
    @PostMapping("/register-devices/text")
    @Timed
    public ResponseEntity<?> createRegisterDevicesByText(@Valid @RequestBody RegisterTestbedResourceWithTextDTO registerTestbdResourceWithText, HttpServletRequest request) throws URISyntaxException, JsonParseException, JsonMappingException, IOException, JSONException {
        log.info("REST request to save RegisterDevices : {}", registerTestbdResourceWithText);

        String token = openAMSecurityHelper.getToken(request);
        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);
        if(userID.isEmpty()) {
          return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "RegisterDevices",
                                            "Register testbed resource fail with invalid userID"))
                    .body(new ServiceResponse("Register testbed resource fail with invalid userID"));
        }
        log.info("REST request with cookie userID : {}", userID);

        Boolean rs = testbedsRegisterService
                .isAnnotatedResourceDescriptionValid(
                        new OntologyValidatorBody("", registerTestbdResourceWithText.getAnnotatedResourceDescription(), registerTestbdResourceWithText.getContentType()), "");


        if (!rs) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                    "registerDevices",
                                    "Register testbed resource fail with invalid resource!"
                            ))
                     .body(new ServiceResponse("Register testbed resource fail with invalid resource!" ));
        }

        RegisterDevices registerDevices = new RegisterDevices();
        registerDevices.setAnnotatedResourceDescription(registerTestbdResourceWithText.getAnnotatedResourceDescription());
        ExternalServiceResponse response = testbedsRegisterService.registerResources(registerDevices.getAnnotatedResourceDescription(), registerTestbdResourceWithText.getContentType(), token);

        if (response.getStatusCode() == 201) {
            registerDevices.setUserID(userID);
            registerDevices.setServiceResponse(response.getResponse());
            RegisterDevices result = registerDevicesService.save(registerDevices);
            return ResponseEntity.created(new URI("/api/register-devices/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert("registerDevices", result.getId().toString()))
                    .body(result);
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                    "registerDevices",
                                    "Register testbed resource to IoT registry fail with statusCode:"
                                    + response.getStatusCode()))
                   .body(new ServiceResponse("Register testbed resource to IoT registry fail with statusCode:" + response.getStatusCode()));
        }

    }

    /**
     * POST /register-devices/manual : Create a new registerDevices with manual.
     *
     * @param registerDevices the registerDevices to create
     * @return the ResponseEntity with status 201 (Created) and with body the
     * new registerDevices, or with status 400 (Bad Request) if the
     * registerDevices has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException
     * @throws org.codehaus.jackson.map.JsonMappingException
     * @throws JsonGenerationException
     */
    @PostMapping("/register-devices/manual")
    @Timed
    public ResponseEntity<?> createRegisterDevices(@Valid @RequestBody RegisterTestbedResourceManualDTO registerDevices, HttpServletRequest request) throws URISyntaxException, JsonGenerationException, org.codehaus.jackson.map.JsonMappingException, IOException {
        log.info("REST request to save RegisterDevices : {}", registerDevices);
        String token = openAMSecurityHelper.getToken(request);

        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);
        if(userID.isEmpty()) {
          return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil
                                    .createEntityUpdateAlert(
                                            "RegisterDevices",
                                            "Register devices fail with invalid userID"))
                   .body(new ServiceResponse("Register testbed resource fail with invalid userID"));
        }
        log.info("REST request with cookie userID : {}", userID);

        RegisterDevices device = new RegisterDevices();
        device.setRegisterID(registerDevices.getRegisterTestbeds().getRegisterID());

        TestbedResourceSensorRegisterDTO dto = new TestbedResourceSensorRegisterDTO();
        List<Device> devices = new ArrayList<Device>();
        if (registerDevices.getDevices() != null) {
            for (DeviceDTO deviceDto : registerDevices.getDevices()) {

                Location loc = new Location(deviceDto.getLat(), deviceDto.getLon());
                Device dv = new Device(deviceDto.getId(), deviceDto.getQk(), deviceDto.getUom(), loc);
                devices.add(dv);

            }
        }
        dto.setDevices(devices);
        dto.setId(registerDevices.getRegisterTestbeds().getRegisterID());

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(dto);

        device.annotatedResourceDescription(jsonInString);
        //device.setRegisterTestbeds(registerDevices.getRegisterTestbeds());
        device.setTestbedId(registerDevices.getRegisterTestbeds().getId());

        ExternalServiceResponse response = testbedsRegisterService.registerTestbedResourceSensors(dto, token);

        if (response.getStatusCode() == 200) {

            device.setUserID(userID);
            device.setServiceResponse(response.getResponse());
            device.setRegisterID(registerDevices.getRegisterTestbeds().getRegisterID());

            try {
                ExternalServiceResponse registerResources = testbedsRegisterService.registerResources(response.getResponse(), "application/ld+json", token);
                if(registerResources.getStatusCode() == 201) {

                    device.setUserID(userID);
                    device.setServiceResponse(registerResources.getResponse());
                    device.setRegisterID(registerDevices.getRegisterTestbeds().getRegisterID());
                    RegisterDevices result = registerDevicesService.save(device);
                    return ResponseEntity.created(new URI("/api/register-devices/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert("registerDevices", result.getId().toString()))
                        .body(result);
                } else {
                    return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                "registerDevices",
                                "Register testbed resource to IoT registry fail with statusCode:"
                                    + response.getStatusCode()))
                        .body(new ServiceResponse("Register testbed resource to IoT registry fail with statusCode:" + response.getStatusCode()));
                }

            } catch (Exception ex) {

                log.error("error handler register resource with IoT Registry Service: {}", ex.toString());
                log.info("error handler register resource with IoT Registry Service: {}", ex.toString());
                return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                        HeaderUtil.createEntityUpdateAlert(
                            "registerDevices",
                            "Register testbed resource to IoT registry fail with statusCode:"
                                + response.getStatusCode()))
                    .body(new ServiceResponse("Register testbed resource to IoT registry fail with statusCode:" + response.getStatusCode()));
            }

        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(
                            HeaderUtil.createEntityUpdateAlert(
                                    "registerDevices",
                                    "Register testbed resource to IoT registry fail with statusCode:"
                                    + response.getStatusCode()))
                    .body(new ServiceResponse("Register testbed resource to IoT registry fail with statusCode:" + response.getStatusCode()));
        }

    }


    /**
     * GET /register-devices : get all the registerDevices.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of
     * registerDevices in body
     * @throws URISyntaxException if there is an error to generate the
     * pagination HTTP headers
     */
    @GetMapping("/register-devices")
    @Timed
    public ResponseEntity<List<RegisterDevices>> getAllRegisterDevices(@ApiParam Pageable pageable, HttpServletRequest request)
            throws URISyntaxException {
        log.info("REST request to get a page of RegisterDevices");
        String token = openAMSecurityHelper.getToken(request);
        log.info("REST request with cookie token : {}", token);
        String userID = openAMSecurityHelper.getUserID(token);
        if (openAMSecurityHelper.isAdmin(userID, token)) {
            Page<RegisterDevices> page = registerDevicesService.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/register-devices");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        } else {
            Page<RegisterDevices> page = registerDevicesService.findByUserID(pageable, userID);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/register-devices");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }

    }

    /**
     * GET /register-devices/:id : get the "id" registerDevices.
     *
     * @param id the id of the registerDevices to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the
     * registerDevices, or with status 404 (Not Found)
     */
    @GetMapping("/register-devices/{id}")
    @Timed
    public ResponseEntity<RegisterDevices> getRegisterDevices(@PathVariable Long id, HttpServletRequest request) {
        log.info("REST request to get RegisterDevices : {}", id);
        RegisterDevices registerDevices = registerDevicesService.findOne(id);
        return Optional.ofNullable(registerDevices)
                .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
