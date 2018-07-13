package eu.fiestaiot.portal.testbed.service.dto;


public class Device {

	
	
	private String id;
	
	private String qk;
	
	private String uom;
	
	private Location location;
	
	public Device(){
		
	}
	

	public Device(String id, String qk, String uom, Location location) {
		super();
		this.id = id;
		this.qk = qk;
		this.uom = uom;
		this.location = location;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	

}
