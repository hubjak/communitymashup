
package org.sociotech.mashupsync.gui;

import org.eclipse.swt.widgets.FileDialog;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.data.SyncResultEntry;
import org.sociotech.mashupsync.data.SyncResultEntryInsufficient;
import org.sociotech.mashupsync.data.SyncResultEntryMissing;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class ResultWindow {
	private Shell shell;
	private Display display;
	
	// for testing only
	/*public static void main(String[] args) {
		new ResultWindow(null, new Display());
	}*/
	
    public ResultWindow(final SyncResult result, Display display) {
        this.display = display;
        this.shell = new Shell(display);
        shell.setText("Ergebnisse der Synchronisation");
        shell.setSize(377, 401);
        shell.setLayout(new GridLayout(2, false));
        
        final FileDialog fileDialog = new FileDialog(shell);
        fileDialog.setFileName("sync_results.txt");
        fileDialog.setOverwrite(true);
        fileDialog.setText("Speicherort angeben");
        
        Group grpExport = new Group(shell, SWT.NONE);
        grpExport.setLayout(new RowLayout(SWT.HORIZONTAL));
        grpExport.setText("Exportieren als");
        
        Button btnExportCSV = new Button(grpExport, SWT.DEFAULT);
        btnExportCSV.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		String filename = fileDialog.open();
        		
        		writeFile(filename, result.toCSV());
        	}
        });
        
        btnExportCSV.setText("CSV");
        
        Button btnExportXML = new Button(grpExport, SWT.DEFAULT);
        btnExportXML.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		String filename = fileDialog.open();
        		
        		writeFile(filename, result.toXML());
        	}
        });
        btnExportXML.setText("XML");
        
        HashMap<String, TreeItem> treeItemMapping = new HashMap<>();
        new Label(shell, SWT.NONE);
        
        Tree tree = new Tree(shell, SWT.BORDER);
        tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 4, 4));
        
        TreeItem warnings = new TreeItem(tree, 0);
        TreeItem misses = new TreeItem(tree, 0);
        
        TreeColumn col1 = new TreeColumn(tree, SWT.LEFT);
        col1.setText("Autoren");
        TreeColumn col2 = new TreeColumn(tree, SWT.LEFT);
        col2.setText("Titel");
        TreeColumn col3 = new TreeColumn(tree, SWT.CENTER);
        col3.setText("Jahr");
        
        int countMisses = 0, countWarnings = 0;	
       
        for(SyncResultEntry e : result.getWarnings()) {
        	if(e instanceof SyncResultEntryMissing) {
        		SyncResultEntryMissing missing = (SyncResultEntryMissing) e;
        		
        		TreeItem parent = treeItemMapping.get("m_" + missing.getSource());
        		
        		if(parent == null) {
        			treeItemMapping.put("m_" + missing.getSource(), new TreeItem(misses, 0));
        			parent = treeItemMapping.get("m_" + missing.getSource());
        			parent.setText(new String[] {missing.getSource(), "", ""});
        		}
        		
        		countMisses++;
        		new TreeItem(parent, 0).setText(new String[] {missing.getReference().getAuthorsString(), 
        				missing.getReference().getTitle(), String.valueOf(missing.getReference().getYear())});
        	} else if(e instanceof SyncResultEntryInsufficient) {
        		SyncResultEntryInsufficient insufficient = (SyncResultEntryInsufficient) e;
        		
        		TreeItem parent = treeItemMapping.get("i_" + insufficient.getSource());
        		
        		if(parent == null) {
        			treeItemMapping.put("i_" + insufficient.getSource(), new TreeItem(warnings, 0));
        			parent = treeItemMapping.get("i_" + insufficient.getSource());
        			parent.setText(new String[] {insufficient.getSource(), "", ""});
        		}
        		
        		countWarnings++;
        		new TreeItem(parent, 0).setText(new String[] {insufficient.getReference().getAuthorsString(), 
        				insufficient.getReference().getTitle(), String.valueOf(insufficient.getReference().getYear())});
        	}
        }       
        

        warnings.setText(new String[] {"Unvollständige Einträge/Fehlende Autoren (" + countWarnings + ")", "", ""});
        misses.setText(new String[] {"Fehlende Einträge (" + countMisses + ")", "", ""});

        tree.setHeaderVisible(true);
        col1.setResizable(true);
        col2.setResizable(true);
        col3.setResizable(true);
        col1.pack();
        col2.pack();
        col3.pack();
        tree.pack();
        
        shell.pack();       
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
	}
    
    public static boolean writeFile(String filename, String content) {
    	try {
    		FileWriter w = new FileWriter(filename);
    		w.write(content);
    		w.close();
    		return true;
		} catch(IOException e) {
			return false;
		}
    }
}
