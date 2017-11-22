package riverraid;

public class Barco extends Enemigo {
	public Barco () {
		super("barco_izq.png", "barco_der.png", 116, 29);
		velocidad = 10;
		puntos = 5;
	}
}
