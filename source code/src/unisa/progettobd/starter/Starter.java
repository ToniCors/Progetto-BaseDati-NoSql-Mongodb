package unisa.progettobd.starter;
import unisa.progettobd.frame.Frame_Starter;

public class Starter {

	public static void main(String[] args) {
		
		//initial frame
		Frame_Starter initFrame = new Frame_Starter();
		initFrame.setSize(600,400);
		initFrame.setTitle("Progetto BD2");
		initFrame.setDefaultCloseOperation(Frame_Starter.EXIT_ON_CLOSE);
		initFrame.setResizable(false);
		initFrame.setVisible(true);
		
	}

}
