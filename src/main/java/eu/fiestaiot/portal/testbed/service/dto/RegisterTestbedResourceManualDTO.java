package eu.fiestaiot.portal.testbed.service.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import eu.fiestaiot.portal.testbed.domain.RegisterTestbeds;

public class RegisterTestbedResourceManualDTO {
	@NotNull
	private RegisterTestbeds registerTestbeds;
	@NotNull
	private List<DeviceDTO> devices;
	
	public RegisterTestbeds getRegisterTestbeds() {
		return registerTestbeds;
	}
	public void setRegisterTestbeds(RegisterTestbeds registerTestbeds) {
		this.registerTestbeds = registerTestbeds;
	}
	public List<DeviceDTO> getDevices() {
		return devices;
	}
	public void setDevices(List<DeviceDTO> devices) {
		this.devices = devices;
	}
	public RegisterTestbedResourceManualDTO(){
		
	}
	public RegisterTestbedResourceManualDTO(RegisterTestbeds registerTestbeds,
			List<DeviceDTO> devices) {
		super();
		this.registerTestbeds = registerTestbeds;
		this.devices = devices;
	}
	
	
	
}
