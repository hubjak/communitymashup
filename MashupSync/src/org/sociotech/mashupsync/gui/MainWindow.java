
package org.sociotech.mashupsync.gui;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.sociotech.mashupsync.MashupSyncFacade;


public class MainWindow {
	private static Shell shell;
	
	private static void displayError(String errorMsg) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
        
        messageBox.setText("Fehler");
        messageBox.setMessage(errorMsg);
        messageBox.open();
	}
	
    public static void main(String[] args) {
        Display display = new Display();
        shell = new Shell(display);
        final MashupSyncFacade api = new MashupSyncFacade();
        shell.setSize(499, 528);
        shell.setText("CommunityMashup Synchronisation");
        RowLayout layout = new RowLayout();
        layout.type = SWT.VERTICAL;
        layout.wrap = true;
        shell.setLayout(layout);
        Label label = new Label(shell, SWT.PUSH);
        RowData rowData = new RowData(438, SWT.DEFAULT);
        rowData.exclude = true;
        label.setLayoutData(rowData);
        label.setText("CommunityMashup-URL:");
        final Text txt_url = new Text(shell, SWT.PUSH);
        txt_url.setEditable(true);
        txt_url.setLayoutData(new RowData(425, SWT.DEFAULT));
        txt_url.setText("http://localhost:8081/mashup");
        
        Button btn_start = new Button(shell, SWT.PUSH);
        
        btn_start.addSelectionListener(new SelectionAdapter() {
        	@Override
        	public void widgetSelected(SelectionEvent e) {
        		try {  
            		api.init(txt_url.getText());
        		} catch(Exception ex) {   		        
    		        if(ex instanceof MalformedURLException)
    		        	displayError("Die angegebene URL ist ungültig.");
    		        else if(ex instanceof IOException)
    		        	displayError("Die angegebene URL ist nicht erreichbar.");
    		        else if(ex instanceof JAXBException)
    		        	displayError("Unerwartetes Datenformat.");
    		        else
    		        	displayError("Unbekannter Fehler beim Verarbeiten der Daten.");
    		        
    		        ex.printStackTrace();
        		}
        	}
        });
        
        btn_start.setText("Starten");
       
        shell.setMinimumSize(new Point(500, 500));
        shell.pack();
        shell.open();
        
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) display.sleep();
        }
    }
}
