package com.vbkn.titan.exception;

/**
 * @author Valter Bruno Konrad Neto
 */
public class PropertiesUtilException extends Exception {
	
	private static final long serialVersionUID = 6599944061174508056L;

	public PropertiesUtilException(String msg){
		super(msg);
	}
	
	public PropertiesUtilException(String msg, Exception e){
		super(msg, e);
	}

}
