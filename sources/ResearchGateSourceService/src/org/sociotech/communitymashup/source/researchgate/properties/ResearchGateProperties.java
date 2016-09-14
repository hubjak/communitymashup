
package org.sociotech.communitymashup.source.researchgate.properties;

/**
 * This class contains string constants for properties used by the ResearchGate source service.
 * 
 * @author Jakob Huber
 */
public class ResearchGateProperties {

	public static final String API_URL_PROPERTY		= "baseUrl";
	public static final String API_URL_PROPERTY_DEFAULT = "https://www.researchgate.net/";

	public static final String API_CACHE_FILE_PREFIX_PROPERTY	= "apiCacheFilePrefix";
	

	/**
	 * If this property is set to true persons for document authors will be created. 
	 */
	public static final String CREATE_AUTHOR_PERSONS_PROPERTY 	= "createAuthorPersons";
	public static final String CREATE_AUTHOR_PERSONS_DEFAULT 	= "true";
	
	/**
	 * If this property is set to true persons for document editors will be created. 
	 */
	public static final String CREATE_EDITOR_PERSONS_PROPERTY 	= "createEditorPersons";
	public static final String CREATE_EDITOR_PERSONS_DEFAULT 	= "true";
	
	
	/**
	 * A comma-separated list of ResearchGate department IDs (researchgate.net/institution/***)
	 * whose members should be searched for publications.
	 */
	public static final String INCLUDE_DEPARTMENTS_PROPERTY = "includeDepartments";
	public static final String INCLUDE_DEPARTMENTS_DEFAULT = "";
	
	
	/**
	 * A comma-separated list of ResearchGate user IDs (researchgate.net/profile/***)
	 * to include apart from those who are members in one of the specified departments.
	 */
	public static final String INCLUDE_PERSONS_PROPERTY = "includePersons";
	public static final String INCLUDE_PERSONS_DEFAULT = "";
	
	
	/**
	 * If this property is set only objects with year => minYear will be imported. 
	 */
	public static final String MIN_YEAR_PROPERTY 	= "minYear";
	public static final String MIN_YEAR_DEFAULT 	= "";
	
	/**
	 * If this property is set to true organizations will be added as organization objects.
	 */
	public static final String ADD_INSTITUTE_AS_TAG_PROPERTY 	= "addInstituteAsTag";
	public static final String ADD_INSTITUTE_AS_TAG_DEFAULT 	= "false";
	
	/**
	 * If this property is set to true organizations will be added as organization objects.
	 */
	public static final String ADD_ORGANIZATIONS_PROPERTY 	= "addOrganizations";
	public static final String ADD_ORGANIZATIONS_DEFAULT 	= "false";
}