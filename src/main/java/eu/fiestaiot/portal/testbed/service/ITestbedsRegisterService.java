package eu.fiestaiot.portal.testbed.service;

import java.io.IOException;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;
import eu.fiestaiot.portal.testbed.service.dto.ExternalServiceResponse;
import eu.fiestaiot.portal.testbed.service.dto.OntologyValidatorBody;
import eu.fiestaiot.portal.testbed.service.dto.TestbedResourceSensorRegisterDTO;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;

public interface ITestbedsRegisterService {

    ExternalServiceResponse deteleTestbed(String resourceId, String token) throws ClientProtocolException, IOException;

    ExternalServiceResponse registerTestbed(RegisterTestbeds testbedEntity, String token) throws IOException, JSONException;

    ExternalServiceResponse registerResources(String resources, String contentType, String token) throws IOException, JSONException;

    Boolean isAnnotatedObservationValueValid(OntologyValidatorBody body, String token) throws IOException;

    Boolean isAnnotatedResourceDescriptionValid(OntologyValidatorBody value, String token) throws IOException;

    void submit(RegisterTestbeds testbedEntity) throws IOException;

    ExternalServiceResponse registerTestbedResourceSensors(TestbedResourceSensorRegisterDTO dto, String token) throws JsonGenerationException, JsonMappingException, IOException;

    ExternalServiceResponse registerTestbedResourceSensorsWithJsonText(String jsonText, String token) throws JsonGenerationException, JsonMappingException, IOException;

}
