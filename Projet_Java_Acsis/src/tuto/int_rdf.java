package tuto;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class int_rdf extends JFrame {
	private JPanel panel1;
    private JTable table;
    private int i=0;
    
    // methode qui permet la lecture d'un fichier RDF et afficher le triples dans une table
    private void lire_fichier_rdf (String inputFileName,JTable Table){
    	// créer un modèle vide
     	Model model = ModelFactory.createDefaultModel();
     		 // utiliser le FileManager pour trouver le fichier d'entrée
     		 InputStream in = FileManager.get().open( inputFileName );
     		if (in == null) {
     		    throw new IllegalArgumentException(
     		                                 "Fichier: " + inputFileName + " non trouvé");    
        }                                       
	    model.read(in, null);
	    affichage_rdf_table(model,table);
    }
    private void affichage_rdf_table (Model model,JTable Table)
    {
    	 StmtIterator iter = model.listStatements();
 		while (iter.hasNext()) {
          Statement stmt      = iter.nextStatement(); // get next statement
          Resource subject   = stmt.getSubject();   // get the subject
          Property predicate = stmt.getPredicate(); // get the predicate
          RDFNode object    = stmt.getObject();    // get the object
           //remplissage de la table 
           javax.swing.table.DefaultTableModel mod = (javax.swing.table.DefaultTableModel) Table.getModel();
           mod.addRow(new Object[]{subject,predicate,object});
    }
 		}
    // ouvrir le fichier RDF 
	private void ouvrir_fichier(java.awt.event.ActionEvent evt)  {  
	    // choix d'un fichier rdf 
    	String filename ;
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        
        // vider la table 
        table=vider_table (table);
        lire_fichier_rdf (filename,table);
		}
	public JTable vider_table (JTable Table)  // vider une table
	{
		 i=Table.getRowCount();
	        for(int p=1;p<=i;p++){
	          ((DefaultTableModel)Table.getModel()).removeRow(0);
	        }
			return Table;
	}
	// creation de la Jframe Fenetre principale de l'interface 
	public int_rdf() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 512);
		//Genre application windows sur l'interface
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmRDF = new JMenuItem("RDF");
		mntmRDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ouvrir_fichier(evt);
			}
		});
		mnFile.add(mntmRDF);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuitter);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel panel = new JPanel();
		panel1.add(panel, "cell 0 1,grow");
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 15, 2, 2);
		panel.add(scrollPane);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 148, 866, 241);
		panel.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "Sujet", "Predicat", "Objet"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Object.class, String.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table);		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					int_rdf frame = new int_rdf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
