package tuto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
@SuppressWarnings("serial")
public class Interface extends JFrame {

	public Interface () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		JTable table = new JTable();
		Traitement traite= new Traitement(); 
		Myrdf lec_rdf= new Myrdf(); 
		
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
			    traite.vider_Jtable(table);
			    
				lec_rdf.affichage_rdf_Jtable(lec_rdf.lire_fichier_rdf(traite.ouvrir_fichier()),table);
				
				
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
		JPanel  panel1 = new JPanel();
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
}
