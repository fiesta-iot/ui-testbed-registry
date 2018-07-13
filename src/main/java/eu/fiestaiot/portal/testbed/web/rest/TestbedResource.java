/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.fiestaiot.portal.testbed.web.rest;

import com.codahale.metrics.annotation.Timed;
import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.service.FilterService;
import eu.fiestaiot.portal.testbed.service.ITestbedsRegisterService;
import eu.fiestaiot.portal.testbed.service.RegisterTestbedsService;
import eu.fiestaiot.portal.testbed.service.dto.TestbedRegisterInputDTO;
import eu.fiestaiot.portal.testbed.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nguyendanghung
 */

@RestController
@RequestMapping("/api")
public class TestbedResource {
    private final Logger log = LoggerFactory
            .getLogger(TestbedResource.class);

    @Inject
    private RegisterTestbedsService registerTestbedsService;
    @Inject
    private ITestbedsRegisterService testbedsRegisterService;
    
    @Inject
    private FilterService filterService;
    
    
    @GetMapping("/getAllTestbeds")
    @Timed
    public ResponseEntity<List<RegisterTestbeds>> getAllTestbeds(
            @ApiParam Pageable pageable, HttpServletRequest request) throws URISyntaxException {
        Page<RegisterTestbeds> page = registerTestbedsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
                page, "/api/getAllTestbeds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    @GetMapping("/getTestbedByID/{id}")
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
    @PostMapping("/getTestbedByRegisterID")
    @Timed
    public ResponseEntity<?> findTestbedByRegisterID(
            @Valid @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request getTestbedByRegisterID : {}", testbedRegisterInputDTO);
        String result = filterService.findTestbedByRegisterID(testbedRegisterInputDTO);        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/getTestbedByIRIAndName")
    @Timed
    public ResponseEntity<?> getTestbedByIRIAndName(
            @Valid @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request getTestbedByIRIAndName : {}", testbedRegisterInputDTO);
        String result = filterService.findTestbedByIRIAndName(testbedRegisterInputDTO);        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/getTestbedByIRI")
    @Timed
    public ResponseEntity<?> getTestbedByIRI(
            @Valid @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO,
            HttpServletRequest request) throws URISyntaxException, IOException,
            JSONException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request getTestbedByIRIAndName : {}", testbedRegisterInputDTO);
        String result = filterService.findTestbedByIRI(testbedRegisterInputDTO);        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    @PostMapping("/getAllTestbedsByUserID")
    @Timed
    public ResponseEntity<?> getAllTestbedsByUserID(
            @ApiParam Pageable pageable, @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO, HttpServletRequest request)
            throws URISyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request to get a page of getAllTestbedsByUserID");
        String result = filterService.getAllTestbedsByUserID(testbedRegisterInputDTO);       
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/getAllTestbedsRegisterIDsByUserID")
    @Timed
    public ResponseEntity<?> getAllTestbedsRegisterIDsByUserID(
            @ApiParam Pageable pageable, @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO, HttpServletRequest request)
            throws URISyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request to get a page of getAllTestbedsRegisterIDsByUserID");
        String result = filterService.findAllTestbedsRegisterIDsByUserID(testbedRegisterInputDTO);                
        return new ResponseEntity<>(result,  HttpStatus.OK);
    }

    @PostMapping("/getAllTestbedsRegisterByRegisterIDList")
    @Timed
    public ResponseEntity<?> getAllTestbedsRegisterByRegisterIDList(
            @RequestBody TestbedRegisterInputDTO testbedRegisterInputDTO, HttpServletRequest request)
            throws URISyntaxException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException {
        log.info("REST request to get a page of getAllTestbedsRegisterByRegisterIDList");
        String list = filterService.findAllTestbedsRegisterByRegisterIDList(testbedRegisterInputDTO);             
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    
}
