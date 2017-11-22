package riverraid;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Random;

import javax.swing.ImageIcon;

public abstract class Objeto {
	protected ImageIcon icono;
	
	public Rectangle rect;
	
	Random random;
	
	Objeto (int ancho, int alto) {
		random = new Random();
		int x = Juego.ANCHO_PARED + random.nextInt(Juego.ANCHO_RIO - ancho);
		rect = new Rectangle(x, -alto, ancho, alto);
	}
	
	Objeto (String nombreIcono, int ancho, int alto) {
		this(ancho, alto);
		URL url = this.getClass().getResource("imagenes/" + nombreIcono);
		icono = new ImageIcon(url);
	}
	
	public void setPos (int pos) {
		rect.x = pos;
	}
	
	public void Pintar (Graphics g) {
		g.drawImage(icono.getImage(), rect.x, rect.y, rect.width, rect.height, null);
	}
	public abstract void Accion (Juego juego);
	
	public void Actualizar (Juego juego) {}
	
	public void Destruir (Juego juego) {}
}
