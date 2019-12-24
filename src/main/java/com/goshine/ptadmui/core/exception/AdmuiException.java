package com.goshine.ptadmui.core.exception;

/**
 * Admui Exception
 * @author  Admui
 * @since   Admui v1.0.0
 */
@SuppressWarnings("serial")
public class AdmuiException extends Exception {
	public AdmuiException() {
		super();
	}

	public AdmuiException(String message) {
		super(message);
	}

	public AdmuiException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdmuiException(Throwable cause) {
		super(cause);
	}
}
