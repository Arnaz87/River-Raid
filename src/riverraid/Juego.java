package riverraid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel {
	
	/** Ancho de la pantalla */
	public static int ANCHO = 700;
	/** Alto de la pantalla */
	public static int ALTO = 400;
	/** Fotos por segundo */
	public static int FPS = 12;
	
	/** Ancho del rio en pixeles */
	public static int ANCHO_RIO = 400;
	/** Velocidad del avión a los lados */
	public static int VEL_X = 20;
	/** Velocidad de avance normal en pixeles por segundo */
	public static int VEL_Y = 10;
	/** Velocidad en pixeles por segundo en modo acelerado */
	public static int ACELERACION = 20;
	/** Segundos que dura el combustible */
	public static int TIEMPO_CONSUMO = 20;
	
	/** Distancia mínima de diferencia entre los enemigos inicial */
	public static int DISTANCIA_MINIMA = 200;
	
	public static int ANCHO_PARED = (ANCHO - ANCHO_RIO) / 2;
	
	/** Objetos actualmente en el mapa */
	private ArrayList<Objeto> objetos;
	/** Misiles  actualmente en el mapa */
	private ArrayList<Misil> misiles;
	/** Objetos que se eliminarán después del turno */
	private ArrayList<Objeto> objetosElim;
	
	Avion avion;
	
	Timer tiempo;
	Random random;
	
	Sonido sonido;

	public boolean izq, der, acelerar;
	
	private int vidas;
	public int puntos;
	private float combustible;
	private int velocidad;

	/** Distancia mínima de diferencia entre los enemigos. Decrece con el tiempo */
	private int distanciaMinima;
	/** Distancia al siguiente objeto en pixeles */
	private int siguienteObjeto;
	
	public Rectangle paredIzq, paredDer;
	
	private Jugador jugador;

	public Juego(Jugador jugador) {
		super();
		setSize(ANCHO, ALTO);
		setPreferredSize(getSize());
		
		this.jugador = jugador;
		
		sonido = new Sonido();
		
		paredIzq = new Rectangle(0, 0, ANCHO_PARED, ALTO);
		paredDer = new Rectangle(ANCHO-ANCHO_PARED, 0, ANCHO_PARED, ALTO);
		
		izq = false;
		der = false;
		acelerar = false;

		avion = new Avion();
		
		vidas = 3;
		puntos = 0;
		
		tiempo = new Timer(1000/FPS /*ms*/, new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent arg0) { Actualizar(); }
		});
		random = new Random();
		
		Iniciar();
	}
	
	/** Iniciar o reiniciar el juego */
	public void Iniciar () {
		distanciaMinima = DISTANCIA_MINIMA;
		siguienteObjeto = distanciaMinima;
		avion.Iniciar();
		objetos = new ArrayList<Objeto>();
		misiles = new ArrayList<Misil>();
		combustible = 1;
		tiempo.start();
	}
	
	public void GenerarObjeto () {
		Objeto objeto;
		int r = random.nextInt(100);
		
		if (r < 35) {
			objeto = new Combustible();
		} else if (r < 50) {
			objeto = new Helicoptero();
		} else {
			objeto = new Barco();
		}
		
		objetos.add(objeto);
		
		siguienteObjeto = distanciaMinima + random.nextInt(80);
		if (distanciaMinima > 0)
			distanciaMinima -= 3;
	}
	
	public void ActualizarObjetos () {
		for (Objeto objeto : objetos) {
			objeto.rect.y += velocidad;
			
			objeto.Actualizar(this);
			
			if (objeto.rect.y > ALTO)
				objetosElim.add(objeto);
			
			if (avion.getRect().intersects(objeto.rect)) {
				objeto.Accion(this);
				objetosElim.add(objeto);
			}
		}
	}
	
	public void ActualizarMisiles () {
		ArrayList<Misil> misilElim = new ArrayList<Misil>();
		for (Misil misil : misiles) {
			misil.y -= Misil.VEL;
			
			if (misil.y + Misil.ALTO < 0)
				misilElim.add(misil);
			
			for (Objeto objeto : objetos) {
				if (misil.getRect().intersects(objeto.rect)) {
					objeto.Destruir(this);
					sonido.Explosion();
					objetosElim.add(objeto);
					misilElim.add(misil);
				}
			}
		}
		misiles.removeAll(misilElim);
	}
	
	public void Actualizar () {
		
		int velx = 0;
		if (der && !izq) velx =  VEL_X;
		if (izq && !der) velx = -VEL_X;
		avion.x += velx;
		
		Rectangle arct = avion.getRect();
		if (paredIzq.intersects(arct) || paredDer.intersects(arct)) {
			Matar();
		}
		
		if (acelerar) {
			velocidad = ACELERACION;
		} else {
			velocidad = VEL_Y;
		}
		siguienteObjeto -= velocidad;
		
		if (siguienteObjeto <= 0) {
			GenerarObjeto();
		}
		combustible -= 1.0/(TIEMPO_CONSUMO * FPS);
		if (combustible <= 0.0) Matar();
		
		objetosElim = new ArrayList<Objeto>();
		ActualizarObjetos();
		ActualizarMisiles();
		objetos.removeAll(objetosElim);
		
		repaint();
	}
	
	public void Matar () {
		sonido.Muerte();
		if (vidas == 0) {
			tiempo.stop();
			jugador.setPuntaje(puntos);
			jugador.Guardar();
			System.exit(0);
		} else {
			vidas--;
			Iniciar();
		}
	}
	
	public void Rellenar () {
		combustible = 1;
		sonido.Fuel();
	}
	
	public void Disparar () {
		int pos = avion.x;
		Misil misil = new Misil(pos);
		misiles.add(misil);
		sonido.Laser();
	}
	
	public void paint (Graphics g) {
		// Césped
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, ANCHO, ALTO);
		// Río
		g.setColor(Color.BLUE);
		g.fillRect(ANCHO/2 - ANCHO_RIO/2, 0, ANCHO_RIO, ALTO);
		
		avion.Pintar(g);
		
		for (Objeto objeto : objetos) {
			objeto.Pintar(g);
		}
		
		for (Misil misil : misiles) {
			misil.Pintar(g);
		}
		
		g.setColor(Color.BLACK);
		g.drawString("Combustible: " + (int)(combustible*100) + "%", 10, 10);
		g.drawString("Vidas: " + vidas, 10, 20);
		g.drawString("Puntaje: " + puntos, 10, 30);
	}

}
