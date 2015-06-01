package com.iaddress.api.rest.basic.core.exception;

public class ErrorMessage {

	private String errorMessage;

	public ErrorMessage() {
		super();
	}
	
	public ErrorMessage(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
