package com.iaddress.api.rest.basic.cod.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.iaddress.api.rest.basic.cod.web.model.DeliveryLocationAddRequest;
import com.iaddress.api.rest.basic.location.data.model.LocationModel;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;

@Named
public class CODLocationMapper {

	public LocationModel deliveryLocationAddRequestToLocationModel(
			DeliveryLocationAddRequest requestBean) {
		LocationModel dbBean = null;
		if (requestBean != null) {
			dbBean = new LocationModel();
			dbBean.setGeoAddress(requestBean.getGeoAddress());
			dbBean.setGeoCode(new double[] {
					Double.parseDouble(requestBean.getLat()),
					Double.parseDouble(requestBean.getLng()) });
			dbBean.setMobile(requestBean.getMobile());
			dbBean.setEmail(requestBean.getEmail());
		}
		return dbBean;
	}

	public LocationModel stringMobileToLocationModel(String mobile) {
		LocationModel dbBean = null;
		dbBean = new LocationModel();
		dbBean.setMobile(mobile);
		return dbBean;
	}

	public List<LocationInfoResponse> listLocationModelToListLocationInfoResponse(
			List<LocationModel> locations) {
		List<LocationInfoResponse> uiBeans = new ArrayList<LocationInfoResponse>();
		if (locations != null) {
			for (LocationModel location : locations) {
				uiBeans.add(locationModelToLocationInfoResponse(location));
			}
		}
		return uiBeans;
	}

	public LocationInfoResponse locationModelToLocationInfoResponse(
			LocationModel location) {
		LocationInfoResponse uiBean = null;
		if (location != null) {
			uiBean = new LocationInfoResponse();
			uiBean.setiAddress(location.getiAddress());
			uiBean.setGeoAddress(location.getGeoAddress());
			uiBean.setLat(String.valueOf(location.getGeoCode()[0]));
			uiBean.setLng(String.valueOf(location.getGeoCode()[1]));
			// BeanUtils.copyProperties(location, uiBean);
		}
		return uiBean;
	}

}
