package riverraid;

public class Combustible extends Objeto {

	public Combustible () {
		super("combustible.png", 26, 44);
	}

	public void Accion(Juego juego) {
		juego.Rellenar();
	}
	
	public void Destruir (Juego juego) {
		juego.puntos -= 10;
	}

}
