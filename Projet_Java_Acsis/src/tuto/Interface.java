package tuto;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import org.apache.lucene.queryparser.classic.ParseException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.awt.Font;
@SuppressWarnings("serial")

public class Interface extends JFrame {

	public static Model model = ModelFactory.createDefaultModel();
	public static Model model_bis = ModelFactory.createDefaultModel();
	public static Myrdf lec_rdf= new Myrdf();

	public static JTable table = new JTable();
	public static JLabel label = new JLabel("");
	public static JLabel label_1 = new JLabel("");
	public static JTextField textField;
	public static Traitement traite= new Traitement(); 
	public static DefaultTableModel modeltable;
	public static javax.swing.table.DefaultTableModel mod_bis;

	public static int i ;

	@SuppressWarnings("deprecation")
	public Interface () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 916, 512);
		// Apparence d'une application Windows
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		// 1. Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmRDF = new JMenuItem("RDF");
		mntmRDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try{  
					lec_rdf.lire_fichier_rdf(traite.ouvrir_fichier());
				}catch(Exception E){
					// java.lang.NullPointerException
				}
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
				}) 
		{
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

		textField = new JTextField();
		textField.setBounds(39, 86, 482, 28);
		panel.add(textField);
		textField.setColumns(10);
		textField.disable();
		JButton btnRecherche = new JButton("Recherche");

		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!textField.getText().equals(""))
						Myindex.ajout_adddoc();
					else traite.actualiser_table(table);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnRecherche.setBounds(552, 86, 89, 28);
		panel.add(btnRecherche);


		label.setBounds(562, 123, 222, 14);
		panel.add(label);
		label_1.setFont(new Font("Arial", Font.ITALIC, 10));
		label_1.setBounds(60, 61, 190, 14);
		panel.add(label_1);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {	
					Interface frame = new Interface();
					frame.setTitle("Recherche par mots-clés dans des données RDF");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}
}
