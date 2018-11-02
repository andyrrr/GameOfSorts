package me.gameOfSorts;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class GameOfSorts {
	/** Main del juego donde se crea el juego */
	public static void main(String[] args) {

		/** Crea la instancia del juego */
		Juego juego = new Juego();

		/** Inicializa la pantalla */
		JFrame pantalla = new JFrame(Juego.TITULO);

		/** Agrega el juego a la pantalla */
		pantalla.add(juego);

		/** Define los l�mites de la pantalla */
		pantalla.setSize(Juego.ANCHO, Juego.ALTURA);
		/** SetResizable para que no se pueda modificar */
		pantalla.setResizable(false);

		/** Le da el componente al panel para poder enfocarlo */
		pantalla.setFocusable(true);

		/** Agrega un WindowListener al frame */
		pantalla.addWindowListener(new WindowAdapter() {

			/** Override de un m�todo para cerrar la pantalla */
			public void windowClosing(WindowEvent e) {
				/** Imprime en la consola que se est� saliendo del juego */
				System.err.println("Saliendo del juego\n Ha salido del juego");
				/** Llama al m�todo quitar para detener la ejecuci�n en la consola */
				juego.quitar();
			}

		});

		/** Muestra el frame en el centro de la pantalla */
		pantalla.setLocationRelativeTo(null);
		/** Hace el frame visible, o sea, lo muestra */
		pantalla.setVisible(true);

		/** AC� SE INICIA EL JUEGO */
		juego.iniciar();

	}

}
