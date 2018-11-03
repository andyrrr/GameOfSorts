package game;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Jugador extends Sprite implements Variables {

	/** Coordenadas iniciales del jugador */
	private final int Y_INICIAL = 285;
	private final int X_INICIAL = 40;

	/** Imagen del jugador */
	private final String imagenJugador = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\grifo.png";
	/** altura de la imagen del jugador */
	private int altura;

	/** Inicia al constructor del jugador */
	public Jugador() {

		initPlayer();
	}

	/** Inicia al jugador */
	private void initPlayer() {

		/** Carga la imagen del jugador */
		ImageIcon ii = new ImageIcon(imagenJugador);

		/** Altura de la imagen */
		altura = ii.getImage().getHeight(null);

		/** Define la imagen del jugador */
		setImage(ii.getImage());
		/** Le define la coordenada en x para jugar */
		setX(X_INICIAL);
		/** Le define la coordenada en Y para jugar */
		setY(Y_INICIAL);
	}

	/** Método para mover al jugador */
	public void moverse() {

		/** Aumento del movimiento en X y Y */
		y += dY;
		x += dX;

		/** Para que el jugador no pueda salir de la pantalla por arriba */
		if (y <= 2) {
			y = 2;
		}

		/** Para que el jugador no pueda salir de la pantalla por abajo */
		if (y >= ALTURA_FRAME - 2 * altura) {
			y = ALTURA_FRAME - 2 * altura;
		}
		/** Para que el jugador no pueda salir de la pantalla por la izquierda */
		if (x <= 2) {
			x = 2;
		}
		/** Para que el jugador no pueda salir de la pantalla por la derecha */
		if (x >= ANCHO_FRAME - 2 * altura) {
			x = ANCHO_FRAME - 2 * altura;
		}

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {

			dY = -2;
		}

		if (key == KeyEvent.VK_DOWN) {

			dY = 2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dX = 2;
		}

		if (key == KeyEvent.VK_LEFT) {
			dX = -2;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {

			dY = 0;
		}

		if (key == KeyEvent.VK_DOWN) {

			dY = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dX = 0;
		}
		if (key == KeyEvent.VK_LEFT) {
			dX = 0;
		}
	}
}