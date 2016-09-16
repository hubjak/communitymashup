
package org.sociotech.mashupsync.gui;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.data.SyncResultEntry;
import org.sociotech.mashupsync.data.SyncResultEntryInsufficient;
import org.sociotech.mashupsync.data.SyncResultEntryMissing;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;


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
	
	public static void main(String[] args) {
		new ResultWindow(null, new Display());
	}
	
    public ResultWindow(SyncResult result, Display display) {
        this.display = display;
        this.shell = new Shell(display);
        shell.setSize(377, 401);
        shell.setLayout(new GridLayout(2, false));
        
        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.setText("New Button");
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        new Label(shell, SWT.NONE);
        
        HashMap<String, TreeItem> treeItemMapping = new HashMap<>();
        
        Tree tree = new Tree(shell, SWT.BORDER);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 4));
        
        TreeItem warnings = new TreeItem(tree, 0);
        TreeItem misses = new TreeItem(tree, 0);
        
        warnings.setText("Unvollständige Einträge (Autoren fehlen)");
        misses.setText("Fehlende Einträge");
        
        for(SyncResultEntry e : result.getWarnings()) {
        	if(e instanceof SyncResultEntryMissing) {
        		SyncResultEntryMissing missing = (SyncResultEntryMissing) e;
        		
        		TreeItem parent = treeItemMapping.get("m_" + missing.getSource());
        		
        		if(parent == null) {
        			treeItemMapping.put("m_" + missing.getSource(), new TreeItem(misses, 0));
        			parent = treeItemMapping.get("m_" + missing.getSource());
        			parent.setText(missing.getSource());
        		}
        		
        		new TreeItem(parent, 0).setText(missing.getReference().toString());
        	} else if(e instanceof SyncResultEntryInsufficient) {
        		SyncResultEntryInsufficient insufficient = (SyncResultEntryInsufficient) e;
        		
        		TreeItem parent = treeItemMapping.get("i_" + insufficient.getSource());
        		
        		if(parent == null) {
        			treeItemMapping.put("i_" + insufficient.getSource(), new TreeItem(warnings, 0));
        			parent = treeItemMapping.get("i_" + insufficient.getSource());
        			parent.setText(insufficient.getSource());
        		}
        		
        		new TreeItem(parent, 0).setText(insufficient.getReference().toString());
        	}
        }       
        
        shell.pack();       
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
	}
}
