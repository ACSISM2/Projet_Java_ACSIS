package tuto;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Traitement {
	
	
	public void vider_Jtable (JTable Table)  // vider une table
	{
		 int i=Table.getRowCount();
	        for(int p=1;p<=i;p++){
	          ((DefaultTableModel)Table.getModel()).removeRow(0);
	        }
	}
	
	public String ouvrir_fichier()  {  
	    // choix d'un fichier rdf 
    	String filename ;
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        return filename;
}


}
