package estados;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import input.KeyInputs;
import input.MouseInput;
import me.gameOfSorts.Juego;
import rendering.ui.Button;
import utilidades.Fonts;

/** Clase para el menú */
public class EstadoMenu implements Estado {

	private Button[] options; // opcion 0, 1 o 2
	private int currentSelection; // selección actual

	/** Si se selecciona una opción, hace lo siguiente */
	private void select(ManejadorEstados estadoManager) {
		switch (currentSelection) {
		/** Primer caso */
		case 0:
			System.out.println("Jugar");
			estadoManager.setEstado("nivel1");
			break;
		/** Segundo caso */
		case 1:
			System.out.println("Opciones");
			break;
		/** Tercer caso */
		case 2:
			System.out.println("Salir");
			Juego.INSTANCIA.quitar(); // llama a la función salir del juego
			break;
		}
	}

	/** Con esto se grafica el texto del menú */
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK); // Devuelve el color del fondo
		g.fillRect(0, 0, Juego.ANCHO, Juego.ALTURA); // se dibuja un cuadrado alrededor de las letras

		/** Con esto se dibujan las letras */
		Fonts.drawString(g, new Font("Arial", Font.BOLD, 72), Color.WHITE, "Game Of Sorts", 80);

		/** Al navegar por las opciones */
		for (int i = 0; i < options.length; i++) {
			if (i == currentSelection) {
				options[i].setSelected(true);
			} else {
				options[i].setSelected(false);
			}
			options[i].render(g);
		}
	}

	@Override
	/**
	 * Se llama la función para dibujar los botones, es decir, escribir las opciones
	 * en pantalla
	 */
	public void init() {
		options = new Button[3];
		options[0] = new Button("Jugar", 200 + 0 * 80, new Font("Arial", Font.PLAIN, 32),
				new Font("Arial", Font.BOLD, 48), Color.WHITE, Color.YELLOW);

		options[1] = new Button("Opciones", 200 + 1 * 80, new Font("Arial", Font.PLAIN, 32),
				new Font("Arial", Font.BOLD, 48), Color.WHITE, Color.YELLOW);

		options[2] = new Button("Salir", 200 + 2 * 80, new Font("Arial", Font.PLAIN, 32),
				new Font("Arial", Font.BOLD, 48), Color.WHITE, Color.YELLOW);

	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(ManejadorEstados estadoManager) {
		boolean click = false;
		for (int i = 0; i < options.length; i++) {
			/** Se le asigna un rectángulo al mouse */
			if (options[i].intersects(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
				currentSelection = i;
				click = MouseInput.wasPressed(MouseEvent.BUTTON1);
			}
		}

		/** Permite navegar con las flechas */
		if (KeyInputs.wasPressed(KeyEvent.VK_UP)) {
			currentSelection--; /** Se resta a las posiciones */
			if (currentSelection < 0) { /** Si la selección actual es menor a 0 */
				currentSelection = options.length - 1; /** Para navegar por los botones */
			}
		}

		/** Permite navegar con las flechas */
		if (KeyInputs.wasPressed(KeyEvent.VK_DOWN)) {
			currentSelection++;
			if (currentSelection >= options.length) {
				currentSelection = 0;
			}
		}

		/**
		 * Si se da click, selecciona a donde está intersecando el rectángulo asignado
		 */
		if (click || KeyInputs.wasPressed(KeyEvent.VK_ENTER)) {
			select(estadoManager);
		}

	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	/** Devuelve el nombre del estado, en este caso, menu */
	public String getName() {
		return "menu";
	}

}
