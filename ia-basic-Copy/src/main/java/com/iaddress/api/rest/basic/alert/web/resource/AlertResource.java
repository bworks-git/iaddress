package com.iaddress.api.rest.basic.alert.web.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;

import com.iaddress.api.rest.basic.alert.service.AlertService;
import com.iaddress.api.rest.basic.alert.web.model.AlertRequest;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Named
@Scope("request")
@Path("/alert")
@Api(value = "/alert", description = "Provides support for the alert functionalities for various operations.")
public class AlertResource {

	
	@Inject
	private AlertService alertService;

	@POST
	@Path("/sendIAddress")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Send iAddress Info", notes = "Sends the iAddress information to the provided email address.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 500, message = "Something wrong in Server") })
	public Response sendIAddressInfo(@ApiParam(value = "Lacation related information") AlertRequest alertRequest){
		alertService.sendIAddressInfo(alertRequest);
		return Response.status(201).build();
	}
	
	
}
