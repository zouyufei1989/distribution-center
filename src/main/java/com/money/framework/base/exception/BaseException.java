package com.money.framework.base.exception;

public class BaseException extends Exception{

	private String msg;
	private static final long serialVersionUID = -2473591901715155353L;
	
	public BaseException(String msg){
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg(){
		return msg;
	}
}
