package com.iaddress.api.rest.basic.location.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.iaddress.api.rest.basic.location.data.model.LocationModel;
import com.iaddress.api.rest.basic.location.web.model.LocationAddRequest;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;

/**
 * @author amit sarkar
 *
 */
@Named
public class LocationMapper {

	public LocationModel locationAddRequestToLocationModel(
			LocationAddRequest requestBean) {
		LocationModel dbBean = null;
		if (requestBean != null) {
			dbBean = new LocationModel();
			dbBean.setGeoAddress(requestBean.getGeoAddress());
			dbBean.setGeoCode(new double[] {
					Double.parseDouble(requestBean.getLat()),
					Double.parseDouble(requestBean.getLng()) });
		}
		return dbBean;
	}

	/**
	 * Geo Code validation should be done earlier. A valid geo code is a
	 * combination of latitude and longitude separated by comma no space
	 * allowed. e.g. 51.5033630,-0.1276250.
	 * 
	 * @param geoCode
	 * @return
	 */
	public LocationModel stringGeoCodeToLocationModel(String geoCode) {
		LocationModel dbBean = null;
		dbBean = new LocationModel();
		String position[] = geoCode.split(",");
		dbBean.setGeoCode(new double[] { Double.parseDouble(position[0]),
				Double.parseDouble(position[1]) });
		return dbBean;
	}

	public List<LocationInfoResponse> listLocationModelToListLocationInfoResponse(
			List<LocationModel> locations) {
		List<LocationInfoResponse> uiBeans = new ArrayList<LocationInfoResponse>();
		if(locations != null){
			for (LocationModel location : locations) {
				uiBeans.add(locationModelToLocationInfoResponse(location));
			}
		}
		return uiBeans;
	}

	public LocationInfoResponse locationModelToLocationInfoResponse(LocationModel location){
		LocationInfoResponse uiBean = null;
		if(location!=null){
			uiBean = new LocationInfoResponse();
			uiBean.setiAddress(location.getiAddress());
			uiBean.setGeoAddress(location.getGeoAddress());
			uiBean.setLat(String.valueOf(location.getGeoCode()[0]));
			uiBean.setLng(String.valueOf(location.getGeoCode()[1]));
			//BeanUtils.copyProperties(location, uiBean);
		}
		return uiBean;
	}

	public LocationModel stringIAddressToLocationModel(String iAddress) {
		LocationModel dbBean = null;
		dbBean = new LocationModel();
		dbBean.setiAddress(iAddress);
		return dbBean;
	}
	
}
