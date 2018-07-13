package eu.fiestaiot.portal.testbed.service.dto;

import javax.validation.constraints.NotNull;

public class GetTestbedsByRegisterIDDTO {
	
	@NotNull
	private String registerID;
	
	public GetTestbedsByRegisterIDDTO(){
		
	}

	public GetTestbedsByRegisterIDDTO(String registerID) {
		super();
		this.registerID = registerID;
	}

	public String getRegisterID() {
		return registerID;
	}

	public void setRegisterID(String registerID) {
		this.registerID = registerID;
	}

}
