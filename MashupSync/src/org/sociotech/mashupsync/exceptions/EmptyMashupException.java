package org.sociotech.mashupsync.exceptions;

public class EmptyMashupException extends Exception {
	public EmptyMashupException(String msg) {
		super(msg);
	}
	
	public EmptyMashupException() {
		super();
	}
}
