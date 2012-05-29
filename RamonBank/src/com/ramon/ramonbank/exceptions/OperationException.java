package com.ramon.ramonbank.exceptions;

public class OperationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OperationException() {
	}

	/**
	 * @param message
	 */
	public OperationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OperationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
