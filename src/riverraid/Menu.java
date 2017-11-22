package riverraid;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {
	
	private JLabel jugar, manual, puntaje, creditos, salir;
	
	Creditos creds;
	Manual man;
	Top top;
	
	public Menu () {
		super("River Raid");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = getContentPane();
		
		container.setLayout(new FlowLayout());
		
		container.add(jugar = new JLabel(" Jugar "));
		container.add(manual = new JLabel("Instrucciones"));
		container.add(puntaje = new JLabel("  Top 10  "));
		container.add(creditos = new JLabel(" Créditos "));
		container.add(salir = new JLabel("  Salir  "));
		
		Eventos ev = new Eventos();
		
		creds = new Creditos();
		man = new Manual();
		top = new Top();
		
		jugar.addMouseListener(ev);
		manual.addMouseListener(ev);
		puntaje.addMouseListener(ev);
		creditos.addMouseListener(ev);
		salir.addMouseListener(ev);
		
		setResizable(false);
		setSize(105, 123);
		setLocationRelativeTo(null);
	}
	
	private void Empezar () {
		Formulario formulario = new Formulario(this);
		formulario.setVisible(true);
	}
	
	private class Creditos extends JFrame {
		public Creditos () {
			super("Créditos");
			Container container = getContentPane();
			container.setLayout(new FlowLayout());
			container.add(new JLabel("Creador: Arnaud Castellanos Galea"));
			container.add(new JLabel("Basado en River Raid de Activision"));
			setResizable(false);
			setSize(300, 70);
			setLocationRelativeTo(null);
		}
	}
	
	private class Manual extends JFrame {
		public Manual () {
			super("Manual");
			Container container = getContentPane();
			container.setLayout(new FlowLayout());
			container.add(new JLabel("- Flechas Izquierda y Derecha para mover el avión"));
			container.add(new JLabel("- Flecha hacia arriba para acelerar"));
			container.add(new JLabel("- Espacio para acelerar"));
			setResizable(false);
			setSize(380, 90);
			setLocationRelativeTo(null);
		}
	}
	
	private class Top extends JFrame {
		public Top () {
			super("Top 10");
			Container container = getContentPane();
			container.setLayout(new FlowLayout());
			
			Jugador[] top = Jugador.Top10();
			
			for (Jugador jug : top) {
				String linea = "     ---     ";
				if (jug != null)
					linea = jug.toString();
				container.add(new JLabel(linea));
			}
			setResizable(false);
			setSize(105, 225);
			setLocationRelativeTo(null);
		}
	}
	
	private class Eventos implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent ev) {
			JLabel lbl = (JLabel) ev.getSource();
			if (lbl == jugar) Empezar();
			if (lbl == salir) System.exit(0);
			if (lbl == creditos) creds.setVisible(true);
			if (lbl == manual) man.setVisible(true);
			if (lbl == puntaje) top.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent ev) {
			JLabel lbl = (JLabel) ev.getSource();
			lbl.setForeground(Color.RED);
		}

		@Override
		public void mouseExited(MouseEvent ev) {
			JLabel lbl = (JLabel) ev.getSource();
			lbl.setForeground(Color.BLACK);
		}

		@Override
		public void mousePressed(MouseEvent ev) {  }
		@Override
		public void mouseReleased(MouseEvent ev) {  }
		
	}
}
