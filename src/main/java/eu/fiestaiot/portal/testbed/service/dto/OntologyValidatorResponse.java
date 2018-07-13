package eu.fiestaiot.portal.testbed.service.dto;

public class OntologyValidatorResponse {
	private Boolean result;
	private String message;
	
	
	public OntologyValidatorResponse(){
		
	}
	public OntologyValidatorResponse(Boolean result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
