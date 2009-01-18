package com.vbkn.titan.ejb.exception;

public class SessionFacadeException extends Exception {
	
	private static final long serialVersionUID = 1843262896869914149L;

	public SessionFacadeException(String msg){
		super(msg);
	}
	
	public SessionFacadeException(Exception e){
		super(e);
	}
	
	public SessionFacadeException(String msg, Exception e){
		super(msg, e);
	}

}

