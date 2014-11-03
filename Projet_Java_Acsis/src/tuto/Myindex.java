package tuto;


import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;


public class Myindex {

	public static void ajout_adddoc() throws IOException, ParseException{

		Interface.modeltable = (DefaultTableModel) Interface.table.getModel();


		@SuppressWarnings("deprecation")
		StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

		// 1. creation de l'index
		Directory index = new RAMDirectory();

		@SuppressWarnings("deprecation")
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);

		IndexWriter w = new IndexWriter(index, config);
		Interface.i=0;
		StmtIterator iter = Interface.model_bis.listStatements();
		while (iter.hasNext()) {
			Interface.i++;
			Statement stmt      = iter.nextStatement();    // get next statement
			Resource subject   = stmt.getSubject();        // get the subject
			Property predicate = stmt.getPredicate();     // get the predicate
			RDFNode object    = stmt.getObject();  
			addDoc(w, object.toString(),subject.toString(),predicate.toString(),"index1",Interface.i);
			addDoc(w, subject.toString(),object.toString(),predicate.toString(),"index2",Interface.i);
		
		}
		w.close();
	
		Recherche.Recherche_index(analyzer,index);
		
	}

	public static void addDoc(IndexWriter w, String title1, String title2,String title3 ,String nom,int ligne_num) throws IOException {

		Document doc = new Document();
		doc.add(new TextField("Objet", title1, Field.Store.YES));
		doc.add(new StringField("Sujet", title2, Field.Store.YES));
		doc.add(new StringField("Predicat", title3+"", Field.Store.YES));
		doc.add(new StringField("index", nom, Field.Store.YES));
		doc.add(new StringField("ligne", ligne_num+"", Field.Store.YES));
		w.addDocument(doc);

	}




}
