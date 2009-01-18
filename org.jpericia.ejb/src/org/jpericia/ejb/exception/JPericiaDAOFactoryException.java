package org.jpericia.ejb.exception;

public class JPericiaDAOFactoryException extends Exception {
	
	private static final long serialVersionUID = 1191593460892772403L;

	public JPericiaDAOFactoryException(String msg){
		super(msg);
	}
	
	public JPericiaDAOFactoryException(Exception e){
		super(e);
	}
	
	public JPericiaDAOFactoryException(String msg, Exception e){
		super(msg, e);
	}

}

