/*******************************************************************************
 * Copyright (c) 2013 Peter Lachenmaier.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Peter Lachenmaier - Design and initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.framework.java.exceptions;

/**
 * Exception indicating an error while connecting to a remote CommunityMashup.
 * 
 * @author Peter Lachenmaier
 */
public class MashupConnectionException extends Exception {

	/**
	 * The generated serial version uid.
	 */
	private static final long serialVersionUID = -6986032126519446575L;

	/**
	 * The url tried to connect when the error appears.
	 */
	private final String connectionUrl;

	/**
	 * If Exception is based on a previous exception it will be linked in this field. 
	 */
	private Exception origException = null;
	
	/**
	 * Creates a new mashup exception indicating an error with the connection to
	 * a remote CommunityMashup.
	 * 
	 * @param message
	 *            Message describing the error
	 * @param connectionUrl
	 *            Url tried to connect.
	 */
	public MashupConnectionException(String message, String connectionUrl) {
		super(message);
		this.connectionUrl = connectionUrl;
	}
	
	/**
	 * Creates a new mashup exception indicating an error with the connection to
	 * a remote CommunityMashup. Keeps the original exception and uses its message.
	 * 
	 * @param originalException Exception, this exception is based on.
	 * @param connectionUrl Url tried to connect to.
	 */
	public MashupConnectionException(Exception originalException, String connectionUrl) {
		this(originalException.getMessage(), connectionUrl);
		this.origException = originalException;
	}

	/**
	 * Returns the url tried to connect when the error appeared.
	 * 
	 * @return The url tried to connect when the error appeared.
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * If this exception is based on another exception, the original exception will be returned.
	 * 
	 * @return The original exception or null if it does not exist.
	 */
	public Exception getOriginalException() {
		return origException;
	}

}
