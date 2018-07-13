package eu.fiestaiot.portal.testbed.service.dto;

public class Location {
	
	private float lat;
	private float lon;
	
	public Location(float lat, float lon) {
		super();
		this.lat = lat;
		this.lon = lon;
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
