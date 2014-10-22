package tuto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
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
	private JTextField textField;
    private JTable table;
    private int i=0;

	private void ouvrir_fichier(java.awt.event.ActionEvent evt)  {  
	    // choix d'un fichier rdf 
    	String inputFileName ;
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        inputFileName= filename;
        
        // vider la table 
        i=table.getRowCount();
        i--;
        //System.out.print(j);
        for(int p=0;p<=i;p++){
          ((DefaultTableModel)table.getModel()).removeRow(0);
        }
       
        textField.setText(filename);
        
        // créer un modèle vide
     	Model model = ModelFactory.createDefaultModel();
     		 
     		 
     		 // utiliser le FileManager pour trouver le fichier d'entrée
     		 InputStream in = FileManager.get().open( inputFileName );
     		if (in == null) {
     		    throw new IllegalArgumentException(
     		                                 "Fichier: " + inputFileName + " non trouvé");
        
        }                                       
    	
	    model.read(in, null);
	    StmtIterator iter = model.listStatements();
	 
		while (iter.hasNext()) {
         Statement stmt      = iter.nextStatement(); // get next statement
         Resource  subject   = stmt.getSubject();   // get the subject
         Property  predicate = stmt.getPredicate(); // get the predicate
         RDFNode   object    = stmt.getObject();    // get the object
         javax.swing.table.DefaultTableModel mod = (javax.swing.table.DefaultTableModel) table.getModel();
         mod.addRow(new Object[]{subject,predicate,object});
      
		}
   }
	
	public int_rdf() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 512);
		//Genre application windows sur l'interface
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuitter = new JMenu("File");
		menuBar.add(mnQuitter);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnQuitter.add(mntmQuitter);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel1);
		panel1.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel panel = new JPanel();
		panel1.add(panel, "cell 0 1,grow");
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(39, 111, 340, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 15, 2, 2);
		panel.add(scrollPane);
		
		JButton btnNewButton = new JButton("Parcourir");
		btnNewButton.setBounds(389, 110, 113, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				ouvrir_fichier(evt);
			}
		});
		panel.add(btnNewButton);
		
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
