package tuto;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Traitement {

	public void vider_Jtable (JTable Table)  /* ***** Vider la Table ***** */
	{
		int i=Table.getRowCount();
		for(int p=1;p<=i;p++){
			((DefaultTableModel)Table.getModel()).removeRow(0);
		}
	}

	public void actualiser_table(JTable Table){ /* ***** Actualiser la Table ***** */

		Interface.traite.vider_Jtable(Interface.table);
		Myrdf.affichage_rdf_Jtable (Interface.model_bis,Table);
		Interface.label.setText("");
		Interface.textField.setText("");
	}


	@SuppressWarnings("deprecation")
	public String ouvrir_fichier()  {  /* *****Choix d'un fichier RDF ***** */

		String filename ;

		JFileChooser chooser=new JFileChooser("RDF");
		chooser.showOpenDialog(null);

		File f=chooser.getSelectedFile();
		filename=f.getAbsolutePath();
		Interface.label_1.setText(f.getName());
		Interface.textField.enable();
		return filename;
	}


}