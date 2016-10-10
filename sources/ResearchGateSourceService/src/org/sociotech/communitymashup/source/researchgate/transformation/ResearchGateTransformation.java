package org.sociotech.communitymashup.source.researchgate.transformation;

import java.net.CookieManager;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;

import org.osgi.service.log.LogService;
import org.sociotech.communitymashup.data.Content;
import org.sociotech.communitymashup.data.DataFactory;
import org.sociotech.communitymashup.data.Person;
import org.sociotech.communitymashup.source.researchgate.ResearchGateSourceService;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.ResearchGateAPIWrapper;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.items.Author;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.items.Publication;
import org.sociotech.communitymashup.source.researchgate.meta.ResearchGateTags;
import org.sociotech.communitymashup.source.researchgate.properties.ResearchGateProperties;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * Transforms results from the ResearchGate api wrapper into CommunityMashup objects.
 * 
 * @author Jakob Huber
 *
 */
public class ResearchGateTransformation {

	/**
	 * Reference to the ResearchGate source service, used e. g. for logging 
	 */
	private ResearchGateSourceService source = null;
	
	/**
	 * Factory for creating new CommunityMashup objects
	 */
	private DataFactory factory = DataFactory.eINSTANCE;
	
	/**
	 * Reference to the ResearchGate api to possible get additional information 
	 */
	private ResearchGateAPIWrapper api = null;
	
	
	private CookieManager cookieManager = new CookieManager();
	
	/**
	 * Creates a new ResearchGate transformation.
	 * 
	 * @param source Source service used for logging and data adding
	 * @param api Reference to the api wrapper
	 */
	public ResearchGateTransformation(ResearchGateSourceService source, ResearchGateAPIWrapper api) {
		this.source = source;
		this.api = api;
		java.net.CookieHandler.setDefault(cookieManager);
	}
	
	/**
	 * Gathers all user IDs to search for contents and transforms them into CommunityMashup objects.
	 * 
	 * @param users Array of user IDs to search for publications
	 * @param departments Array of department IDs to search for users which should be searched for publications
	 * @param lastUpdated Date of last update (so only recently updated objects are added)
	 * 
	 * if configuration says to create author/editor then will create
	 */
	public void addUsersAndPublications(String[] users, String[] departments) {
		HashSet<String> usersToAdd = new HashSet<>();
		
		LinkedList<Publication> publications = new LinkedList<>();
		if(users.length > 0 && users[0].length() > 0)
			usersToAdd.addAll(Arrays.asList(users));
		
		if(departments.length > 0 && departments[0].length() > 0)
			for(String department : Arrays.asList(departments)) {
				String[] ins_dep = department.split("/");
				source.log("Searching for users in department " + ins_dep[0] + " / " + ins_dep[1], LogService.LOG_DEBUG);
				usersToAdd.addAll(api.getAuthorsFromDepartment(ins_dep[0], ins_dep[1]));
			}		

		source.log("Found a total of " + usersToAdd.size() + " distinct users.", LogService.LOG_DEBUG);
		
		for(String user : usersToAdd) {
			if(user.trim().length() == 0) continue;
			
			source.log("Now processing user " + user, LogService.LOG_DEBUG);
			publications.addAll(api.getPublicationsFromUser(user));
		}
		
		
		// go through all objects retrieved from ResearchGate
		for (Publication p : publications){			
			// check if document already exists
			if(source.getContentWithSourceIdent(p.getId()) != null || p.getCreationDate() == null)
				continue;
			
			// citaiton object
			LiteratureReference ref = new LiteratureReference();
			
			// call the factory to create a new object
			Content content = factory.createContent();
			content = source.add(content, p.getId());

			if (content == null) {
				source.log("Could not add document to source", LogService.LOG_WARNING);
				return;
			}
						
			content.metaTag(ResearchGateTags.RESEARCHGATE);
			content.metaTag(p.getType());
			content.setName(p.getTitle());
			
			if(p.getAbstractText() != null)
				content.setStringValue(p.getAbstractText());
			
			
			ref.setTitle(p.getTitle());
			
			Calendar c = Calendar.getInstance();
			c.setTime(p.getCreationDate());
			ref.setYear(c.get(Calendar.YEAR));
			
			boolean first = true;
			
			
			// add authors and contributors if wished
			for(Author a : p.getAuthors()) {
				ref.addAuthor(a.getFirstname(), a.getLastname());
				
				if(!shouldCreateAuthors() && first || !shouldCreateEditors() && !first) {
					first = false;
					continue;
				}
				
				Person person = createPerson(a.getFirstname(), a.getLastname(), a.getID());
				
				if(first)
					content.setAuthor(person);
				else
					content.addContributor(person);
				
				first = false;
			} 
			content.addCitation(ref.marshal());	

		    
		} // (2005, 11) (2006, 14) (2007, 12) (2008, 28) (2009, 33) (2010, 17) (2011, 20) (2012, 17) (2013, 21) (2014, 19) (2015, 16) (2016, 7)
	}
	
	/**
	 * Determines from the configuration if persons should be created for authors.
	 * 
	 * @return True if they should be created.
	 */
	private boolean shouldCreateAuthors() {
		if(source.getConfiguration().isPropertyTrue(ResearchGateProperties.CREATE_AUTHOR_PERSONS_PROPERTY))
		{
			return true;
		}
		
		if(source.getConfiguration().getProperty(ResearchGateProperties.CREATE_AUTHOR_PERSONS_PROPERTY) == null)
		{
			return new Boolean(ResearchGateProperties.CREATE_AUTHOR_PERSONS_DEFAULT);
		}
		
		return false;
	}

	/**
	 * Creates a person with the given first and lastname. (Both required)
	 * 
	 * @param firstname Firstname of the person
	 * @param lastname Lastname of the person
	 * @param id The ResearchGate identifiert of the person
	 * @return Person object with the given first and lastname
	 */
	private Person createPerson(String firstname, String lastname, String id) {
		if(firstname == null || firstname.isEmpty())
		{
			// at least a firstname is required
			return null;
		}
		
		if(firstname == null || firstname.isEmpty())
		{
			// at least a lastname is required
			return null;
		}
		
		Person p;
		
		// check if person already exists
		if(id != null && (p = source.getPersonWithSourceIdent(id)) != null) {
			return p;
		}

		// create new person
		Person person = factory.createPerson();
		
		// set the name
		person.setFirstname(firstname);
		person.setLastname(lastname);
		
		// and add it
		if(id != null)
			person = source.add(person, id);
		else
			person = source.add(person);
		
		if(person == null)
		{
			source.log("Could not add person for author " + firstname + lastname, LogService.LOG_DEBUG);
			return null;
		}
		
		// tag the person
		person.metaTag(ResearchGateTags.RESEARCHGATE);
		
		return person;
	}
	
	
	/**
	 * Determines from the configuration if persons should be created for editors.
	 * 
	 * @return True if they should be created.
	 */
	private boolean shouldCreateEditors() {
		if(source.getConfiguration().isPropertyTrue(ResearchGateProperties.CREATE_EDITOR_PERSONS_PROPERTY))
		{
			return true;
		}
		
		if(source.getConfiguration().getProperty(ResearchGateProperties.CREATE_EDITOR_PERSONS_PROPERTY) == null)
		{
			return new Boolean(ResearchGateProperties.CREATE_EDITOR_PERSONS_DEFAULT);
		}
		
		return false;
	}
}
