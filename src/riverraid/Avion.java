package riverraid;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;

import javax.swing.ImageIcon;

public class Avion {
	public int x;
	
	private final int ancho = 55;
	private final int alto = 40;
	private final int y = 10;
	
	private ImageIcon icono;
	
	public Avion () {
		URL url = this.getClass().getResource("imagenes/avion.png");
		icono = new ImageIcon(url);
	}
	
	public void Iniciar () {
		x = Juego.ANCHO/2 - ancho/2;
	}

	public void Pintar(Graphics g) {
		Rectangle r = getRect();
		g.drawImage(icono.getImage(), r.x, r.y, r.width, r.height, null);
	}
	
	public Rectangle getRect () {
		return new Rectangle(x-ancho/2, Juego.ALTO-alto-y, ancho, alto);
	}
}
