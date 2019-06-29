package unisa.progettobd.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import unisa.progettobd.result.Frame_PersonalizzataResult;
import unisa.progettobd.service.DatabaseManager;

public class Frame_QueryPersonalizzata extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private JCheckBox checkIscritti, checkLaureati;
	private JRadioButton radioUguale, radioMaggiore, radioMinore, radioNoneNumero;
	private JCheckBox check2014, check2015, check2016;
	private JTextField textNumStudenti, textNomeUni;
	private JComboBox<String> comboAggregazione, comboFilledUni, comboUni;
	private Vector<String> vectUni, vectFilledUni;
	private JCheckBox checkUni;
	private JButton buttAgg, buttRem;
	public Frame_QueryPersonalizzata() {
		super();


		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(5,0, 20, 20));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

		//FIRST PANEL -----------------------------------------------------------------
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(new GridLayout(2,0));

		JPanel radioPanelTipologia = new JPanel();
		radioPanelTipologia.setLayout(new GridLayout(0,3));

		checkIscritti = new JCheckBox("Iscritti");
		checkIscritti.setSelected(true);

		checkLaureati= new JCheckBox("Laureati");
		
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,0));
		
		comboAggregazione = new JComboBox<String>();
		comboAggregazione.addItem("Nessuno");
		comboAggregazione.addItem("Media");
		comboAggregazione.addItem("Somma");
		comboAggregazione.addItem("Minimo");
		comboAggregazione.addItem("Massimo");


		pan.add(new JLabel("Seleziona il tipo di aggregazione:"));
		pan.add(comboAggregazione);
		
		radioPanelTipologia.add(checkIscritti);
		radioPanelTipologia.add(checkLaureati);
		radioPanelTipologia.add(pan);

		firstPanel.add(new JLabel("Seleziona il tipo di ricerca:"));
		firstPanel.add(radioPanelTipologia);

		// ------------------------------- PANEL FOR THE CHECKBOXES (FIRST PANEL) -------------------------------
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new GridLayout(0,3));

		check2014 = new JCheckBox("2014");
		check2014.setSelected(true);

		check2015 = new JCheckBox("2015");
		check2015.setSelected(true);

		check2016 = new JCheckBox("2016");
		check2016.setSelected(true);

		checkPanel.add(check2014);
		checkPanel.add(check2015);
		checkPanel.add(check2016);

		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(2,0, 10, 10));
		secondPanel.add(new JLabel("Seleziona anno:"));
		secondPanel.add(checkPanel);


		// ------------------------ LABEL FOR THE RADIO BUTTONS (NUMBER OF STUDENTS, SECOND PANEL

		JPanel radioPanelNumStudenti = new JPanel();
		radioPanelNumStudenti.setLayout(new GridLayout(0,4));

		radioUguale = new JRadioButton("Uguale a:");	
		radioUguale.addActionListener(this);
		radioUguale.setActionCommand("stud_uguale");

		radioMaggiore = new JRadioButton("Maggiore di:");
		radioMaggiore.addActionListener(this);
		radioMaggiore.setActionCommand("stud_maggiore");

		radioMinore = new JRadioButton("Minore di:");		
		radioMinore.addActionListener(this);
		radioMinore.setActionCommand("stud_minore");

		radioNoneNumero = new JRadioButton("None");
		radioNoneNumero.addActionListener(this);
		radioNoneNumero.setActionCommand("stud_none");
		radioNoneNumero.setSelected(true);

		ButtonGroup radioGroupNumStudenti = new ButtonGroup();
		radioGroupNumStudenti.add(radioMinore);
		radioGroupNumStudenti.add(radioUguale);
		radioGroupNumStudenti.add(radioMaggiore);
		radioGroupNumStudenti.add(radioNoneNumero);

		radioPanelNumStudenti.add(radioMinore);
		radioPanelNumStudenti.add(radioUguale);
		radioPanelNumStudenti.add(radioMaggiore);
		radioPanelNumStudenti.add(radioNoneNumero);

		textNumStudenti = new JTextField();
		textNumStudenti.setEnabled(false);;

		JPanel thirdPanel = new JPanel();
		thirdPanel.setLayout(new GridLayout(3,0, 10, 10));
		thirdPanel.add(new JLabel("Seleziona il numero di studenti:"));
		thirdPanel.add(radioPanelNumStudenti);
		thirdPanel.add(textNumStudenti);

		// ----------------------------------- CODE OR NAME OF THE UNIVERSITY
		JPanel panelHeadUni = new JPanel();
		panelHeadUni.setLayout(new GridLayout(0,2));
		panelHeadUni.add(new JLabel("Selezione l'Università:"));
		
		checkUni = new JCheckBox("Attiva");
		checkUni.addActionListener(this);
		checkUni.setActionCommand("attiva_uni");
		
		panelHeadUni.add(checkUni);
		
		JPanel radioPanelUni = new JPanel();
		radioPanelUni.setLayout(new GridLayout(0,3, 10, 10));

		vectUni = getListUni();
		comboUni = new JComboBox<String>(vectUni);
		comboUni.setEnabled(false);
		vectFilledUni = new Vector<>();
		comboFilledUni = new JComboBox<String>(vectFilledUni);
		comboFilledUni.setEnabled(false);
		
		JPanel panelFillUni = new JPanel();
		panelFillUni.setLayout(new GridLayout(2, 0, 10, 10));

		buttAgg = new JButton(">>");
		buttAgg.addActionListener(this);
		buttAgg.setActionCommand("buttagg");
		buttAgg.setEnabled(false);
		
		buttRem = new JButton("<<");
		buttRem.addActionListener(this);
		buttRem.setActionCommand("buttrem");
		buttRem.setEnabled(false);
		panelFillUni.add(buttAgg);
		panelFillUni.add(buttRem);


		radioPanelUni.add(comboUni);
		radioPanelUni.add(panelFillUni);
		radioPanelUni.add(comboFilledUni);

		JPanel fourthPanel = new JPanel();
		fourthPanel.setLayout(new GridLayout(2,0, 10, 10));

		
		
		
		fourthPanel.add(panelHeadUni);
		fourthPanel.add(radioPanelUni);


		JButton buttVai = new JButton("Vai");
		buttVai.addActionListener(this);
		buttVai.setActionCommand("vai");

		contentPanel.add(firstPanel);
		contentPanel.add(secondPanel);
		contentPanel.add(thirdPanel);

		contentPanel.add(fourthPanel);
		contentPanel.add(buttVai);
		add(contentPanel);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();

		if(event.equals("vai")) {
			String queryNumero = "none";

			String tipoStudenti = "entrambi";
			String tipoAggregazione = comboAggregazione.getItemAt(comboAggregazione.getSelectedIndex());

			Boolean anno[] = new Boolean[3];


			int numero;

			try{
				numero = Integer.parseInt(textNumStudenti.getText());
			}catch(Exception x) {
				numero = 0;
			}


			anno[0] = anno[1] = anno[2] = false;

			if(check2014.isSelected()) anno[0] = true;
			if(check2015.isSelected()) anno[1] = true;
			if(check2016.isSelected()) anno[2] = true;


			if(radioUguale.isSelected()) queryNumero = "uguale";
			else if(radioMinore.isSelected()) queryNumero = "minore";
			else if(radioMaggiore.isSelected()) queryNumero = "maggiore";

			if(checkIscritti.isSelected() && checkLaureati.isSelected()) tipoStudenti = "entrambi";
			else if(checkIscritti.isSelected()) tipoStudenti = "iscritti";
			else if(checkLaureati.isSelected()) tipoStudenti = "laureati";

			
			Vector<String> tempVec = vectFilledUni;
			
			if(!checkUni.isSelected()) tempVec = null;
			else if(vectFilledUni.size() == 0) tempVec = null;
			
			Frame_PersonalizzataResult result = new Frame_PersonalizzataResult(tipoStudenti, tipoAggregazione, tempVec, anno, queryNumero, numero);
			result.setSize(1200,500);
			result.setTitle("Results");
			result.setVisible(true);
		}else {

			if(event.equals("attiva_uni")) {
				if(checkUni.isSelected()) {
					buttAgg.setEnabled(true);
					buttRem.setEnabled(true);
					comboUni.setEnabled(true);
					comboFilledUni.setEnabled(true);
				}else {
					buttAgg.setEnabled(false);
					buttRem.setEnabled(false);
					comboUni.setEnabled(false);
					comboFilledUni.setEnabled(false);
				}
			}
			
			if(event.equals("stud_maggiore")) {
				textNumStudenti.setEnabled(true);
			}else if(event.equals("stud_minore")) {
				textNumStudenti.setEnabled(true);
			}else if(event.equals("stud_uguale")) {
				textNumStudenti.setEnabled(true);
			}else if(event.equals("stud_none")) {
				textNumStudenti.setEnabled(false);
			}

			if(event.equals("uni_nome")) {
				textNomeUni.setEnabled(true);
			}else if(event.equals("uni_codice")) {
				textNomeUni.setEnabled(true);
			}else if(event.equals("uni_none")) {
				textNomeUni.setEnabled(false);
			}

			if(event.equals("buttagg")) {
				int index = comboUni.getSelectedIndex();

				if(index > -1) {
					String toAdd = comboUni.getItemAt(index);
					vectUni.remove(index);

					vectFilledUni.add(toAdd);
				}

				
			}else if(event.equals("buttrem")) {
				int index = comboFilledUni.getSelectedIndex();
				if(index > -1) {

					String toRem = comboFilledUni.getItemAt(index);
					
					vectFilledUni.remove(index);
					vectUni.add(toRem);
				}
				
			}
			
			if(vectUni.size() > 0) comboUni.setSelectedIndex(0);
			if(vectFilledUni.size() > 0) comboFilledUni.setSelectedIndex(0);
			
			comboUni.updateUI();
			comboFilledUni.updateUI();
			
		}


	}

	
	private Vector<String> getListUni(){
		HashMap<String, String> hash = new HashMap<>();
		
		MongoCollection<Document> coll = DatabaseManager.getIscritti();
		

		MongoCursor<Document> it = coll.find().iterator();

		while(it.hasNext()) {
			
			Document d = it.next();
			hash.put(d.getString("ATENEO_CODICE"), d.getString("ATENEO_NOME"));

		}
		
		Vector<String> list = new Vector<>();
		
		hash.forEach((k,v) -> list.add(k + "," + v));
		
		return list;
	}

}
