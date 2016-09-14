package org.sociotech.communitymashup.source.researchgate;

import org.osgi.framework.BundleContext;
import org.sociotech.communitymashup.source.impl.SourceBundleActivator;

public class ResearchGateSourceBundleActivator extends SourceBundleActivator {


	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		super.start(bundleContext, new ResearchGateSourceInstantiationService());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		super.stop(bundleContext);
	}

}
