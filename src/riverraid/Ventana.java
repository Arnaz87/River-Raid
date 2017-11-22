package riverraid;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Ventana extends JFrame {

	Teclas teclas;
	
	public Ventana (Juego juego) {
		super("River Raid");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();
		
		container.add(juego);
		
		addKeyListener(teclas = new Teclas(juego));
		
		setResizable(false);
		pack();
		
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
}
