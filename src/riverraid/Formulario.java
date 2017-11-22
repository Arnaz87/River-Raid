package riverraid;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Formulario extends JFrame {
	
	private JTextField nombre;
	private JButton iniciar;
	
	private Menu menu;
	
	public Formulario (Menu menu) {
		super("Ingresar");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());
		container.add(new JLabel("Ingresa tu nombre"));
        container.add(nombre = new JTextField(10));
		container.add(iniciar = new JButton("Iniciar"));
		
		this.menu = menu;
		
		iniciar.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empezar();
            }
        });
		
		setResizable(false);
		setSize(380, 60);
		setLocationRelativeTo(null);
	}
	
	public void Empezar () {
		Jugador jugador = new Jugador(nombre.getText());

		Juego juego = new Juego(jugador);
		Ventana ventana = new Ventana(juego);
		
		setVisible(false);
		menu.setVisible(false);
		ventana.setVisible(true);
		
		juego.Iniciar();
	}
	
}