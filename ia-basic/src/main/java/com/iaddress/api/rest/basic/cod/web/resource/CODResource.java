package com.iaddress.api.rest.basic.cod.web.resource;

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

import com.iaddress.api.rest.basic.cod.service.CODService;
import com.iaddress.api.rest.basic.cod.web.model.DeliveryLocationAddRequest;
import com.iaddress.api.rest.basic.location.web.model.LocationInfoResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Component
@Scope("request")
@Path("/cod")
@Api(value = "/cod", description = "Supports for Cash On Delivery Process.")
public class CODResource {

	@Inject
	private CODService codService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add Delivery Location", notes = "Add delivery location information in the system")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 400, message = "Improper data provided"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response addDeliveryLocation(
			@ApiParam(value = "Delivery Lacation Details") DeliveryLocationAddRequest location) {
		validateDeliveryLocation(location);
		String iAddress = codService.addDeliveryLocation(location);
		return Response.status(201)
				.entity("{\"iAddress\":\"" + iAddress + "\"}").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Suggest Delivery Location", notes = "Search for historical delivery information in the system for the provided input", response = LocationInfoResponse.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response suggestDeliveryLocation(
			@ApiParam(value = "Mobile number against which the delivery locations will be suggested.", required = true) @QueryParam(value = "mobile") String mobile) {
		List<LocationInfoResponse> locationInfos = codService
				.retrieveDeliveryLocations(mobile);
		if (locationInfos != null) {
			return Response.status(200).entity(locationInfos).build();
		} else {
			throw new NotFoundException("Information not found");
		}
	}

	private void validateDeliveryLocation(DeliveryLocationAddRequest location) {
		if (StringUtils.isBlank(location.getMobile())
				|| !StringUtils.isNumeric(location.getMobile())) {
			throw new BadRequestException("Invalid Moblie Number");
		}

		if (StringUtils.isBlank(location.getEmail())) {
			throw new BadRequestException("Invalid Email Id");
		}
	}

}
