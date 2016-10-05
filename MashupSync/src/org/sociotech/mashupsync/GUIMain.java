package org.sociotech.mashupsync;

import org.sociotech.mashupsync.gui.MainWindow;
import org.sociotech.mashupsync.sync.NormalizeAndGroup;

/**
 * GUI Initialization class for the CommunityMashup synchronization tool 
 * 
 * @author Jakob Huber
 *
 */

public class GUIMain {

	public static void main(String[] args) {
		MashupSyncFacade facade = new MashupSyncFacade();
		facade.setSyncMethod(new NormalizeAndGroup());
		MainWindow wnd = new MainWindow(facade);
	}

}
