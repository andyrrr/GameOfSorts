package game;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.ImageIcon;


public class Dragon extends Sprite {
	String nombre;
	String apellido;
	int recarga;
	int edad;
	int resistencia;

	/** Crea un objeto fuego para el drag�n */
	private Fuego fuego;
	/** Ac� se carga la imagen del drag�n */
	private final String imagenDragon = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\dragonPrueba - copia.png";

	/** Constructor de la clase drag�n */
	public Dragon(int x, int y, int id) {
		initDragon(x, y);
	}

	/** Iniciar al drag�n */
	private void initDragon(int x, int y) {

		/** Le define la posici�n en X y Y */
		this.x = x;
		this.y = y;

		/** Crea el nuevo objeto de fuego */
		fuego = new Fuego(x, y);
		/** Carga la imagen del drag�n */
		ImageIcon imagen = new ImageIcon(imagenDragon);
		setImage(imagen.getImage());
	}

	/** Para que los dragones se muevan por su cuenta */
	public void moverAutomaticamente(int directionY) {
		/** Le aumenta en Y la direcci�n (Esta va cambiando) */
		this.y += directionY;
	}

	/** Devuelve al objeto fuego */
	public Fuego getFuego() {

		return fuego;
	}

	/** Crea la clase fuego para el drag�n */
	public class Fuego extends Sprite {

		/** Carga la imagen del fuego para el drag�n */
		private final String imagenFuego = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\bomb - copia.png";
		private boolean destruido;

		/** Constructor para crear al fuego con las coordenadas en X y y */
		public Fuego(int x, int y) {

			initFuego(x, y);
		}

		/** Ac� se inicia el fuego */
		private void initFuego(int x, int y) {

			setDestruido(true);
			this.x = x;
			this.y = y;
			ImageIcon ii = new ImageIcon(imagenFuego);
			setImage(ii.getImage());

		}

		/** Set para el fuego */
		public void setDestruido(boolean destruido) {

			this.destruido = destruido;
		}

		/***/
		public boolean isDestruido() {

			return destruido;
		}
	}
}