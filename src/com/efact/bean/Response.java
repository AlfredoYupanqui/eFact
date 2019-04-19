package com.efact.bean;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public int errorCode;
	public String message;
	public Boolean status;
	public List<?> objectList;
	public Object object;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public List<?> getObjectList() {
		return objectList;
	}
	public void setObjectList(List<?> objectList) {
		this.objectList = objectList;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
