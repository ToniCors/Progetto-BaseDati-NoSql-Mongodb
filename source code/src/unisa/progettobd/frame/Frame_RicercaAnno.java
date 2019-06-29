package unisa.progettobd.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import unisa.progettobd.result.Frame_AnnoResult;

public class Frame_RicercaAnno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboAnni;
	private JRadioButton radioIscritti, radioLaureati;
	
	public Frame_RicercaAnno() {
		super();
			
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
		contentPane.setLayout(new GridLayout(4,0, 13, 13));
	
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,2));
		
		radioIscritti = new JRadioButton("Iscritti");
		radioIscritti.setSelected(true);
		
		radioLaureati = new JRadioButton("Laureati");
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioIscritti);
		radioGroup.add(radioLaureati);
		
		radioPanel.add(radioIscritti);
		radioPanel.add(radioLaureati);		
		
		// ----------------------------------------------------------
		
		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(new GridLayout(2,0));
		
		comboAnni = new JComboBox<String>();
		comboAnni.addItem("2014");
		comboAnni.addItem("2015");
		comboAnni.addItem("2016");
		
		comboAnni.setSelectedIndex(0);
		
		secondPanel.add(new JLabel("Seleziona l'anno da ricercare:"));
		secondPanel.add(comboAnni);
		
		//----------------------------------------------------------------------------------
		JButton buttVai = new JButton("Vai");
		buttVai.addActionListener(this);
		
		
		contentPane.add(new JLabel("Seleziona il tipo di ricerca:"));
		contentPane.add(radioPanel);;
		contentPane.add(secondPanel);
		contentPane.add(buttVai);
		setContentPane(contentPane);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String tipo = "iscritti";
		String anno = comboAnni.getItemAt(comboAnni.getSelectedIndex());
		
		if(radioIscritti.isSelected()) tipo = "iscritti";
		else if(radioLaureati.isSelected()) tipo = "laureati";
		else tipo = "confronto";
		
		Frame_AnnoResult result = new Frame_AnnoResult(tipo, anno);
		result.setSize(1200,500);
		result.setTitle("Results");
		result.setVisible(true);
		
		
		
	}
	
}

