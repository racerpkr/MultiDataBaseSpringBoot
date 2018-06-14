package com.multidatabase.response;

public class ResultResponseObject {
	
	private String responsee;
	private Object result;
	private int code;
	private String message;
	
	public String getResponsee() {
		return responsee;
	}
	public void setResponsee(String responsee) {
		this.responsee = responsee;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
