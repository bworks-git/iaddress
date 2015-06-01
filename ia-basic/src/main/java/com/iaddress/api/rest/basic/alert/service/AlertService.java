package com.iaddress.api.rest.basic.alert.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.iaddress.api.rest.basic.alert.web.model.AlertRequest;
import com.iaddress.api.rest.basic.location.service.LocationService;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;

@Named
public class AlertService {

	@Inject
	private LocationService locationService;

	public void sendIAddressInfo(AlertRequest alertRequest) {
		LocationInfoResponse locationInfoResponse = locationService
				.retrieveIAddress(alertRequest.getiAddress());
		String to = alertRequest.getReceipientEmail();
		String subject = "Info for iAddress : "
				+ locationInfoResponse.getiAddress();
		String content = "Please note the iAddress details."
				+ "\niAddress : " + locationInfoResponse.getiAddress()  
				+ "\nRegistered Address : " + locationInfoResponse.getGeoAddress();
		AlertServiceUtil.sendEMail(to, subject, content);
	}

}
