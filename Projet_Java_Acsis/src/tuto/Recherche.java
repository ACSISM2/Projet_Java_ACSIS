package tuto;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

public class Recherche {

	public static void Recherche_index(StandardAnalyzer analyzer,Directory index) throws IOException{	/* ***** Recherche dans l'index ***** */

		String querystr=Interface.textField.getText().concat("*"); // Concatination du mot clé inséré par * 

		try{
			@SuppressWarnings("deprecation")
			// 1. Requête
			Query q = new QueryParser(Version.LUCENE_CURRENT, "Objet", analyzer).parse(querystr);

			// 2. Recherche
			int hitsPerPage = 1000;

			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);

			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);

			searcher.search(q, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			String testeur="";
			int c=hits.length;	

			Interface.traite.vider_Jtable(Interface.table);
			for(int i=0;i<hits.length;++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				//	System.out.println((i + 1) + ". " + d.get("Sujet") +"||"+ "\t" + d.get("Objet")+"||"+ "\t" + d.get("Num"));
				Interface.mod_bis = (javax.swing.table.DefaultTableModel) Interface.table.getModel();

				if(  (d.get("index").equals("index1")) && !(d.get("ligne").equals(testeur))  ){
					Interface.mod_bis.addRow(new Object[]{d.get("Sujet"),d.get("Predicat"),d.get("Objet")});

					testeur=d.get("ligne");
				}
				else if(d.get("index").equals("index2") && !(d.get("ligne").equals(testeur))) {
					Interface.mod_bis.addRow(new Object[]{d.get("Objet"),d.get("Predicat"),d.get("Sujet")});

					testeur=d.get("ligne");
				}
				else  c--;
			}

			reader.close();
			Interface.label.setText(c + "  Resultats trouvés."); // Nombre de résultats trouvés 
		}catch(org.apache.lucene.queryparser.classic.ParseException e){
			//	L'exception est utilisée pour empêcher les redondances des résultats affichés 
			JOptionPane.showMessageDialog(null, "Ile ne faut pas commencer par "+Interface.textField.getText(), "Attention !", JOptionPane.WARNING_MESSAGE, null);

		}
	}
}


