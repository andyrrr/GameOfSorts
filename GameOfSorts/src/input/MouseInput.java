package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Clase para manejar los inputs del mouse */
public class MouseInput extends MouseAdapter {

	private static final boolean[] buttons = new boolean[10]; // botones del mouse
	private static final boolean[] lastButtons = new boolean[10]; // botones del mouse usados
	private static int x = -1, y = -1; // última posición del mouse
	private static int lastX = x, lastY = y; // última posición del mouse
	@SuppressWarnings("unused")
	private static boolean moving; // booleano si se está moviendo el mouse

	@Override
	/** Revisa cual botón del mouse se presionó */
	public void mousePressed(MouseEvent e) {
		System.out.println("Button: " + e.getButton()); // imprime el botón presionado
		buttons[e.getButton()] = true; // vuelve true el botón actual
	}

	@Override
	/** Revisa si se soltó el botón */
	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	@Override
	/** Revisa si se dio click */
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		System.out.println("Se ha dado click");
	}

	@Override
	/** Revisa si el mouse se está moviendo */
	public void mouseMoved(MouseEvent e) {
		x = e.getX(); // devuelve la posción en x del mouse en el frame
		y = e.getY(); // devuelve la posición en y del mouse en el frame
		// TODO: revisar
		moving = true;
	}

	/** Actualiza constantemente las acciones del mouse */
	public static void update() {
		for (int i = 0; i < 10; i++) {
			lastButtons[i] = buttons[i];
		}
		if (x == lastX && y == lastY) {
			moving = false;
		}
		lastX = x;
		lastY = y;
	}

	/** Revisa si se está presionando un botón del mouse */
	public static boolean isDown(int button) {
		return buttons[button];
	}

	/** Revisa si se presionó un botón */
	public static boolean wasPressed(int button) {
		return isDown(button) && !lastButtons[button];
	}

	/** Revisa si se soltó un botón */
	public static boolean wasReleased(int button) {
		return !isDown(button) && lastButtons[button];

	}

	/** Devuelve la posición en x del mouse */
	public static int getX() {
		return x;
	}

	/** Devuelve la posición en Y del mouse */
	public static int getY() {
		return y;
	}
}
