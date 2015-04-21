import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.SplashScreen; 
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Fenetre extends JFrame implements ActionListener {
	
	private JPanel pan;
	private JPanel[] panels;
	private CardLayout cardlayout;
	//------ Barre d'outils -----
	private JComboBox modecombo;
	private JButton addbutton;
	private JButton showbutton;
	private int num_panel;
	private JMenuItem item1 ;
	private JMenuItem item2 ;
	private JMenuItem item3 ;
	private JMenuItem item4 ;
	private JMenuItem item5 ;
	private JMenuItem item6 ;
	private JCheckBoxMenuItem affich;
	private JButton delbutton;
    //-------------------------
	
	// ----RDV------
	private RDV rdv;
	private RDV[] rdvs;
	private JTextField nomtf;
	private JTextField prenomtf;
	private JTextField agetf;
	private JTextField malad_anttf;
	private JTextField num_idtf;
	private JTextField num_idptf;
	private JTextField num_teltf;
	private JTextField maladietf;
	private int[] med_id;
	private JComboBox medcombo;
	private JDateChooser datec;
	private JButton enreg;
	private JSpinner timeSpinner;
	private SpinnerDateModel spinner_model;
	private JPanel[] panel_revs;
	private DefaultTableModel model3 ;
	private JTable table3;
	private Object[] tabr1;
	private Object[] tabr2;
	private Object[] tabr3;
	private Object[] tabr4;
	private Object[] tabr5;
	private Object[] tabr6;

	//----------------
	
	// ----- MEDECIN------
	private Medecin[] med;
	private Medecin medecin;
	private JTextField nommedtf;
	private JTextField prenommedtf;
	private JTextField Specialitetf;
	private JTextField telmedtf;
	private JTextField numeromedtf;
	private JTextField adrmedltf;
	private JButton enregmed;
	private DefaultTableModel model2 ;
	private JTable table2;
	private Object[] tabm1;
	private Object[] tabm2;
	private Object[] tabm3;
	private Object[] tabm4;
	private Object[] tabm5;
	private Object[] tabm6;

	//-----------------------
	
	//-------- PATIENT--------
	Patient patient;
	private Patient[] pat;
	private Object[] tab1;
	private Object[] tab2;
	private Object[] tab3;
	private Object[] tab4;
	private Object[] tab5;
	private Object[] tab6;
	private Object[] tab7;
	private DefaultTableModel model ;
	private JTable table1;

	//-----------------------
	
	//------- CONSULTATION-----
	private Consultation consultation;
	private Consultation[] cons;
	private JComboBox rdvcombo;
	private JComboBox deccombo;
	private JButton enregcons;
	private JTextArea diagta;
	private JTextArea traitta;
	private DefaultTableModel model4 ;
	private JTable table4;
	private Object[] tabc1;
	private Object[] tabc2;
	private Object[] tabc3;
	private Object[] tabc4;
	private Object[] tabc5;
	private Object[] tabc6;
	//-----------------------------
	
	//--------Hospitalisation------
	private Hospitalisation hospital; 
	private Hospitalisation[] hosp; 
	private JComboBox conscombo;
	private JTextField servicetf;
	private JTextArea bilanta;
	private JDateChooser date_entrer;
	private JDateChooser date_sortie;
	private JComboBox gard_malade;
	private JTextField num_salle;
	private JTextField num_lit;
	private JButton enreghosp;	
	private DefaultTableModel model5 ;
	private JTable table5;
	private Object[] tabh1;
	private Object[] tabh2;
	private Object[] tabh3;
	private Object[] tabh4;
	private Object[] tabh5;
	private Object[] tabh6;
	private Object[] tabh7;
	private Object[] tabh8;
	private Object[] tabh9;
//------------------------------
	public static void main(String[] args) {
		
		try
		{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}

		catch (Exception e)
		{
		System.out.println("Unable to load Windows look and feel");
		}
		
		new Splashscr() ;
		new Fenetre();
		
		
	}
	
	public Fenetre()
	{
		
		
		medecin = new Medecin();
		this.setSize(1000, 700);
		this.setLocationRelativeTo(null);
		this.setTitle("MEHospital Software");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		num_panel=0;
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("splash.png"));
		pan = new JPanel(cardlayout = new CardLayout());
		panels = new JPanel[9];
	
		
		//-----------------------------------
		
		//---------BARRE D'OUTILS----------------
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		addbutton = new JButton("Ajouter ",new ImageIcon(".//95.png"));
		toolbar.add(addbutton);
		delbutton = new JButton("Supprimer",new ImageIcon(".//14.png"));
		toolbar.add(delbutton);
		showbutton = new JButton("Afficher",new ImageIcon(".//63.png"));
		toolbar.add(showbutton);
		addbutton.addActionListener(this);
		delbutton.addActionListener(this);
		showbutton.addActionListener(this);
		modecombo= new JComboBox();
		modecombo.addItem("Medecin");
		modecombo.addItem("Patient");
		modecombo.addItem("Rendez-vous");
		modecombo.addItem("Consultation");
		modecombo.addItem("Hospitalité");
		modecombo.setSize(modecombo.getSize());
		modecombo.setMaximumSize(new Dimension(120,35));
		modecombo.setSize(32, 21);
		modecombo.addActionListener(this);
		toolbar.addSeparator();
		toolbar.add(modecombo);
		modecombo.setUI(new BasicComboBoxUI());
		addbutton.setBackground(new Color(239,224,217));modecombo.setBackground(new Color(239,224,217));
		showbutton.setBackground(new Color(239,224,217)); delbutton.setBackground(new Color(239,224,217));
		toolbar.setBackground(new Color(239,230,230));

		//---------------------------------------------------

		
		//-----------------RENDEZ-VOUS------------------------------
		
		//ajouter
		panels[0]= new JPanel();
		rdv=new RDV();
		
		GridLayout gl = new GridLayout(14, 2);
		gl.setHgap(10);
		gl.setVgap(10);
		
		panels[0].setLayout(gl);
		
		JLabel noml = new JLabel("Nom* : ");
		nomtf = new JTextField(20);nomtf.setFont(new Font("Arial",15,20));

		JLabel prenoml = new JLabel("Prenom* : ");
		prenomtf = new JTextField(20);prenomtf.setFont(new Font("Arial",15,20));
		
		JLabel agel = new JLabel("Age* :");
		agetf = new JTextField(5);agetf.setFont(new Font("Arial",15,20));
		
		JLabel maladiel = new JLabel("Maladi* : ");
		maladietf = new JTextField(30);maladietf.setFont(new Font("Arial",15,20));
		
		JLabel malad_antl = new JLabel("type de maladie antescedant   : ");
		malad_anttf = new JTextField(30);malad_anttf.setFont(new Font("Arial",15,20));
		
		JLabel num_idl = new JLabel("N° de la carte d'identité*   : ");
		num_idtf = new JTextField(20);num_idtf.setFont(new Font("Arial",15,20));
		
		JLabel num_idpl = new JLabel("N° de la carte d'identité des parrents(pour les mineurs)   :");
		num_idptf = new JTextField(5);num_idptf.setFont(new Font("Arial",15,20));
		
		JLabel num_tell = new JLabel("N° Telephone*   :");
		num_teltf = new JTextField(5);num_teltf.setFont(new Font("Arial",15,20));
		
		JLabel medecinl = new JLabel("Medecin*   :");
		
		medcombo = new JComboBox();medcombo.setFont(new Font("Arial",15,20));
		med = medecin.MedecinAll();
		med_id = new int[med.length];
		for(int i= 0;i< med.length;i++)
		{
			medcombo.addItem(med[i].getNom()+" "+med[i].getPrenom());
			med_id[i]=med[i].getid();
		}
		
		JLabel datel = new JLabel("Date* :");
		datec = new JDateChooser();datec.setFont(new Font("Arial",15,20));
		JLabel timel = new JLabel("Heur* :");
		spinner_model = new SpinnerDateModel();
		timeSpinner = new JSpinner( spinner_model);
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setFont(new Font("Arial",15,20));
		JLabel remarque = new JLabel("Les champs procédés par (*) sont obligatoire :");
		JLabel vide = new JLabel("");
		
		enreg = new JButton("Enregistrer");
		
		enreg.addActionListener(this);
		
		panels[0].add(noml);panels[0].add(nomtf);
		panels[0].add(prenoml);panels[0].add(prenomtf);
		panels[0].add(agel);panels[0].add(agetf);
		panels[0].add(maladiel);panels[0].add(maladietf);
		panels[0].add(malad_antl);panels[0].add(malad_anttf);
		panels[0].add(num_tell);panels[0].add(num_teltf);
		panels[0].add(num_idl);panels[0].add(num_idtf);
		panels[0].add(num_idpl);panels[0].add(num_idptf);
		panels[0].add(medecinl);panels[0].add(medcombo);
		panels[0].add(datel);panels[0].add(datec);
		panels[0].add(timel);panels[0].add(timeSpinner);
		panels[0].add(remarque);panels[0].add(vide);
		panels[0].add(enreg);
		
		//afficher
		model3 = new DefaultTableModel();
		table3 = new JTable(model3);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane3 = new JScrollPane();
		scrollpane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane3.setBounds(new Rectangle(20,10,950,600));
		scrollpane3.getViewport().add(table3,null);
		
		tabr1=new Object[100];	
		tabr2=new Object[100];
		tabr3=new Object[100];
		tabr4=new Object[100];
		tabr5=new Object[100];
		tabr6=new Object[100];
		
		panels[5] = new JPanel();
		panels[5].setLayout(null);
		panels[5].add(scrollpane3);
		
		//-----------------------FIN RENDEZ-VOUS ---------------------------
		
		//-----------------MEDECIN------------------------------
		
		//Ajouter
		panels[1]= new JPanel();
		panels[1].setLayout(null);
		panels[1].setSize(100, 100);
		GridLayout gl2 = new GridLayout(12, 2);
		gl.setHgap(10);
		gl.setVgap(10);
		panels[1].setLayout(gl2);
		 
		JLabel nommedl = new JLabel("     Nom : ");
		nommedtf = new JTextField(20);nommedtf.setFont(new Font("Arial",15,20));
		
		JLabel prenommedl = new JLabel("     Prenom : ");
		prenommedtf = new JTextField(20);prenommedtf.setFont(new Font("Arial",15,20));
		
		JLabel Specialitel = new JLabel("     Specialite :");
		Specialitetf = new JTextField(20);Specialitetf.setFont(new Font("Arial",15,20));
		
		JLabel telmedl = new JLabel("     Numero de Telephone :");
		telmedtf = new JTextField(20);telmedtf.setFont(new Font("Arial",15,20));
		
		JLabel numermedl = new JLabel("     Numero d'inscription au conseil de l'ordre :");
		numeromedtf = new JTextField(20);numeromedtf.setFont(new Font("Arial",15,20));
		
		JLabel adrmedl = new JLabel("     Adresse :");
		adrmedltf = new JTextField(40);adrmedltf.setFont(new Font("Arial",15,20));
		
		enregmed = new JButton("     Enregistrer");
		
		enregmed.addActionListener(this);
		
		panels[1].add(nommedl);panels[1].add(nommedtf);
		panels[1].add(prenommedl);panels[1].add(prenommedtf);
		panels[1].add(Specialitel);panels[1].add(Specialitetf);
		panels[1].add(telmedl);panels[1].add(telmedtf);
		panels[1].add(numermedl);panels[1].add(numeromedtf);
		panels[1].add(adrmedl);panels[1].add(adrmedltf);
		panels[1].add(enregmed);
		
		//afficher
		model2 = new DefaultTableModel();
		table2 = new JTable(model2);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane2 = new JScrollPane();
		scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane2.setBounds(new Rectangle(20,10,950,600));
		scrollpane2.getViewport().add(table2,null);
		panels[4] = new JPanel();
		tabm1=new Object[100];	
		tabm2=new Object[100];
		tabm3=new Object[100];
		tabm4=new Object[100];
		tabm5=new Object[100];
		tabm6=new Object[100];
		panels[4].setLayout(null);
		panels[4].add(scrollpane2);

		//-----------------------FIN MEDECIN ---------------------------
		
		//----------------------- PATIENT-------------------------------
		
		patient=new Patient();
		panels[2] = new JPanel();
		panels[2].setLayout(null);
		model = new DefaultTableModel();
		table1 = new JTable(model);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBounds(new Rectangle(20,10,950,600));
		scrollpane.getViewport().add(table1,null);

		tab1=new Object[100];	
		tab2=new Object[100];
		tab3=new Object[100];
		tab4=new Object[100];
		tab5=new Object[100];
		tab6=new Object[100];
		tab7=new Object[100];	
		
	
		
		
		//panels[2].add(table1);
		panels[2].add(scrollpane);
		
		//--------------------------------------------------------------
		
		//---------------------------Consultation-----------------------
		//ajouter
		
		panels[3] = new JPanel();
		GridLayout gl3 = new GridLayout(8, 2);
		gl.setHgap(10);
		gl.setVgap(10);
		panels[3].setLayout(gl3);
		JLabel rdvl = new JLabel("     Rendez-vous : ");
		rdvcombo = new JComboBox();rdvcombo.setFont(new Font("Arial",12,12));
		rdvs=rdv.getAll();
		for(int i= 0;i< rdvs.length;i++)
		{
			rdvcombo.addItem("Le Patient : "+patient.getInfo(rdvs[i].getPatId()).getNom()+" "+patient.getInfo(rdvs[i].getPatId()).getPrenom()+" | Medecin: "+medecin.getInfo(rdvs[i].getMedId()).getNom()+" "+medecin.getInfo(rdvs[i].getMedId()).getPrenom()+" | Date: "+rdvs[i].getDate());
			
		}
		JLabel diagl = new JLabel("     Diagonositique : ");
		diagta = new JTextArea();diagta.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel traitl = new JLabel("     Traitement : ");
		traitta = new JTextArea(); traitta.setBorder(BorderFactory.createLineBorder(Color.gray));
		JLabel decl = new JLabel("     Admis pour une hospitalisation : : ");
		deccombo = new JComboBox();
		deccombo.addItem("OUI");
		deccombo.addItem("NON");
		enregcons = new JButton("     Enregistrer");
		enregcons.addActionListener(this);
		panels[3].add(rdvl);panels[3].add(rdvcombo);
		panels[3].add(diagl);panels[3].add(diagta);
		panels[3].add(traitl);panels[3].add(traitta);
		panels[3].add(decl);panels[3].add(deccombo);
		panels[3].add(enregcons);
		
		//afficher
		panels[6] = new JPanel();
		panels[6].setLayout(null);
		model4 = new DefaultTableModel();
		table4 = new JTable(model4);
		table4.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollpane4 = new JScrollPane();
		scrollpane4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane4.setBounds(new Rectangle(20,10,950,600));
		scrollpane4.getViewport().add(table4,null);
		
		tabc1=new Object[100];	
		tabc2=new Object[100];
		tabc3=new Object[100];
		tabc4=new Object[100];
		tabc5=new Object[100];
		tabc6=new Object[100];

		panels[6].add(scrollpane4);
		
		//--------------------------------------------------------------
		
		//----------------------HOSPITALISATION---------------------------
		//ajouter
			panels[7]=new JPanel();
			consultation = new Consultation();
			GridLayout gl4 = new GridLayout(12, 2);
			gl.setHgap(10);
			gl.setVgap(10);
			panels[7].setLayout(gl4);
			JLabel consl = new JLabel("     Consultation : ");
			conscombo = new JComboBox();conscombo.setFont(new Font("Arial",12,12));
			cons=consultation.getHosp();
			for(int i= 0;i< cons.length;i++)
			{
				conscombo.addItem("Le Patient : "+patient.getInfo(cons[i].getRdv().getPatId()).getNom()+" | Medecin: "+medecin.getInfo(cons[i].getRdv().getMedId()).getNom()+" | Date: "+rdvs[i].getDate());
				
			}
			
			JLabel servicl=new JLabel("    Service : ");
			servicetf = new JTextField();servicetf.setBorder(BorderFactory.createLineBorder(Color.gray));
			JLabel num_sallel=new JLabel("    Numero de salle : ");
			num_salle = new JTextField();num_salle.setBorder(BorderFactory.createLineBorder(Color.gray));			
			JLabel num_litl=new JLabel("    Numero de lit : ");
			num_lit = new JTextField();num_lit.setBorder(BorderFactory.createLineBorder(Color.gray));
			JLabel bilanl=new JLabel("    Bilan : ");
			bilanta = new JTextArea();bilanta.setBorder(BorderFactory.createLineBorder(Color.gray));
			JLabel gard_maladel=new JLabel("    Besion d'une garde malade : ");
			gard_malade = new JComboBox();
			gard_malade.addItem("OUI");
			gard_malade.addItem("NON");
			JLabel date_entrel = new JLabel("Date d'entrée :");
			date_entrer = new JDateChooser();date_entrer.setFont(new Font("Arial",12,12));
			JLabel date_sortiel = new JLabel("Date de sortie :");
			date_sortie = new JDateChooser();date_sortie.setFont(new Font("Arial",12,12));
			enreghosp = new JButton("Enregistrer");
			enreghosp.addActionListener(this);
			panels[7].add(consl);panels[7].add(conscombo);
			panels[7].add(servicl);panels[7].add(servicetf);
			panels[7].add(num_sallel);panels[7].add(num_salle);
			panels[7].add(num_litl);panels[7].add(num_lit);
			panels[7].add(bilanl);panels[7].add(bilanta);
			panels[7].add(gard_maladel);panels[7].add(gard_malade);
			panels[7].add(date_entrel);panels[7].add(date_entrer);
			panels[7].add(date_sortiel);panels[7].add(date_sortie);
			panels[7].add(enreghosp);
		//afficher
			panels[8]=new JPanel();
			panels[8] = new JPanel();
			panels[8].setLayout(null);
			model5 = new DefaultTableModel();
			table5 = new JTable(model5);
			table5.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			JScrollPane scrollpane5 = new JScrollPane();
			scrollpane5.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scrollpane5.setBounds(new Rectangle(20,10,950,600));
			scrollpane5.getViewport().add(table5,null);
			
			tabh1=new Object[100];	
			tabh2=new Object[100];
			tabh3=new Object[100];
			tabh4=new Object[100];
			tabh5=new Object[100];
			tabh6=new Object[100];
			tabh7=new Object[100];
			tabh8=new Object[100];
			tabh9=new Object[100];

			panels[8].add(scrollpane5);
		//-----------------------END HOSPITLISATION-------------------------

		this.add(toolbar, BorderLayout.NORTH);
		this.add(pan);
		pan.add(panels[0],"RDV");
		pan.add(panels[1],"MED");
		pan.add(panels[2],"PAT");
		pan.add(panels[3],"CONS");
		pan.add(panels[4],"MED_SHOW");
		pan.add(panels[5],"RDV_SHOW");
		pan.add(panels[6],"CONS_SHOW");
		pan.add(panels[7],"HOSP");
		pan.add(panels[8],"HOSP_SHOW");
		cardlayout.show(pan, "MED");
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == enregmed )
		{
			try{
			Medecin med = new Medecin(nommedtf.getText(),prenommedtf.getText(),Specialitetf.getText(),Integer.parseInt(telmedtf.getText()),Integer.parseInt(numeromedtf.getText()),adrmedltf.getText());
			med.save();
			JOptionPane.showMessageDialog(null, "L'operation s'est déroulé correctement , Merci", "succés", JOptionPane.INFORMATION_MESSAGE);
			nommedtf.setText("");prenommedtf.setText("");Specialitetf.setText("");telmedtf.setText("");numeromedtf.setText("");adrmedltf.setText("");
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Les champs ne sont pas rempis correctement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(a.getSource() == enreg ){
			try{
		Patient pat = new Patient(nomtf.getText(), prenomtf.getText(), malad_anttf.getText()+"",maladietf.getText(),
				Integer.parseInt(agetf.getText()),Integer.parseInt(num_teltf.getText()),
					Integer.parseInt(num_idtf.getText()),Integer.parseInt("0"+num_idptf.getText()));
			rdv = new RDV(med_id[medcombo.getSelectedIndex()],pat.save(),new java.sql.Date(datec.getDate().getTime()),new java.sql.Time(spinner_model.getDate().getTime()));
			rdv.save();
			JOptionPane.showMessageDialog(null, "L'operation s'est déroulé correctement , Merci", "succés", JOptionPane.INFORMATION_MESSAGE);
			
			nomtf.setText("");prenomtf.setText("");malad_anttf.setText("");maladietf.setText("");
			agetf.setText("");num_teltf.setText("");num_idtf.setText("");num_idptf.setText("");datec.setDate(null);
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Les champs ne sont pas rempis correctement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}else if(a.getSource() == modecombo ){
			if(modecombo.getSelectedIndex()==2)
			{
				cardlayout.show(pan, "RDV");
				num_panel = 2;
				addbutton.setIcon(new ImageIcon(".//95.png"));
				addbutton.setEnabled(true);
				showbutton.setIcon(new ImageIcon(".//63.png"));
				showbutton.setEnabled(true);

				
				
				
			}else if(modecombo.getSelectedIndex()==1)
			{
				
				cardlayout.show(pan, "PAT");
				num_panel = 1;
				addbutton.setIcon(new ImageIcon(".//95_n.png"));
				addbutton.setEnabled(false);
				showbutton.setIcon(new ImageIcon(".//63_n.png"));
				showbutton.setEnabled(false);
				
				
				pat =patient.getAll();
				
				for(int i=0;i<pat.length;i++)
				{
					tab1[i] = pat[i].getNom();
					tab2[i] = pat[i].getPrenom();
					tab3[i] = pat[i].getAge();
					tab4[i] = pat[i].getMalad();
					tab5[i] = pat[i].getMaladAnt();
					tab6[i] = pat[i].getNid();
					tab7[i] = pat[i].getNidp();
					System.out.print(pat[i].getNom());
				}
				
					model.setColumnCount(0);
				

					model.addColumn("NOM",tab1);
					model.addColumn("PRENOM",tab2);
					model.addColumn("AGE",tab3);
					model.addColumn("MALADIE",tab4);
					model.addColumn("MALADIE ANTESCEDANT",tab5);
					model.addColumn("NUMERO ID",tab6);
					model.addColumn("NUMERO ID PARRENTS",tab7);
				
				
				
				
			}else if(modecombo.getSelectedIndex()==0)
			{
				cardlayout.show(pan, "MED");
				addbutton.setIcon(new ImageIcon(".//95.png"));
				addbutton.setEnabled(true);
				showbutton.setIcon(new ImageIcon(".//63.png"));
				showbutton.setEnabled(true);
				num_panel = 0;
			}
			else if(modecombo.getSelectedIndex()==3)
			{

				cardlayout.show(pan, "CONS");
				addbutton.setIcon(new ImageIcon(".//95.png"));
				addbutton.setEnabled(true);
				showbutton.setIcon(new ImageIcon(".//63.png"));
				showbutton.setEnabled(true);
				num_panel = 3;
			}
			else if(modecombo.getSelectedIndex()==4)
			{
				cardlayout.show(pan, "HOSP");
				addbutton.setIcon(new ImageIcon(".//95.png"));
				addbutton.setEnabled(true);
				showbutton.setIcon(new ImageIcon(".//63.png"));
				showbutton.setEnabled(true);
				num_panel = 4;
			}
		}
		else if(a.getSource() == showbutton )
		{
			
			if(num_panel==0)
			{
				med = medecin.MedecinAll();
				for(int i=0;i<med.length;i++)
				{
					tabm1[i] = med[i].getNom();
					tabm2[i] = med[i].getPrenom();
					tabm3[i] = med[i].getSpec();
					tabm4[i] = med[i].getNum();
					tabm5[i] = med[i].getTel();
					tabm6[i] = med[i].getAdr();
					System.out.print(med[i].getAdr());

				}
				
					model2.setColumnCount(0);
				

					model2.addColumn("NOM",tabm1);
					model2.addColumn("PRENOM",tabm2);
					model2.addColumn("SPECIALITE",tabm3);
					model2.addColumn("NUMERO DANS L'ORDRE",tabm4);
					model2.addColumn("TELEPHONE",tabm5);
					model2.addColumn("ADRESSE",tabm6);
					
				
				cardlayout.show(pan, "MED_SHOW");
				
			}else if(num_panel==3)
			{
				consultation = new Consultation();
				cons = consultation.getAll();
				for(int i=0;i<cons.length;i++)
				{
					tabc1[i] = cons[i].getRdv().getId();
					tabc2[i] = medecin.getInfo(cons[i].getRdv().getMedId()).getNom() +" "+medecin.getInfo(cons[i].getRdv().getMedId()).getPrenom();
					tabc3[i] = patient.getInfo(cons[i].getRdv().getPatId()).getNom() +" "+patient.getInfo(cons[i].getRdv().getPatId()).getPrenom();
					tabc4[i] = cons[i].getDiagnostique();
					tabc5[i] = cons[i].getTraitement();
					tabc6[i] = cons[i].getDecision();
					

				}
				
					model4.setColumnCount(0);
				

					model4.addColumn("NUMERO DE RDV",tabc1);
					model4.addColumn("MEDECIN TRAITANT",tabc2);
					model4.addColumn("PATIENT",tabc3);
					model4.addColumn("DIAGNOSTIQUE",tabc4);
					model4.addColumn("TRAITEMENT",tabc5);
					model4.addColumn("DECISION",tabc6);
					
				cardlayout.show(pan, "CONS_SHOW");
			}else if(num_panel==2)
			{	
				
				rdvs = rdv.getAll();
				
				for(int i=0;i<rdvs.length;i++)
				{
					
					tabr1[i] = medecin.getInfo(rdvs[i].getMedId()).getNom()+"  "+medecin.getInfo(rdvs[i].getMedId()).getPrenom();
					tabr2[i] = patient.getInfo(rdvs[i].getPatId()).getNom()+"  "+patient.getInfo(rdvs[i].getPatId()).getPrenom();
					tabr3[i] = rdvs[i].getDate();
					tabr4[i] = rdvs[i].getHeur();
					//tabr5[i] = med[i].getTel();
					//tabr6[i] = med[i].getAdr();
					

				}
				
					model3.setColumnCount(0);
				

					model3.addColumn("MEDECIN TRAITANT",tabr1);
					model3.addColumn("PATIENT",tabr2);
				 	model3.addColumn("DATE",tabr3);
					model3.addColumn("HEUR",tabr4);
					//model3.addColumn("TELEPHONE",tabr5);
					//model3.addColumn("ADRESSE",tabr6);
					
					cardlayout.show(pan, "RDV_SHOW");
			}
			else if(num_panel==4)
			{
				
					hospital=new Hospitalisation();
					hosp=hospital.getAll();
					System.out.print(""+num_panel+""+hosp.toString());
					
					for(int i=0;i<hosp.length;i++)
					{
						System.out.print("ssss"+medecin.getInfo(hosp[i].getCons().getRdv().getMedId()).getNom());
						
						tabh1[i] = medecin.getInfo(hosp[i].getCons().getRdv().getMedId()).getNom()+"  "+medecin.getInfo(hosp[i].getCons().getRdv().getMedId()).getPrenom();
						tabh2[i] = patient.getInfo(hosp[i].getCons().getRdv().getPatId()).getNom()+"  "+patient.getInfo(hosp[i].getCons().getRdv().getPatId()).getPrenom();
						tabh3[i] = hosp[i].getService();
						tabh4[i] = hosp[i].getNumS();
						tabh5[i] = hosp[i].getNumL();
						tabh6[i] = hosp[i].getEntre();
						tabh7[i] = hosp[i].getSortie();
						tabh8[i] = hosp[i].getGard();
						tabh9[i] = hosp[i].getBilan();
						

					}
					
						model5.setColumnCount(0);
					

						model5.addColumn("MEDECIN TRAITANT",tabh1);
						model5.addColumn("PATIENT",tabh2);
					 	model5.addColumn("SERVICE",tabh3);
						model5.addColumn("NUMERO DE SALLE",tabh4);
					 	model5.addColumn("NUMERO DE LIT",tabh5);
					 	model5.addColumn("DATE D'ENTRE",tabh6);
						model5.addColumn("DATE DE SORTIE",tabh7);
						model5.addColumn("GUARD MALADE",tabh8);
					 	model5.addColumn("BILAN",tabh9);


					cardlayout.show(pan, "HOSP_SHOW");
			}
				
		}
		else if(a.getSource() == delbutton )
		{
			if(num_panel==0)
			{
				med = medecin.MedecinAll();
				int rep = JOptionPane.showConfirmDialog(null, "cette opertation vas supprimer tous les Consultation ,RDVs et Les Hospitalisaion chargé par le Medecin \n "+med[table2.getSelectedRow()].getNom()+" "+med[table2.getSelectedRow()].getPrenom(), "Remarque", JOptionPane.YES_NO_OPTION);
				if(JOptionPane.YES_OPTION==rep)
				{
				medecin.Delete(med[table2.getSelectedRow()].getid());
				model2.removeRow(table2.getSelectedRow());
				}
			}
			else if(num_panel==1)
			{
				pat = patient.getAll();
				int rep = JOptionPane.showConfirmDialog(null, "cette opertation vas supprimer tous les Consultation ,RDVs et Les Hospitalisaion du patient \n "+pat[table1.getSelectedRow()].getNom()+" "+pat[table1.getSelectedRow()].getPrenom(), "Remarque", JOptionPane.YES_NO_OPTION);
				if(JOptionPane.YES_OPTION==rep)
				{
				patient.Delete(pat[table1.getSelectedRow()].getId());
				model.removeRow(table1.getSelectedRow());
				}
			}
			else if(num_panel==2)
			{
				
				rdvs = rdv.getAll();
				rdv.Delete(rdvs[table3.getSelectedRow()].getId());
				model3.removeRow(table3.getSelectedRow());
				
			}

		}
		else if(a.getSource() == addbutton )
		{
			if(num_panel==0)
			{
				cardlayout.show(pan, "MED");
				
			}else if(num_panel==1)
			{
				
			}else if(num_panel==2)
			{
				cardlayout.show(pan, "RDV");
			}
			else if(num_panel==3)
			{
				cardlayout.show(pan, "CONS");
			}
			else if(num_panel==4)
			{
				cardlayout.show(pan, "HOSP");
			}
				
		}
		else if(a.getSource() == enregcons)
		{
			
			Boolean dec = false;
			if(deccombo.getSelectedIndex()==0) dec=true;
			try{
			consultation = new Consultation(rdvs[rdvcombo.getSelectedIndex()],diagta.getText(),traitta.getText(),dec);
			consultation.save();
			JOptionPane.showMessageDialog(null, "L'operation s'est déroulé correctement , Merci", "succés", JOptionPane.INFORMATION_MESSAGE);
			diagta.setText("");traitta.setText("");
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Les champs ne sont pas rempis correctement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			}else if(a.getSource() == enreghosp ){
			Boolean gard=false;
			if(gard_malade.getSelectedIndex()==0) gard=true; 
			try{
			hospital = new Hospitalisation(cons[conscombo.getSelectedIndex()],servicetf.getText(),Integer.parseInt(num_salle.getText()),Integer.parseInt(num_lit.getText()),new java.sql.Date(date_entrer.getDate().getTime()),new java.sql.Date(date_sortie.getDate().getTime()),gard,bilanta.getText());
			hospital.save();
			JOptionPane.showMessageDialog(null, "L'operation s'est déroulé correctement , Merci", "succés", JOptionPane.INFORMATION_MESSAGE);
			servicetf.setText("");num_salle.setText("");num_lit.setText("");date_entrer.setDate(null);date_sortie.setDate(null);bilanta.setText("");
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Les champs ne sont pas rempis correctement", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


}

