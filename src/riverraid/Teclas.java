package riverraid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclas implements KeyListener {
	
	Juego juego;
	
	public Teclas (Juego juego) {
		this.juego = juego;
	}
	
	@Override
	public void keyPressed(KeyEvent ev) {
		switch (ev.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			juego.izq = true;
			break;
		case KeyEvent.VK_RIGHT:
			juego.der = true;
			break;
		case KeyEvent.VK_UP:
			juego.acelerar = true;
			break;
		case KeyEvent.VK_SPACE:
			juego.Disparar();
			break;
		}
	} 

	@Override
	public void keyReleased(KeyEvent ev) {
		switch (ev.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			juego.izq = false;
			break;
		case KeyEvent.VK_RIGHT:
			juego.der = false;
			break;
		case KeyEvent.VK_UP:
			juego.acelerar = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent ev) { }

}
