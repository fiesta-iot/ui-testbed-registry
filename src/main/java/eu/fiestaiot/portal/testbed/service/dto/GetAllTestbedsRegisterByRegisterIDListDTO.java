package eu.fiestaiot.portal.testbed.service.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class GetAllTestbedsRegisterByRegisterIDListDTO {
	
	@NotNull
	private List<String> registerIDList;

	public GetAllTestbedsRegisterByRegisterIDListDTO(){
		
	}
	
	public GetAllTestbedsRegisterByRegisterIDListDTO(List<String> registerIDList) {
		super();
		this.registerIDList = registerIDList;
	}

	public List<String> getRegisterIDList() {
		return registerIDList;
	}

	public void setRegisterIDList(List<String> registerIDList) {
		this.registerIDList = registerIDList;
	}
	

}
