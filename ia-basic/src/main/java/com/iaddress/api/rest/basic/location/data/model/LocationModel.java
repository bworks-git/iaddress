package com.iaddress.api.rest.basic.location.data.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author amit sarkar
 *
 */
@Document(collection = "t_locations")
public class LocationModel {

	@Id
	private String id;

	private String iAddress;
	private double[] geoCode;
	private String geoAddress;
	private String mobile;
	private String email;

	public String getiAddress() {
		return iAddress;
	}

	public void setiAddress(String iAddress) {
		this.iAddress = iAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double[] getGeoCode() {
		return geoCode;
	}

	public void setGeoCode(double[] geoCode) {
		this.geoCode = geoCode;
	}

	public String getGeoAddress() {
		return geoAddress;
	}

	public void setGeoAddress(String geoAddress) {
		this.geoAddress = geoAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
