package eu.fiestaiot.portal.testbed.service.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestbedRegisterInputDTO {


	private String registerID;

	private String iri;

	private String name;

	private String userID;

	private List<String> registerIDList;
	
	private List<String>  expectedFieldsAsResult;


	public List<String> getExpectedFieldsAsResult() {
		return expectedFieldsAsResult;
	}

	public void setExpectedFieldsAsResult(List<String> expectedFieldsAsResult) {
		this.expectedFieldsAsResult = expectedFieldsAsResult;
	}


	public List<String> getRegisterIDList() {
		return registerIDList;
	}

	public void setRegisterIDList(List<String> registerIDList) {
		this.registerIDList = registerIDList;
	}

	public String getRegisterID() {
		return registerID;
	}

	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}

	public String getIri() {
		return iri;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	


//	public static void main(String[] args) {
//		Gson gson = new GsonBuilder().create();
//		TestbedRegisterInputDTO target = new TestbedRegisterInputDTO();
////		String a[] = new String[]{"registerID","userID","name","iri",
////				"annotatedResourceDescription","annotatedObservation",
////				"latitude","longitude","getObservationsURL","getLastObservationsURL",
////				"getApiKey","pushObservationsURL","pushLastObservationsURL","pushApiKey"};
////                
//                
//                String a[] = new String[]{"iri"};
//		
//                
//                target.setExpectedFieldsAsResult(Arrays.asList(a));
//                
//		target.setIri("IRI");
//		target.setName("testbed1");
//		target.setUserID("userID");
//                
//		target.setRegisterIDList(new ArrayList());
//		System.out.println(gson.toJson(target));
//	}

}
