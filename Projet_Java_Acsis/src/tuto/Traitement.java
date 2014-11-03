package tuto;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Traitement {

	public void vider_Jtable (JTable Table)  // vider la table
	{
		int i=Table.getRowCount();
		for(int p=1;p<=i;p++){
			((DefaultTableModel)Table.getModel()).removeRow(0);
		}
	}
	//////actualiser la table //////////
	public void actualiser_table(JTable Table){
		Interface.traite.vider_Jtable(Interface.table);
		Myrdf.affichage_rdf_Jtable (Interface.model_bis,Table);
		Interface.label.setText("");
		Interface.textField.setText("");
	}


	@SuppressWarnings("deprecation")
	public String ouvrir_fichier()  {  
		// choix d'un fichier rdf 

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