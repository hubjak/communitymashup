/*******************************************************************************
 * Copyright (c) 2013 Peter Lachenmaier - Cooperation Systems Center Munich (CSCM).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Peter Lachenmaier - Design and initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.source.yammer.adapter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.sociotech.communitymashup.data.ThumbRanking;
import org.sociotech.communitymashup.source.yammer.YammerSourceService;

public class NewThumbAdapter extends AdapterImpl {
	private YammerSourceService yammerSource;
	
	public NewThumbAdapter(YammerSourceService yammerSource) {
		this.yammerSource = yammerSource;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification notification) {
		if(notification != null && (notification.getNewValue() instanceof ThumbRanking) && notification.getEventType() == Notification.ADD)
		{
			yammerSource.writeThumbRanking((ThumbRanking) notification.getNewValue());
		}
	}
}
