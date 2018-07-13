package eu.fiestaiot.portal.testbed.service;

import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.GsonBuilder;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.fiestaiot.portal.testbed.config.FiestaTestbedRegistryProperties;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.repository.RegisterTestbedsRepository;
import eu.fiestaiot.portal.testbed.service.dto.ExternalServiceResponse;
import eu.fiestaiot.portal.testbed.service.dto.OntologyValidatorBody;
import eu.fiestaiot.portal.testbed.service.dto.TestbedResourceSensorRegisterDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@Service("testbedsRegisterService")
public class TestbedsRegisterService implements ITestbedsRegisterService {

    private static final String VALIDATED = "validated";
    private static final String RESPONSE_CODE = "Response Code: ";
    private static final String REGISTER_SERVICE_CALL_FAILED_STATUS_CODE = "register service call failed - status code: ";

    private final static Logger logger = LoggerFactory.getLogger(TestbedsRegisterService.class);

    @Inject
    private RegisterTestbedsRepository registerTestbedsRepository;
    @Inject
    private FiestaTestbedRegistryProperties fiestaTestbedProperties;

    public Boolean isAnnotatedResourceDescriptionValid(OntologyValidatorBody body, String token) throws IOException {

        return validateResources(body, token);
    }

    // TODO replace the code location, maybe for the dispatcher component.
    @SuppressWarnings("Duplicates")
    private Boolean validateResources(OntologyValidatorBody body, String token) throws IOException {
        List<Header> headers = new ArrayList<>();
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, body.getContentType());
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerContentType);

        StringEntity jsonInput = new StringEntity(body.getAnnotatedResourceDescription());
        jsonInput.setContentEncoding("UTF-8");
        jsonInput.setContentType(body.getContentType());
        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getOntologyValidatorResourceUrl())
                .setEntity(jsonInput).build();

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info("RESPONSE_CODE: " + statusCode);

        if (statusCode != 200) {
            throw new RuntimeException("REGISTER_SERVICE_CALL_FAILED_STATUS_CODE" + statusCode);
        }

        HttpEntity responseEntity = response.getEntity();
        String results = EntityUtils.toString(responseEntity);

        logger.info("Line : {} ", results);
        Object jsonObject = JsonUtils.fromString(results);
        Object isValid = ((Map) jsonObject).get(VALIDATED);

        return Boolean.valueOf(isValid.toString());
    }

    public Boolean isAnnotatedObservationValueValid(OntologyValidatorBody body, String token) throws IOException {
        return validateObservations(body, token);
    }

    //TODO replace the code location, maybe for the dispatcher component.
    @SuppressWarnings("Duplicates")
    private Boolean validateObservations(OntologyValidatorBody body, String token) throws IOException {
        List<Header> headers = new ArrayList<>();
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, body.getContentType());
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerContentType);


        StringEntity jsonInput = new StringEntity(body.getAnnotatedObservation());
        jsonInput.setContentEncoding("UTF-8");
        jsonInput.setContentType(body.getContentType());
        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getOntologyValidatrorObservationUrl())
                .setEntity(jsonInput).build();

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info(RESPONSE_CODE + statusCode);

        if (statusCode != 200) {
            throw new RuntimeException(REGISTER_SERVICE_CALL_FAILED_STATUS_CODE + statusCode);
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        String resultValue = result.toString();
        logger.info("Line : " + resultValue);
        Object jsonObject = JsonUtils.fromString(resultValue);
        Object isValid = ((Map) jsonObject).get(VALIDATED);

        return Boolean.valueOf(isValid.toString());
    }

    public void submit(RegisterTestbeds testbedEntity) throws IOException {
        // save(testbedEntity);
        //registerTestbedsRepository.save(testbedEntity);
        //register(testbedEntity);
    }

    @Override
    public ExternalServiceResponse deteleTestbed(String resourceId, String token) throws ClientProtocolException, IOException {
        // TODO Auto-generated method stub
        ExternalServiceResponse externalServiceResponse = new ExternalServiceResponse();
        List<Header> headers = new ArrayList<>();
        Header headerAccept = new BasicHeader(HttpHeaders.ACCEPT, "application/ld+json");
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/ld+json");
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerAccept);
        headers.add(headerContentType);


        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

        HttpUriRequest request = RequestBuilder.delete().setUri(resourceId)
                .build();
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info("RESPONSE_CODE: " + statusCode);
        externalServiceResponse.setStatusCode(statusCode);
        return externalServiceResponse;
    }

    @Override
    public ExternalServiceResponse registerResources(String resources, String contentType,  String token) throws IOException, JSONException {

        ExternalServiceResponse externalServiceResponse = new ExternalServiceResponse();

        List<Header> headers = new ArrayList<>();
        Header headerAccept = new BasicHeader(HttpHeaders.ACCEPT, contentType);
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, contentType);
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);


        headers.add(headerAccept);
        headers.add(headerContentType);

        StringEntity entityInput = new StringEntity(resources);
        entityInput.setContentEncoding("UTF-8");
        entityInput.setContentType(contentType);

        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getIotRegisterResourceUrl())
                .setEntity(entityInput).build();
        logger.info("request to iot service body: {}", resources);
        logger.info("requset to iot service header:{}" + headers);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();


        logger.info("request to iot service response code: " + statusCode);

        HttpEntity responseEntity = response.getEntity();
        String results = EntityUtils.toString(responseEntity);

        logger.info("request to iot service response result:{} ", results);

        externalServiceResponse.setBody(resources);
        externalServiceResponse.setStatusCode(statusCode);
        externalServiceResponse.setResponse(results);
        externalServiceResponse.setHeaders(headers);

        return externalServiceResponse;
    }

    public ExternalServiceResponse registerTestbed(RegisterTestbeds testbedEntity, String token) throws IOException, JSONException {
        logger.info("do request to IoT registry to register testbed:" + testbedEntity.getIri());

        ExternalServiceResponse externalServiceResponse = new ExternalServiceResponse();
        List<Header> headers = new ArrayList<>();
        Header headerAccept = new BasicHeader(HttpHeaders.ACCEPT, "text/plain");
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerAccept);
        headers.add(headerContentType);


        StringEntity jsonInput = new StringEntity(testbedEntity.getIri());
        jsonInput.setContentEncoding("UTF-8");
        jsonInput.setContentType("text/plain");

        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();

//
        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getIotRegisterTestbedUrl())
                .setEntity(jsonInput).build();

        logger.info("request to iot service body: {}", testbedEntity.getIri());
        logger.info("requset to iot service header:{}" + headers);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info("request to iot service response code: " + statusCode);
        HttpEntity responseEntity = response.getEntity();
        String results = EntityUtils.toString(responseEntity);
        logger.info("request to iot service response result:{} ", results);
        externalServiceResponse.setResponseId(results);
        externalServiceResponse.setBody(testbedEntity.getIri());
        externalServiceResponse.setStatusCode(statusCode);
        externalServiceResponse.setResponse(results);
        externalServiceResponse.setHeaders(headers);

        return externalServiceResponse;
    }

    @Override
    public ExternalServiceResponse registerTestbedResourceSensorsWithJsonText(String jsonText, String token)
            throws JsonGenerationException, JsonMappingException, IOException {

        ExternalServiceResponse externalServiceResponse = new ExternalServiceResponse();

        List<Header> headers = new ArrayList<>();
        Header headerAccept = new BasicHeader(HttpHeaders.ACCEPT, "application/ld+json");
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerAccept);
        headers.add(headerContentType);


        StringEntity jsonInput = new StringEntity(jsonText);
        jsonInput.setContentEncoding("UTF-8");
        jsonInput.setContentType("application/json");
        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getIotRegisterTextUrl())
                .setEntity(jsonInput).build();

        logger.info("request to iot service body: {}", jsonInput.getContent());
        logger.info("requset to iot service header:{}" + headers);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info("request to iot service response code: " + statusCode);

        HttpEntity responseEntity = response.getEntity();
        String results = EntityUtils.toString(responseEntity);

        externalServiceResponse.setStatusCode(statusCode);
        externalServiceResponse.setResponse(results);

        logger.info("request to iot service response result:{} ", results);

        return externalServiceResponse;
    }

    @Override
    public ExternalServiceResponse registerTestbedResourceSensors(
            TestbedResourceSensorRegisterDTO dto, String token) throws JsonGenerationException, JsonMappingException, IOException {
        ExternalServiceResponse externalServiceResponse = new ExternalServiceResponse();

        List<Header> headers = new ArrayList<>();
        Header headerAccept = new BasicHeader(HttpHeaders.ACCEPT, "application/ld+json");
        Header headerContentType = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        Header headeriPlanetDirectoryPro = new BasicHeader("iPlanetDirectoryPro", token);
        headers.add(headeriPlanetDirectoryPro);

        headers.add(headerAccept);
        headers.add(headerContentType);


        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(dto);

        StringEntity jsonInput = new StringEntity(jsonInString);
        jsonInput.setContentEncoding("UTF-8");
        jsonInput.setContentType("application/json");
        HttpClient client = HttpClients.custom().setDefaultHeaders(headers).build();
        HttpUriRequest request = RequestBuilder.post().setUri(fiestaTestbedProperties.getEnpoints().getIotRegisterTextUrl())
                .setEntity(jsonInput).build();

        logger.info("request to iot service body: {}", jsonInput.getContent());
        logger.info("requset to iot service header:{}" + headers);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();

        logger.info("request to iot service response code: " + statusCode);

        HttpEntity responseEntity = response.getEntity();
        String results = EntityUtils.toString(responseEntity);

        externalServiceResponse.setStatusCode(statusCode);
        externalServiceResponse.setResponse(results);

        logger.info("request to iot service response result:{} ", results);

        return externalServiceResponse;
    }

}
