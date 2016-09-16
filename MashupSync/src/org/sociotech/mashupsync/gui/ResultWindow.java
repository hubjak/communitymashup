
package org.sociotech.mashupsync.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.data.SyncResultEntry;


public class ResultWindow {
	private Shell shell;
	private Display display;
	
	private void displayError(final String errorMsg) {
		display.syncExec(new Runnable() {
			public void run() {				
				MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
		        
		        messageBox.setText("Fehler");
		        messageBox.setMessage(errorMsg);
		        messageBox.open();
			}
		});
	}
	
    public ResultWindow(SyncResult result, Display display) {
        this.display = display;
        this.shell = new Shell(display);
        
        for(SyncResultEntry e : result.getWarnings()) {
        	System.out.println(e);
        }
       
	}
}
