package com.iaddress.api.rest.basic.location.service;

import static com.iaddress.api.rest.basic.core.IAddressConstants.IADDRESS_ALLOWED_CHAR_SET;
import static com.iaddress.api.rest.basic.core.IAddressConstants.IADDRESS_LENGTH;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;

import com.iaddress.api.rest.basic.location.data.model.LocationModel;
import com.iaddress.api.rest.basic.location.data.repo.LocationRepository;
import com.iaddress.api.rest.basic.location.mapper.LocationMapper;
import com.iaddress.api.rest.basic.location.web.model.LocationAddRequest;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;

@Named
public class LocationService {

	@Inject
	private LocationRepository locationRepository;

	@Inject
	private LocationMapper mapper;

	public String addLocation(LocationAddRequest requestBean) {
		LocationModel newLocation = mapper
				.locationAddRequestToLocationModel(requestBean);
		String newiAddress = RandomStringUtils.random(IADDRESS_LENGTH,
				IADDRESS_ALLOWED_CHAR_SET);
		newLocation.setiAddress(newiAddress);
		locationRepository.addLocation(newLocation);
		return newiAddress;
	}

	public List<LocationInfoResponse> retrieveLocation(String geoCode) {
		LocationModel baseLocation = mapper
				.stringGeoCodeToLocationModel(geoCode);
		List<LocationModel> locations = locationRepository
				.retrieveLocation(baseLocation);
		return mapper.listLocationModelToListLocationInfoResponse(locations);
	}

	public LocationInfoResponse retrieveIAddress(String iAddress) {
		LocationModel baseLocation = mapper
				.stringIAddressToLocationModel(iAddress);
		LocationModel location = locationRepository
				.retrieveIAddress(baseLocation);
		return mapper.locationModelToLocationInfoResponse(location);
	}

}
