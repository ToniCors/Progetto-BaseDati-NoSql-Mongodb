package unisa.progettobd.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame_Starter extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	public Frame_Starter() {
		super();		
		
		JButton buttRicercaAteneo = new JButton("Ricerca per ateneo");
		buttRicercaAteneo.setActionCommand("ateneo");
		buttRicercaAteneo.addActionListener(this);
		
		JButton buttRicercaAnno = new JButton("Ricerca per anno");
		buttRicercaAnno.setActionCommand("anno");
		buttRicercaAnno.addActionListener(this);
		
		JButton buttRicercaStudenti = new JButton("Ricerca per numero studenti");
		buttRicercaStudenti.setActionCommand("studenti");
		buttRicercaStudenti.addActionListener(this);
		
		JButton buttNewQuery = new JButton("Ricerca personalizzata");
		buttNewQuery.setActionCommand("personalizzata");
		buttNewQuery.addActionListener(this);

		// container of the buttons
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,0, 20, 20));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
		
		panel.add(buttRicercaAteneo);
		panel.add(buttRicercaAnno);
		panel.add(buttRicercaStudenti);
		panel.add(buttNewQuery);

		
		
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		

		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.add(new JLabel("Progetto BD2 - Ricerca laureati e iscritti 2014-2017"), BorderLayout.PAGE_START);
		contentPane.add(new JLabel("Marco Della Medaglia, Donato Di Sapia, Antonio Corsuto"), BorderLayout.PAGE_END);
		
		setContentPane(contentPane);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String toDo = e.getActionCommand();
		
		if(toDo.equals("ateneo")) {
			
			Frame_RicercaAteneo ateneo = new Frame_RicercaAteneo();
			ateneo.setSize(400,300);
			ateneo.setTitle("Ricerca per ateneo");
			ateneo.setResizable(false);
			ateneo.setVisible(true);
		}else if(toDo.equals("anno")) {
			
			Frame_RicercaAnno frameAnno = new Frame_RicercaAnno();
			frameAnno.setSize(400,300);
			frameAnno.setTitle("Ricerca per ateneo");
			frameAnno.setResizable(false);
			frameAnno.setVisible(true);
		}else if(toDo.equals("studenti")) {
			
			Frame_RicercaNumeroStudenti frameStudenti = new Frame_RicercaNumeroStudenti();
			frameStudenti.setSize(500,400);
			frameStudenti.setTitle("Ricerca per numero studenti");
			frameStudenti.setResizable(false);
			frameStudenti.setVisible(true);
		}else if(toDo.equals("personalizzata")){
			
			Frame_QueryPersonalizzata frameNewQuery = new Frame_QueryPersonalizzata();
			frameNewQuery.setSize(700,600);
			frameNewQuery.setTitle("Ricerca personalizzata");
			frameNewQuery.setResizable(false);
			frameNewQuery.setVisible(true);
		}
				
	}

}
