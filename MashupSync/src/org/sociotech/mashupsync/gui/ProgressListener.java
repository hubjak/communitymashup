package org.sociotech.mashupsync.gui;

public interface ProgressListener {
	
	public int getItemsProcessed();
	public int getItemsTotal();
	
	public void reportProgress(int itemsProcessed, int itemsTotal);
	
}
