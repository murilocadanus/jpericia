package com.vbkn.titan.ejb.exception;

public class TitanDAOFactoryException extends Exception {
	
	private static final long serialVersionUID = 1191593460892772403L;

	public TitanDAOFactoryException(String msg){
		super(msg);
	}
	
	public TitanDAOFactoryException(Exception e){
		super(e);
	}
	
	public TitanDAOFactoryException(String msg, Exception e){
		super(msg, e);
	}

}

