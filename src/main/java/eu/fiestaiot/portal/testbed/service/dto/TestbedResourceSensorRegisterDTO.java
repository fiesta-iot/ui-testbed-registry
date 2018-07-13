package eu.fiestaiot.portal.testbed.service.dto;

import java.util.List;

public class TestbedResourceSensorRegisterDTO {
	private String id;
	private List<Device> devices;
	
	public TestbedResourceSensorRegisterDTO(){
		
	}
	public TestbedResourceSensorRegisterDTO(String id, List<Device> devices) {
		super();
		this.id = id;
		this.devices = devices;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	

}