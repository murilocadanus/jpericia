package com.vbkn.titan.exception;

/**
 * @author Valter Bruno Konrad Neto
 */
public class BusinessDelegateException extends Exception {
	
	private static final long serialVersionUID = 6691744088391121279L;

	public BusinessDelegateException(String msg){
		super(msg);
	}
	
	public BusinessDelegateException(Exception e){
		super(e);
	}
	
	public BusinessDelegateException(String msg, Exception e){
		super(msg, e);
	}

}
