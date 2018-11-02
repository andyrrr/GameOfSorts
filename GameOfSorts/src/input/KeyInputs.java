package input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/** Clase para recibir del teclado */
public class KeyInputs extends KeyAdapter {

	/** Se define un número de teclas */
	private static final int NUM_KEYS = 256;

	/** Si las teclas se encuentran en este rango, se activa un boolean */
	private static final boolean[] keys = new boolean[256];
	private static final boolean[] lastKeys = new boolean[NUM_KEYS];

	/** Revisa si se presionó una tecla */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/** Revisa si se soltó la tecla */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/** Actualiza constantemente las teclas que se han presionado */
	public static void update() {
		for (int i = 0; i < NUM_KEYS; i++) {
			lastKeys[i] = keys[i];
		}
	}

	/** Revisa si se presionó una tecla */
	public static boolean wasPressed(int keyCode) {
		return isDown(keyCode) && !lastKeys[keyCode];
	}

	/** Revisa si se soltó la tecla */
	public static boolean wasReleased(int keyCode) {
		return !isDown(keyCode) && lastKeys[keyCode];
	}

	/** Revisa si se está presionando una tecla */
	public static boolean isDown(int keyCode) {
		return keys[keyCode];
	}
}
