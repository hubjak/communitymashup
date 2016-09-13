package org.sociotech.mashupsync;

import java.util.LinkedList;
import java.util.List;

public class SyncConfiguration {
	public enum Mode {
		PERSONAL, COMPREHENSIVE
	};
	
	private Mode syncMode;
	private String firstName;
	private String lastName;
	private List<String> sourceIdentifiers;
	
	public SyncConfiguration() {
		this.sourceIdentifiers = new LinkedList<String>();
	}
	
	public List<String> getSourceIdentifiers() {
		return this.sourceIdentifiers;
	}
	
	public void addSourceIdentifier(String sourceIdentifier) {
		if(!this.sourceIdentifiers.contains(sourceIdentifier))
			this.sourceIdentifiers.add(sourceIdentifier);
	}
	
	public Mode getSyncMode() {
		return syncMode;
	}
	
	public boolean containsSource(String sourceIdentifier) {
		return this.sourceIdentifiers.contains(sourceIdentifier);
	}
	
	public void setSyncMode(Mode sync_mode) {
		this.syncMode = sync_mode;
	}
	public void setSourceIdentifiers(List<String> sourceIdentifiers) {
		this.sourceIdentifiers = sourceIdentifiers;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
