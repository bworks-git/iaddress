package com.iaddress.api.rest.basic.location.web.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.iaddress.api.rest.basic.location.service.LocationService;
import com.iaddress.api.rest.basic.location.web.model.LocationAddRequest;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Component
@Scope("request")
@Path("/location")
@Api(value = "/location", description = "Helps to tag a location or to find associated information about the location.")
public class LocationResource {

	@Inject
	private LocationService locationService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add Location", notes = "Add location information in the system")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 415, message = "Wrong data type"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response addLocationByBody(
			@ApiParam(value = "Lacation related information") LocationAddRequest location) {
		String iAddress = locationService.addLocation(location);
		// return Response.status(201).entity("OK").build();
		return Response.status(201)
				.entity("{\"iAddress\":\"" + iAddress + "\"}").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve Location", notes = "Retrieve location information from the system", response = LocationInfoResponse.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response retrieveLocation(
			@ApiParam(value = "GeoCode of the location. A comma separated value of latitute and longitude, e.g. 51.5033630,-0.1276250", required = true) @QueryParam(value = "geoCode") String geoCode) {
		if (isValidGeoCode(geoCode)) {
			List<LocationInfoResponse> locationInfos = locationService
					.retrieveLocation(geoCode);
			return Response.status(200).entity(locationInfos).build();
		} else {
			throw new BadRequestException("Invalid Geo Code");
		}
	}

	@GET
	@Path("/{iAddress}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve iAddress", notes = "Retrieve iAddress information from the system", response = LocationInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response retrieveIAddress(
			@ApiParam(value = "iAddress value for which details needs to be retrieve.", required = true) @PathParam(value = "iAddress") String iAddress) {
		LocationInfoResponse locationInfo = locationService
				.retrieveIAddress(iAddress);
		if (locationInfo != null) {
			return Response.status(200).entity(locationInfo).build();
		} else {
			throw new NotFoundException("iAddress not found");
		}
	}

	private boolean isValidGeoCode(String geoCode) {
		boolean isValid = true;
		if (StringUtils.isBlank(geoCode) || !StringUtils.contains(geoCode, ',')) {
			isValid = false;
		}
		return isValid;
	}
}
