package org.jpericia.ejb.exception;

public class BusinessObjectException extends Exception {

	private static final long serialVersionUID = 8044969379404605510L;

	public BusinessObjectException(String msg){
		super(msg);
	}
	
	public BusinessObjectException(Exception e){
		super(e);
	}
	
	public BusinessObjectException(String msg, Exception e){
		super(msg, e);
	}

}

