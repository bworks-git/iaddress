package com.iaddress.api.rest.basic.core.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements
		ExceptionMapper<NotFoundException> {
	
	@Context
    private HttpHeaders headers;

	public Response toResponse(NotFoundException exception) {
		return Response.status(Status.NOT_FOUND)
				.entity(new ErrorMessage(exception.getMessage()))
				.type(getAcceptType()).build();
	}
	
	private String getAcceptType(){
		/*
        List<MediaType> accepts = headers.getAcceptableMediaTypes();
        if (accepts!=null && accepts.size() > 0) {
            //choose one
        }else {
            //return a default one like Application/json
        }
        */
		return MediaType.APPLICATION_JSON;
   }

}
