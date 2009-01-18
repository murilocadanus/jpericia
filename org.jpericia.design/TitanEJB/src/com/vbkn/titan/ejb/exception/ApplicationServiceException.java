package com.vbkn.titan.ejb.exception;

public class ApplicationServiceException extends Exception {
	
	private static final long serialVersionUID = 1191593460892772403L;

	public ApplicationServiceException(String msg){
		super(msg);
	}
	
	public ApplicationServiceException(Exception e){
		super(e);
	}
	
	public ApplicationServiceException(String msg, Exception e){
		super(msg, e);
	}

}

