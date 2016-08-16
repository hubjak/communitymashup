/*******************************************************************************
 * Copyright (c) 2014 Peter Lachenmaier - Cooperation Systems Center Munich (CSCM).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Peter Lachenmaier - Design and initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.source.mendeley.apiwrapper.elements;

/**
 * A member in public or private group
 * 
 * @author Peter Lachenmaier
 */
public class MendeleyGroupMember extends MendeleyEntity {
	
	/**
	 * Generate serial version uid
	 */
	private static final long serialVersionUID = -2775040931032885795L;
	
	private String profile_id;
	private String joined;
	private String role;
	
	public String getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}
	public String getJoined() {
		return joined;
	}
	public void setJoined(String joined) {
		this.joined = joined;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}	
}
