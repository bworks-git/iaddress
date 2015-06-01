package com.iaddress.api.rest.basic.location.web.model;

public class LocationInfoResponse {

	private String iAddress;
	
	private String lat;

	private String lng;

	private String geoAddress;

	public String getiAddress() {
		return iAddress;
	}

	public void setiAddress(String iAddress) {
		this.iAddress = iAddress;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getGeoAddress() {
		return geoAddress;
	}

	public void setGeoAddress(String geoAddress) {
		this.geoAddress = geoAddress;
	}

}
