package riverraid;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sonido {
	
	private AudioClip kabum, laser, fuel, muerte;
	
	private AudioClip NuevoClip (String nombre) {
		URL url = this.getClass().getResource("sonidos/" + nombre);
		return Applet.newAudioClip(url);
	}
	
	public Sonido () {
		laser = NuevoClip("laser.aiff");
		kabum = NuevoClip("kabum.wav");
		fuel = NuevoClip("fuel.wav");
		muerte = NuevoClip("muerte.wav");
	}
	
	public void Explosion () {
		kabum.play();
	}
	
	public void Laser () {
		laser.play();
	}
	
	public void Fuel () {
		fuel.play();
	}
	
	public void Muerte () {
		muerte.play();
	}
}
