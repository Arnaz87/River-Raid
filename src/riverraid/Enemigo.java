package riverraid;

import java.net.URL;

import javax.swing.ImageIcon;

public class Enemigo extends Objeto {

	/** Marca si el enemigo se mueve a la izquierda (1) o a la derecha (2)*/
	private int sentido = 1;
	private ImageIcon img_izq, img_der;
	/** Tiempo en que el enemigo empezará a moverse */
	private int tiempo = 10;
	
	protected int velocidad = 10;
	protected int puntos = 5;
	
	public Enemigo (String img_izq, String img_der, int ancho, int alto) {
		super(ancho, alto);
		URL url_izq = this.getClass().getResource("imagenes/" + img_izq);
		URL url_der = this.getClass().getResource("imagenes/" + img_der);
		this.img_izq = new ImageIcon(url_izq);
		this.img_der = new ImageIcon(url_der);
		tiempo = random.nextInt(20);
		if (rect.x < (Juego.ANCHO/2 - ancho/2)) {
			Sentido(2);
		} else {
			Sentido(1);
		}
	}
	
	@Override
	public void Accion(Juego juego) {
		juego.Matar();
	}
	
	public void setPos (int pos) {
		rect.x = pos;
		
		if (pos < Juego.ANCHO) {
			Sentido(1);
		} else {
			Sentido(2);
		}
	}
	
	private void Sentido (int sentido) {
		this.sentido = sentido;
		switch (sentido) {
			case 1:
				icono = img_izq;
				break;
			case 2:
				icono = img_der;
				break;
		}
	}
	
	public void Actualizar (Juego juego) {
		if (tiempo > 0) {
			tiempo--;
		} else {
			if (rect.intersects(juego.paredIzq)) {
				Sentido(2);
			} else if (rect.intersects(juego.paredDer)) {
				Sentido(1);
			}
			switch (sentido) {
				case 1:
					rect.x -= velocidad;
					break;
				case 2:
					rect.x += velocidad;
					break;
			}
		}
	}
	
	public void Destruir (Juego juego) {
		juego.puntos += 15;
	}

}
