package com.iaddress.api.rest.basic.location.web.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author amit sarkar
 *
 */
@XmlRootElement(name = "Location")
public class LocationAddRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "lat")
	private String lat;

	@XmlElement(name = "lng")
	private String lng;

	@XmlElement(name = "geoAddress")
	private String geoAddress;

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
