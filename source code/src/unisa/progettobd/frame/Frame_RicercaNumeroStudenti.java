package unisa.progettobd.frame;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import unisa.progettobd.result.Frame_StudentiResult;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_RicercaNumeroStudenti extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField text;

	private JRadioButton radioIscritti, radioLaureati;
	private JRadioButton radioUguale, radioMaggiore, radioMinore;
	
	public Frame_RicercaNumeroStudenti() {
		super();

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(5,0, 15, 15));
		contentPane.setBorder(BorderFactory.createEmptyBorder(50, 60, 60, 60));
	
		JPanel radioPanelTipologia = new JPanel();
		radioPanelTipologia.setLayout(new GridLayout(0,3));
		
		radioIscritti = new JRadioButton("Iscritti");
		radioIscritti.setSelected(true);
		
		radioLaureati = new JRadioButton("Laureati");
		
		ButtonGroup radioGroupTipologia = new ButtonGroup();
		radioGroupTipologia.add(radioIscritti);
		radioGroupTipologia.add(radioLaureati);
		
		radioPanelTipologia.add(radioIscritti);
		radioPanelTipologia.add(radioLaureati);
		
			
		
		radioUguale = new JRadioButton("Uguale a:");
		radioUguale.setSelected(true);
		
		radioMaggiore = new JRadioButton("Maggiore di:");
		radioMinore = new JRadioButton("Minore di:");
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioMinore);
		radioGroup.add(radioUguale);
		radioGroup.add(radioMaggiore);
		
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,3));
		radioPanel.add(radioMaggiore);
		radioPanel.add(radioUguale);
		radioPanel.add(radioMinore);
		
		text = new JTextField("0");
		
		JButton button = new JButton("Vai");
		button.setActionCommand("vai");
		button.addActionListener(this);
		
		contentPane.add(new JLabel("Seleziona il tipo di ricerca:"));
		contentPane.add(radioPanelTipologia);
		contentPane.add(radioPanel);
		contentPane.add(text);
		contentPane.add(button);
		
		setContentPane(contentPane);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String tipo = "iscritti";
		String queryNumero = "uguale";
		int numero = Integer.parseInt(text.getText());
		
		if(radioIscritti.isSelected()) tipo = "iscritti";
		else if(radioLaureati.isSelected()) tipo = "laureati";
		else tipo = "confronto";
		
		if(radioUguale.isSelected()) queryNumero = "uguale";
		else if(radioMinore.isSelected()) queryNumero = "minore";
		else queryNumero = "maggiore";
		
		Frame_StudentiResult result = new Frame_StudentiResult(tipo, queryNumero, numero);
		result.setSize(1200,500);
		result.setTitle("Results");
		result.setVisible(true);
		
	}
	
}
