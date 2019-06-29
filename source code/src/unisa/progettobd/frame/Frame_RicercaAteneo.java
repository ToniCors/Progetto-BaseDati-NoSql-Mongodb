package unisa.progettobd.frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import unisa.progettobd.result.Frame_AteneoResult;

public class Frame_RicercaAteneo extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JRadioButton radioIscritti, radioLaureati, radioConfronto;
	private JRadioButton radioCodice, radioNome;
	public Frame_RicercaAteneo() {
		super();
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(5,0, 15, 15));
		contentPane.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));


		JPanel radioPanelTipologia = new JPanel();
		radioPanelTipologia.setLayout(new GridLayout(0,2));

		radioIscritti = new JRadioButton("Iscritti");
		radioIscritti.setSelected(true);

		radioLaureati = new JRadioButton("Laureati");
		radioConfronto = new JRadioButton("Confronto");

		ButtonGroup radioGroupTipologia = new ButtonGroup();
		radioGroupTipologia.add(radioIscritti);
		radioGroupTipologia.add(radioConfronto);
		radioGroupTipologia.add(radioLaureati);

		radioPanelTipologia.add(radioIscritti);
		radioPanelTipologia.add(radioLaureati);



		radioCodice = new JRadioButton("Codice");
		radioCodice.setSelected(true);
		radioCodice.setActionCommand("codice");
		radioCodice.addActionListener(this);

		radioNome = new JRadioButton("Nome");
		radioNome.setActionCommand("nome");
		radioNome.addActionListener(this);

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(radioCodice);
		radioGroup.add(radioNome);

		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,2));
		radioPanel.add(radioCodice);
		radioPanel.add(radioNome);


		text = new JTextField("Write the code of the University");

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
		String command = e.getActionCommand();

		if(command.equals("codice")) {
			text.setText("Write the code of the University");
			text.selectAll();

			text.requestFocusInWindow();

		}else if(command.equals("nome")) {
			text.setText("Write the name of the University");
			text.selectAll();
			text.requestFocusInWindow();

		}else if(command.equals("vai")) {
			String iscrittiImmatricolati = "iscritti";
			String toSearch;
			char c = 'n';
			
			if(radioCodice.isSelected()) c = 'c';

			
			if(radioLaureati.isSelected()) iscrittiImmatricolati = "laureati";
			else if(radioConfronto.isSelected()) iscrittiImmatricolati = "confronto";

			toSearch = text.getText();

			Frame_AteneoResult resultFrame = new Frame_AteneoResult(iscrittiImmatricolati, c, toSearch);
			resultFrame.setSize(1500,500);
			resultFrame.setTitle("Results");
			resultFrame.setVisible(true);
		}

	}

}
