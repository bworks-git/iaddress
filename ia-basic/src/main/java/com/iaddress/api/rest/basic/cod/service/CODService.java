package com.iaddress.api.rest.basic.cod.service;

import static com.iaddress.api.rest.basic.core.IAddressConstants.IADDRESS_ALLOWED_CHAR_SET;
import static com.iaddress.api.rest.basic.core.IAddressConstants.IADDRESS_LENGTH;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;

import com.iaddress.api.rest.basic.cod.mapper.CODLocationMapper;
import com.iaddress.api.rest.basic.cod.web.model.DeliveryLocationAddRequest;
import com.iaddress.api.rest.basic.location.data.model.LocationModel;
import com.iaddress.api.rest.basic.location.data.repo.LocationRepository;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;

@Named
public class CODService {

	@Inject
	private LocationRepository locationRepository;

	@Inject
	private CODLocationMapper mapper;

	public String addDeliveryLocation(DeliveryLocationAddRequest requestBean) {
		LocationModel newLocation = mapper
				.deliveryLocationAddRequestToLocationModel(requestBean);
		String newiAddress = RandomStringUtils.random(IADDRESS_LENGTH,
				IADDRESS_ALLOWED_CHAR_SET);
		newLocation.setiAddress(newiAddress);
		locationRepository.addLocation(newLocation);
		return newiAddress;
	}
	
	public List<LocationInfoResponse> retrieveDeliveryLocations(String mobile) {
		LocationModel baseLocation = mapper.stringMobileToLocationModel(mobile);
		List<LocationModel> locations = locationRepository
				.retrieveLocationByMobile(baseLocation);
		return mapper.listLocationModelToListLocationInfoResponse(locations);
	}

}
