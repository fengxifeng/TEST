/*
 *The code is written by 51jiecai.com.
 *All rights reserved.
 */
package com.vwmam.eventm.result;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 *@author kenny
 * Created on 2014年11月27日
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean status;

	private String message;

	private T data;

	public ResultModel(){

	}

	
	
	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public T getData() {
		return data;
	}



	public void setData(T data) {
		this.data = data;
	}



	public ResultModel(boolean status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}



	@Override
	public String toString() {
		return "ResultModel [status=" + status + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
