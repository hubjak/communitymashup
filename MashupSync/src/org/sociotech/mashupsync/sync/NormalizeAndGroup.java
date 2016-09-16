package org.sociotech.mashupsync.sync;

import java.util.HashMap;
import java.util.List;

import org.sociotech.mashupsync.SyncConfiguration;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.gui.ProgressListener;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;
import org.sociotech.mashupsync.normalization.DefaultNormalizer;
import org.sociotech.mashupsync.normalization.NormalizationMethod;

public class NormalizeAndGroup implements SyncMethod {
	private ProgressListener progressListener;
	private NormalizationMethod normalizer;

	public NormalizeAndGroup() {
		this.normalizer = new DefaultNormalizer();
	}

	@Override
	public SyncResult synchronize(
			HashMap<String, LiteratureReference> contents,
			HashMap<String, List<LiteratureReference>> sources,
			SyncConfiguration config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SyncResult synchronize(
			HashMap<String, LiteratureReference> contents,
			HashMap<String, List<LiteratureReference>> sources,
			SyncConfiguration config, ProgressListener progressListener) {
		this.progressListener = progressListener;
		
		return this.synchronize(contents, sources, config);
	}
	
}
