
package org.sociotech.mashupsync.gui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Calendar;

import org.eclipse.swt.widgets.Group;

import javax.xml.bind.JAXBException;

import org.eclipse.swt.SWT;import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.sociotech.mashupsync.MashupSyncFacade;
import org.sociotech.mashupsync.SyncConfiguration;
import org.sociotech.mashupsync.exceptions.*;
import org.eclipse.swt.widgets.List;


public class MainWindow implements ProgressListener {
	private Shell shell;
	private Display display;
	private MashupSyncFacade facade;
	private int itemsProcessed;
	private int itemsTotal;
	
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
	
    public MainWindow(MashupSyncFacade facade_) {
        this.display = new Display();
        this.shell = new Shell(display);
        
        final MashupSyncFacade facade = facade_;
        final Button btnSynchronize, btnAddSource, btnDeleteSources, btnRadioComprehensive, btnRadioPersonal, btnLoad;
        final List sourcesList;
        final Group modeGrp, sourcesGrp, nameGrp, minYearGrp;
        final Composite sourcesGrpButtons;
        final Label lbl1, lbl2;
        final Text txtUrl, txtSource, txtFirstName, txtLastName, txtMinYear;
        
        shell.setMinimumSize(new Point(500, 570));
        shell.setText("CommunityMashup Synchronisation");
        RowLayout layout = new RowLayout();
        layout.marginLeft = layout.marginTop = 15;
        layout.type = SWT.VERTICAL;
        layout.wrap = true;
        shell.setLayout(layout);
        
        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setText("Basis-URL des CommunityMashups");
        txtUrl = new Text(shell, SWT.BORDER);
        txtUrl.setEditable(true);
        txtUrl.setLayoutData(new RowData(414, SWT.DEFAULT));
        txtUrl.setText("http://localhost:8081/mashup");
        btnLoad = new Button(shell, SWT.PUSH);
        btnLoad.setLayoutData(new RowData(134, SWT.DEFAULT));
        
        
        sourcesGrp = new Group(shell, SWT.SHADOW_IN);
        sourcesGrp.setLayoutData(new RowData(430, SWT.DEFAULT));
        sourcesGrp.setLayout(new RowLayout(SWT.VERTICAL));
        sourcesGrp.setText("Meta-Tags der Datenquellen");
        sourcesGrp.setVisible(false);
        
        
        txtSource = new Text(sourcesGrp, SWT.PUSH | SWT.BORDER);
        txtSource.setEditable(true);
        txtSource.setText("");
        txtSource.setLayoutData(new RowData(414, 20));
        
        RowLayout rl1 = new RowLayout(SWT.HORIZONTAL);
        rl1.marginLeft = 0;
        rl1.marginTop = 0;
        sourcesGrpButtons = new Composite(sourcesGrp, SWT.NONE);
        sourcesGrpButtons.setLayout(rl1);      
        
        
        btnAddSource = new Button(sourcesGrpButtons, SWT.NONE);
        btnAddSource.setText("Hinzufügen");        
        
        btnDeleteSources = new Button(sourcesGrpButtons, SWT.NONE);
        btnDeleteSources.setText("Markierte löschen");    
        btnDeleteSources.setEnabled(false);
        
        sourcesList = new List(sourcesGrp, SWT.BORDER);
        sourcesList.setLayoutData(new RowData(422, 95));

        modeGrp = new Group(shell, SWT.SHADOW_IN);
        modeGrp.setLayoutData(new RowData(430, SWT.DEFAULT));
        modeGrp.setText("Modus");
        modeGrp.setLayout(new RowLayout(SWT.HORIZONTAL));
        modeGrp.setVisible(false);
        
        btnRadioComprehensive = new Button(modeGrp, SWT.RADIO);
        btnRadioComprehensive.setText("Personenübergreifend");
        
        btnRadioPersonal = new Button(modeGrp, SWT.RADIO);
        btnRadioPersonal.setSelection(true);
        btnRadioPersonal.setText("Persönlich");           
        
        nameGrp = new Group(shell, SWT.SHADOW_IN);
        nameGrp.setLayoutData(new RowData(430, SWT.DEFAULT));
        nameGrp.setText("Zu suchender Autorenname");
        nameGrp.setLayout(new RowLayout(SWT.VERTICAL));
        
        minYearGrp = new Group(shell, SWT.SHADOW_IN);
        minYearGrp.setLayoutData(new RowData(430, SWT.DEFAULT));
        minYearGrp.setText("Berücksichtige Publikationen ab dem Jahr");
        minYearGrp.setLayout(new RowLayout(SWT.VERTICAL));
        minYearGrp.setVisible(false);
        
        txtMinYear = new Text(minYearGrp, SWT.BORDER);
        txtMinYear.setLayoutData(new RowData(414, SWT.DEFAULT));
        txtMinYear.setText("2012");

        

        lbl1 = new Label(nameGrp, SWT.NONE);
        lbl1.setText("Vorname:");
        txtFirstName = new Text(nameGrp, SWT.BORDER);
        txtFirstName.setLayoutData(new RowData(414, SWT.DEFAULT));
        lbl2 = new Label(nameGrp, SWT.NONE);
        lbl2.setText("Nachname:");
        txtLastName = new Text(nameGrp, SWT.BORDER);
        txtLastName.setLayoutData(new RowData(414, SWT.DEFAULT));

        btnSynchronize = new Button(shell, SWT.NONE);
        btnSynchronize.setText("Synchronisieren");

        SelectionAdapter radioSelAdapter = new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		nameGrp.setEnabled(btnRadioPersonal.getSelection());
        		txtFirstName.setEnabled(btnRadioPersonal.getSelection());
        		txtLastName.setEnabled(btnRadioPersonal.getSelection());
        		nameGrp.setEnabled(btnRadioPersonal.getSelection());
        		lbl1.setEnabled(btnRadioPersonal.getSelection());
        		lbl2.setEnabled(btnRadioPersonal.getSelection());
        	}
        };
    
        btnRadioPersonal.addSelectionListener(radioSelAdapter);
        btnRadioComprehensive.addSelectionListener(radioSelAdapter);
        
        sourcesList.setVisible(false);
        btnSynchronize.setVisible(false);        
        nameGrp.setVisible(false);
        btnLoad.setText("Laden");             
        
        btnLoad.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		btnLoad.setEnabled(false);
        		btnLoad.setText("Verbinden...");
        		final String input = txtUrl.getText();
        		
    			new Thread() {
    				public void run() {
    					try {
    	            		facade.init(input);
    	            		
    	            		display.syncExec(new Runnable() {
    	            			public void run() {
    		            		    sourcesGrp.setVisible(true);
    	    	                    sourcesList.setVisible(true);
    	    	                    modeGrp.setVisible(true);
    	    	                    btnSynchronize.setVisible(true);  
    	    	                    nameGrp.setVisible(true);
    	    	                    btnLoad.setEnabled(true);
    	    	                    minYearGrp.setVisible(true);

    	    	    		        btnLoad.setText("Laden");
    	    	    		        btnLoad.setEnabled(true);
    	            			}
    	            		});
    	            		
    	        		} catch(Exception ex) {   	   	        			
    	    		        if(ex instanceof MalformedURLException)
    	    		        	displayError("Die angegebene URL ist ungültig.");
    	    		        else if(ex instanceof IOException)
    	    		        	displayError("Die angegebene URL ist nicht erreichbar.");
    	    		        else if(ex instanceof JAXBException)
    	    		        	displayError("Unerwartetes Datenformat.");
    	    		        else if(ex instanceof EmptyMashupException)
    	    		        	displayError("Das CommunityMashup enthält keine Inhalte bzw. ist nicht bereit.");
    	    		        else
    	    		        	displayError(ex.getClass().getName() + " beim Verarbeiten der Daten.");
    	    		            	    		        
    	    		        ex.printStackTrace();
    	    		        
    	    		        display.syncExec(new Runnable() {
    	            			public void run() {
    	            				sourcesGrp.setVisible(false);
    	    	                    sourcesList.setVisible(false);
    	    	                    modeGrp.setVisible(false);
    	    	                    btnSynchronize.setVisible(false);  
    	    	                    nameGrp.setVisible(false);
    	    	                    btnLoad.setEnabled(false);
    	    	                    minYearGrp.setVisible(false);

    	    	    		        btnLoad.setText("Laden");
    	    	    		        btnLoad.setEnabled(true);
    	    	    		        
    	    	    		        btnLoad.setText("Laden");
    	    	    		        btnLoad.setEnabled(true);
    	            			}
    	            		});
    	        		}
    				}
    			}.start();
        	}
        });
        
        btnAddSource.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		String input = txtSource.getText().trim();
        		java.util.List<String> selection = Arrays.asList(sourcesList.getItems());
        		
        		if(input.length() == 0) {
        			displayError("Der Name des Source-Tags darf nicht leer sein.");
        			return;
        		} else if(selection.contains(input)) {
        			displayError("Der angegebene Source-Tag ist bereits enthalten.");
        			return;
        		} else {
        			sourcesList.add(input);
        			sourcesList.select(sourcesList.getSelectionCount() - 1);
        			txtSource.setText("");
        			btnDeleteSources.setEnabled(true);
        		}
        	}
        });
        
        sourcesList.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		btnDeleteSources.setEnabled(sourcesList.getSelectionCount() > 0);
        	}
        });
        
        btnDeleteSources.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		sourcesList.remove(sourcesList.getSelectionIndices());
        		
        		if(sourcesList.getSelectionCount() == 0)
        			btnDeleteSources.setEnabled(false);
        	}
        });
        
        btnSynchronize.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
	        	SyncConfiguration config = new SyncConfiguration();
	        	
	        	config.setFirstName(txtFirstName.getText().trim());
	        	config.setLastName(txtLastName.getText().trim());
	        	config.setSyncMode(btnRadioComprehensive.getSelection() ? SyncConfiguration.Mode.COMPREHENSIVE : SyncConfiguration.Mode.PERSONAL);
	        	config.setSourceIdentifiers(Arrays.asList(sourcesList.getItems()));
	        	
	        	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	        	
	        	try {
	        		config.setMinYear(Integer.valueOf(txtMinYear.getText()));
	        	} catch(NumberFormatException ex) {
	        		displayError("Bitte geben Sie ein gültiges Mindestjahr an.");
	        		return;
	        	}
	        	
	        	if(config.getSyncMode() == SyncConfiguration.Mode.PERSONAL && (
	        			config.getFirstName().length() == 0 || config.getLastName().length() == 0)) {
	        		displayError("Bitte geben Sie einen Vor- und Nachnamen an.");
	        		return;
	        	} else if(config.getSourceIdentifiers().size() < 2) {
	        		displayError("Bitte geben Sie mindestens zwei Source-MetaTags zur Synchronisation an.");
	        		return;
	        	} else if(config.getMinYear() < 1900 || config.getMinYear() > currentYear) {
	        		displayError("Das Mindestjahr muss zwischen 1900 und " + currentYear + " liegen.");
	        		return;
	        	}
	        	
	        	facade.synchronize(config);
	        			
        	}
        });
        
        shell.pack();       
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }

	@Override
	public int getItemsProcessed() {
		return this.itemsProcessed;
	}

	@Override
	public int getItemsTotal() {
		return this.itemsTotal;
	}

	@Override
	public void reportProgress(int itemsProcessed, int itemsTotal) {
		this.itemsProcessed = itemsProcessed;
		this.itemsTotal = itemsTotal;
		
	}
}
