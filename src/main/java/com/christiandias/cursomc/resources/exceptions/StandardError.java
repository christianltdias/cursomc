package com.christiandias.cursomc.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	private long timpeStamp;
	
	public StandardError(Integer status, String msg, long timpeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timpeStamp = timpeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getTimpeStamp() {
		return timpeStamp;
	}

	public void setTimpeStamp(long timpeStamp) {
		this.timpeStamp = timpeStamp;
	}
	
	
	
}
