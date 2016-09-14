/*******************************************************************************
 * Copyright (c) 2015 Michael Koch - Cooperation Systems Center Munich (CSCM).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Michael Koch - Initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.source.researchgate;

import java.util.Date;
import java.util.List;

import org.osgi.service.log.LogService;
import org.sociotech.communitymashup.application.Source;
import org.sociotech.communitymashup.data.DataSet;
import org.sociotech.communitymashup.data.Person;
import org.sociotech.communitymashup.source.impl.SourceServiceFacadeImpl;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.ResearchGateAPIWrapper;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.items.Attribute;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.items.Node;
import org.sociotech.communitymashup.source.researchgate.properties.ResearchGateProperties;
import org.sociotech.communitymashup.source.researchgate.transformation.ResearchGateTransformation;


/**
 * This is the main class of the MediaTUM source service for loading reference and project
 * information from a MediaTUM server.
 * 
 * @author Michael Koch
 */
public class ResearchGateSourceService extends SourceServiceFacadeImpl {

	/**
	 * Transformation to CommunityMashup objects.
	 */
	private ResearchGateTransformation transformation;
	
	/**
	 * Reference to the API.
	 */
	private ResearchGateAPIWrapper api;
	
	/**
	 * Date of last update
	 */
	private Date lastUpdated;
	

	/* (non-Javadoc)
	 * @see org.sociotech.communitymashup.source.impl.SourceServiceFacadeImpl#initialize(org.sociotech.communitymashup.application.Source)
	 */
	@Override
	public boolean initialize(Source configuration) {
		
		boolean initialized = super.initialize(configuration);
		
		if(initialized)
		{
			// get api base url from configuration
			String baseUrl = source.getPropertyValueElseDefault(ResearchGateProperties.API_URL_PROPERTY,ResearchGateProperties.API_URL_PROPERTY_DEFAULT);
			
			// get api cache file prefix from configuration
			// just for debugging purposes
			String cacheFilePrefix = source.getPropertyValueElseDefault(ResearchGateProperties.API_CACHE_FILE_PREFIX_PROPERTY,null);						
			
			// create api wrapper
			api = new ResearchGateAPIWrapper(baseUrl, cacheFilePrefix, this);
			
			// create transformation
			transformation = new ResearchGateTransformation(this, api);
			
			this.setInitialized(initialized);
		}
		
		return this.isInitialized();
	}	


	/* (non-Javadoc)
	 * @see org.sociotech.communitymashup.source.impl.SourceServiceFacadeImpl#fillDataSet(org.sociotech.communitymashup.data.DataSet)
	 */
	@Override
	public void fillDataSet(DataSet dataSet) {
		
		super.fillDataSet(dataSet);
		
		log("fillDataSet ...", LogService.LOG_DEBUG);
		
		// get Persons and References from API 
		List list = api.getAllObjects();
		if (list == null) {
			log("Could not get data from MediaTUM", LogService.LOG_ERROR);
			return;
		}
		lastUpdated = new Date();
				
		log("Got "+list.size()+" objects from MediaTUM", LogService.LOG_DEBUG);
		
		try {
			// transform and add result
			transformation.transformAndAddObjects(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
						
	}
	
	
	/* (non-Javadoc)
	 * @see org.sociotech.communitymashup.source.impl.SourceServiceFacadeImpl#updateDataSet()
	 */
	@Override
	protected void updateDataSet() {
		
		super.updateDataSet();
		
		log("updateDataSet ...", LogService.LOG_DEBUG);
		// do not update if lastUpdated < 1 day
		if (lastUpdated.getTime() + 24L*60L*60L*1000L > (new Date()).getTime()) 
			return;
		
		// get Persons and References from API 
		List list = api.getAllObjects();
		if (list == null) {
			log("Could not get data from MediaTUM", LogService.LOG_ERROR);
			return;
		}
				
		log("Got "+list.size()+" objects from MediaTUM", LogService.LOG_DEBUG);
		
		try {
			// transform and add result
			transformation.transformAndAddObjects(list, lastUpdated);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}