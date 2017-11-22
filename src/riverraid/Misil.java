package riverraid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Misil {
	public int x;
	public int y;
	
	public static int ALTO = 30;
	public static int ANCHO = 5;
	public static int VEL = 30;
	
	Misil (int pos) {
		y = Juego.ALTO - ALTO;
		x = pos - ANCHO/2;
	}
	
	public void Pintar (Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, ANCHO, ALTO);
	}
	
	public Rectangle getRect () {
		return new Rectangle(x, y, ANCHO, ALTO);
	}
}
