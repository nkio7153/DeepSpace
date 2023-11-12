package com.depthspace.utils.commonDto;

public class ResultResponse<T> {
    private Integer code =200;
    private  T message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public T getMessage() {
		return message;
	}
	public void setMessage(T message) {
		this.message = message;
	}
    
    
}
