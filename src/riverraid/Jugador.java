package riverraid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Jugador {
	public static final String ARCHIVO = "usuarios.txt";
	
	private String nombre;
	private int puntaje;
	
	public Jugador (String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
	}
	
	public Jugador (String nombre, int puntaje) {
		this.nombre = nombre;
		this.puntaje = puntaje;
	}
	
	public void setPuntaje (int puntaje) {
		this.puntaje = puntaje;
	}
	
	public void Guardar () {
		try {
			FileWriter fw = new FileWriter(ARCHIVO, true);
	        BufferedWriter buf = new BufferedWriter(fw);
	        PrintWriter writer = new PrintWriter(buf);
	        writer.println(nombre + "-" + puntaje);
	        writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toString () {
		return nombre + " - " + puntaje;
	}
	
	public static Jugador[] Top10 () {
		try {
			ArrayList<Jugador> jugadores = new ArrayList();
			FileReader fr = new FileReader(ARCHIVO);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = br.readLine();
			while(linea != null) {
				String[] tokens = linea.split("-");
				Jugador jugador = new Jugador(tokens[0], Integer.parseInt(tokens[1]));
				jugadores.add(jugador);
				linea = br.readLine();
			}
			
			Jugador[] top = new Jugador[10];
			int cont = 0;
			
			while (cont < top.length && jugadores.size() > 0) {
				Jugador max = null;
				for (Jugador jug : jugadores) {
					if (max == null || jug.puntaje > max.puntaje) {
						max = jug;
					}
				}
				top[cont] = max;
				jugadores.remove(max);
				cont++;
			}
			
			return top;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
