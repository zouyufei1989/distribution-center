package com.money.framework.base.exception;

public class BaseException extends Exception{

	private String msg;

	public BaseException(String msg){
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg(){
		return msg;
	}
}
