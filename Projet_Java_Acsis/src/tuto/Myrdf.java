package tuto;

import java.io.InputStream;
import javax.swing.JTable;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;

public class Myrdf {

	public void lire_fichier_rdf (String inputFileName){ /* *****Récupération d'un fichier RDF ***** */

		Interface.label.setText("");
		Interface.textField.setText("");

		// Creation d'un Model vide
		Model model = ModelFactory.createDefaultModel();
		Interface.model_bis=model;

		// Utiliser le FileManager pour trouver le fichier d'entrée
		InputStream in = FileManager.get().open( inputFileName );
		if (in == null) {
			throw new IllegalArgumentException(
					"Fichier: " + inputFileName + " non trouvé");    
		}                                       
		model.read(in, null);
		affichage_rdf_Jtable(model,Interface.table);
	}	


	public static void affichage_rdf_Jtable (Model model,JTable Table)	{	/* *****Affichage d'un fichier RDF dans la Jtable***** */


		Interface.traite.vider_Jtable(Table);

		StmtIterator iter = model.listStatements();
		while (iter.hasNext()) {
			Statement stmt      = iter.nextStatement();     // get next statement
			Resource subject   = stmt.getSubject();        // get the subject
			Property predicate = stmt.getPredicate();     // get the predicate
			RDFNode object    = stmt.getObject();        // get the object

			// Remplissage de la table 
			javax.swing.table.DefaultTableModel mod = (javax.swing.table.DefaultTableModel) Table.getModel();
			mod.addRow(new Object[]{subject,predicate,object});
		}
	}



}



