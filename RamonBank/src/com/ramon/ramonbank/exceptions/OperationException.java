package com.ramon.ramonbank.exceptions;

public class OperationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OperationException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public OperationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OperationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OperationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
