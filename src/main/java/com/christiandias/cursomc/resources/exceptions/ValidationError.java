package com.christiandias.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<FieldMessage>();
	
	public ValidationError(long timpestamp, Integer status, String error, String message, String path) {
		super(timpestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void addError(String fieldName,String message) {
		list.add(new FieldMessage(fieldName,message));
	}

	
	
	
}
