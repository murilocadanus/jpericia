package com.vbkn.titan.ejb.exception;

public class DAOException extends Exception {
	
	private static final long serialVersionUID = 1191593460892772403L;

	public DAOException(String msg){
		super(msg);
	}
	
	public DAOException(Exception e){
		super(e);
	}

	public DAOException(String msg, Exception e){
		super(msg, e);
	}
	
}

