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

	

	//////////recupèrè un fichier rdf a partir de son path/////////// 
		/////////////////////////////////////////////////////
		
			public Model lire_fichier_rdf (String inputFileName){
				
			
	    	// créer un modèle vide
	     	Model model = ModelFactory.createDefaultModel();
	     	// utiliser le FileManager pour trouver le fichier d'entrée
	     	InputStream in = FileManager.get().open( inputFileName );
	     			if (in == null) {
	     				throw new IllegalArgumentException(
	     						"Fichier: " + inputFileName + " non trouvé");    
	     			}                                       
	     			model.read(in, null);
	     			return model;
		}	
		///////////// affichage du rdf dans la Jtable
		
		 public void affichage_rdf_Jtable (Model model,JTable Table)
		    {
			//on vide la table au moment de l'affiche
			   Interface.traite.vider_Jtable(Interface.table);
			 //--------------------------------------------------------------
		    	 StmtIterator iter = model.listStatements();
		 		while (iter.hasNext()) {
		          Statement stmt      = iter.nextStatement();    // get next statement
		          Resource subject   = stmt.getSubject();        // get the subject
		          Property predicate = stmt.getPredicate();     // get the predicate
		          RDFNode object    = stmt.getObject();        // get the object
		           //remplissage de la table 
		           javax.swing.table.DefaultTableModel mod = (javax.swing.table.DefaultTableModel) Table.getModel();
		           mod.addRow(new Object[]{subject,predicate,object});
		    }
		 		}
		 
	}

	

