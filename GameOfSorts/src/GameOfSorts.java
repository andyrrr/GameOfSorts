import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

@SuppressWarnings("serial")

/** Clase GameOfSorts */
public class GameOfSorts extends Canvas implements Runnable {
	public static final String TITULO = "Game Of Sorts"; // T�tulo del juego
	public static final int ANCHO = 640; // Ancho de la pantalla
	public static final int ALTURA = ANCHO / 4 * 3; // Altura de la pantalla
	private boolean corriendo; // Esta variable nos permite usar un loop condicional del juego

	/***/
	private void tick() {

	}

	/** Renderizar es tomar todos los datos y mostrarlos en pantalla */
	private void renderizar() {

		/** Estas son ambas clases de Java */
		BufferStrategy buffer = getBufferStrategy(); // este m�todo es implementado de Canvas

		/** Si no hay un buffer, lo define */
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
		/** Graphics toma los gr�ficos enviados del buffer */
		Graphics g = buffer.getDrawGraphics();

		/////////////////////////////////////////////////////

		g.setColor(Color.RED);
		g.fillRect(0, 0, ANCHO, ALTURA);

		////////////////////////////////////////////////////

		/** Hace un dispose de la cola interna de gr�ficos ya dibujados */
		g.dispose();

		/** Muestra en pantalla las actualizaciones del buffer */
		buffer.show();

	}

	/** Inicia el loop del juego */
	private void iniciar() {
		/** Si ya est� corriendo, permanece as� */
		if (corriendo)
			return;
		/** Si no est� corriendo, lo vuelve True y lo inicia */
		corriendo = true;
		/**
		 * Se usa this, "GameOfSorts-ThreadPrincipal" para hacer m�s f�cil el debugging
		 * de los hilos del juego
		 */
		new Thread(this, "GameOfSorts-ThreadPrincipal").start();
	}

	/** Detiene la ejecuci�n del programa */
	private void quitar() {
		/** Si no est� corriendo, permanece as� */
		if (!corriendo)
			return;

		/** Si est� corriendo, lo vuelve False y lo detiene */
		corriendo = false;
		System.exit(0); // termina la ejecuci�n del programa
	}

	/** Este es el loop del juego */
	@Override
	public void run() {
		/** Estas son variables para actualizaci�n de frames */
		double frames = 60.0; // 60 fps
		double nanosegundosPorTick = 1000000000.0 / frames; // define los nanosegundos
		long ultimaVez = System.nanoTime(); // Define la �ltima vez que se actualiz� el frame
		long temporizador = System.currentTimeMillis(); // temporizador del juego
		double noProcesado = 0.0; // Es el tiempo que no se ha actualizado
		int fps = 0; // contador de los fps
		int tps = 0; // ticks que realiza el frame por segundo

		/** Ac� se limitan los frames */
		boolean Render = false;
		System.out.println("Se inici� el m�todo run(). ");

		while (corriendo) {
			/** Define el tiempo actual que lleva corriendo */
			long ahora = System.nanoTime();
			/** Define el tiempo no procesado el buffer */
			noProcesado += (ahora - ultimaVez) / nanosegundosPorTick;
			ultimaVez = ahora;

			/** Se hace un tick cuando */
			if (noProcesado >= 1.0) {
				tick(); // Llama al m�todo tick
				/** Disminuye el tiempo en el que no se ha actualizado */
				noProcesado--;
				/** Aumenta los ticks por segundo realizados */
				tps++;
				/** Se actualiza o se hace una renderizaci�n */
				Render = true;

			} else {
				/** No actualiza la pantalla */
				Render = false;
			}

			/** Esto es un peque�o delay de un segundo en el Thread */
			try {
				Thread.sleep(1);
				/** Atrapa la excepci�n al dormir el Thread */
			} catch (InterruptedException excepcion) {
				System.out.println("Error en run()");
			}

			/** Ac� se hace un if render = true se actualiza la imagen */
			if (Render) {
				/** Llama a la funci�n que actualiza el frame */
				renderizar();
				/** suma a los frames por segundo */
				fps++;
			}

			/** Se usa mil porque hay mil milisegundos en un segundo */
			if (System.currentTimeMillis() - 1000 > temporizador) {
				/** Se le suma un segundo al temporizador */
				temporizador += 1000;
				/** Se imprimen los frames por segundo */
				System.out.println("FPS: " + fps + "| TPS: " + tps + "\n");
				fps = 0; // inicializa de nuevo los frames por segundo
				tps = 0; // inicializa los ticks por segundo
			}

		}

		/** Termina de ejecutar el programa */
		System.exit(0);

	}

	/** Main del juego donde se crea el juego */
	public static void main(String[] args) {
		/** Crea la instancia del juego */
		GameOfSorts juego = new GameOfSorts();

		/** Inicializa la pantalla */
		JFrame pantalla = new JFrame(TITULO);

		/** Agrega el juego a la pantalla */
		pantalla.add(juego);

		/** Define los l�mites de la pantalla */
		pantalla.setSize(ANCHO, ALTURA);
		/** SetResizable para que no se pueda modificar */
		pantalla.setResizable(false);

		/** Le da el componente al panel para poder enfocarlo */
		pantalla.setFocusable(true);

		/** Agrega un WindowListener al frame */
		pantalla.addWindowListener(new WindowAdapter() {

			/** Override de un m�todo para cerrar la pantalla */
			public void windowClosing(WindowEvent e) {
				/** Imprime en la consola que se est� saliendo del juego */
				System.err.println("Saliendo del juego");
				/** Llama al m�todo quitar para detener la ejecuci�n en la consola */
				juego.quitar();
			}

		});

		/** Muestra el frame en el centro de la pantalla */
		pantalla.setLocationRelativeTo(null);
		/** Hace el frame visible, o sea, lo muestra */
		pantalla.setVisible(true);
		/** Pide el tipo de enfoque asignado */
		pantalla.requestFocus();

		/** AC� SE INICIA EL JUEGO */
		juego.iniciar();
		Logger logger = Logger.getLogger("Juego");
		logger.log(Level.INFO, "Inicia el juego");

	}

}