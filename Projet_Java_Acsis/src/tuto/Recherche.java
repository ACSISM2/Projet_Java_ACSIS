package tuto;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import projet.Document;
import projet.Interface;


public class Recherche {
	public static void Recherche_index(StandardAnalyzer analyzer,Directory index) throws IOException{
	       // 2. requéte (de puis le textField
			String querystr=Interface.textField.getText().concat("*");

			

		
				@SuppressWarnings("deprecation")
				Query q = new QueryParser(Version.LUCENE_CURRENT, "Objet", analyzer).parse(querystr);

				// 3.recherche
				int hitsPerPage = 1000;
				IndexReader reader = DirectoryReader.open(index);
				IndexSearcher searcher = new IndexSearcher(reader);
				TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
				searcher.search(q, collector);
				ScoreDoc[] hits = collector.topDocs().scoreDocs;
				String testeur="";
				int c=hits.length;
			
// 4. display results
				
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
				Interface.label.setText(c + "  Resultats trouvés.");
				
				
				
	}
}
