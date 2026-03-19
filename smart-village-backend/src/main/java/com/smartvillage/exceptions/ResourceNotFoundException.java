package com.smartvillage.exceptions;

public class ResourceNotFoundException extends ApiException {
	public ResourceNotFoundException(String message) {
		super(message);
	}
	public ResourceNotFoundException(String resourse, String field, Object value) {
		super(String.format(resourse + " not found with " + field + " : '" + value + "'"));

	}

}
