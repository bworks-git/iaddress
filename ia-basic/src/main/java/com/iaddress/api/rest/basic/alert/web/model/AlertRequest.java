package com.iaddress.api.rest.basic.alert.web.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlertRequest {

	@XmlElement
	private String receipientEmail;
	@XmlElement
	private String receipientMobile;
	@XmlElement
	private String iAddress;

	public String getReceipientEmail() {
		return receipientEmail;
	}

	public void setReceipientEmail(String receipientEmail) {
		this.receipientEmail = receipientEmail;
	}

	public String getReceipientMobile() {
		return receipientMobile;
	}

	public void setReceipientMobile(String receipientMobile) {
		this.receipientMobile = receipientMobile;
	}

	public String getiAddress() {
		return iAddress;
	}

	public void setiAddress(String iAddress) {
		this.iAddress = iAddress;
	}

}
