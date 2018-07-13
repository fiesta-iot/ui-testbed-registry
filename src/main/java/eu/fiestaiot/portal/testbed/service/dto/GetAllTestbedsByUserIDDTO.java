package eu.fiestaiot.portal.testbed.service.dto;

import javax.validation.constraints.NotNull;

public class GetAllTestbedsByUserIDDTO {
	@NotNull
	private String userID;

	public GetAllTestbedsByUserIDDTO(){
		
	}
	
	public GetAllTestbedsByUserIDDTO(String userID) {
		super();
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
