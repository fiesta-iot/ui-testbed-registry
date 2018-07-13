package eu.fiestaiot.portal.testbed.service.dto;

import javax.validation.constraints.NotNull;

public class DeviceDTO {

	@NotNull
	private String id;
	@NotNull
	private String qk;
	@NotNull
	private String uom;
	@NotNull
	private float lat;
	@NotNull
	private float lon;
	
	public DeviceDTO(){
		
	}
	
	public DeviceDTO(String id, String qk, String uom, float lat, float lon) {
		super();
		this.id = id;
		this.qk = qk;
		this.uom = uom;
		this.lat = lat;
		this.lon = lon;
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getQk() {
		return qk;
	}
	public void setQk(String qk) {
		this.qk = qk;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
}
