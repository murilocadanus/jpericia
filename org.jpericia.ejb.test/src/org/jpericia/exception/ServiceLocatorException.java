package org.jpericia.exception;

/**
 * @author Marlus Cadanus da Costa
 */
public class ServiceLocatorException extends Exception {
	
	private static final long serialVersionUID = -5556668570395802232L;

	public ServiceLocatorException(String msg){
		super(msg);
	}

	public ServiceLocatorException(String msg, Exception e){
		super(msg, e);
	}
	
}
