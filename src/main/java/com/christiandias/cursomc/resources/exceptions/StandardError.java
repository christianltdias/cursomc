package com.christiandias.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long timpestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

	public StandardError(long timpestamp, Integer status, String error, String message, String path) {
		this.timpestamp = timpestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public long getTimpestamp() {
		return timpestamp;
	}

	public void setTimpestamp(long timpestamp) {
		this.timpestamp = timpestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
}
