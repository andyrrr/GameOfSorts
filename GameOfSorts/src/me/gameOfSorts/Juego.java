package me.gameOfSorts;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import estados.EstadoMenu;
import estados.GameState;
import estados.ManejadorEstados;
import input.KeyInputs;
import input.MouseInput;
import rendering.textures.Texture;

@SuppressWarnings("serial")

/** Clase GameOfSorts */
public class Juego extends Canvas implements Runnable {
	public static final String TITULO = "Game Of Sorts"; // T�tulo del juego
	public static final int ANCHO = 1024; // Ancho de la pantalla
	public static final int ALTURA = 520; // Altura de la pantalla
	private boolean corriendo; // Esta variable nos permite usar un loop condicional del juego
	private Texture fondo; // Objeto de la clase que importa im�genes
	private int newX = 0;
	protected final int speed = 20;

	private ManejadorEstados estadoManager; // define el estado actual del juego, niveles, etc

	public static Juego INSTANCIA; // crea el juego

	/***/
	private void tick() {
		/** Est� constantemente llamando y revisando el estado del juego */
		estadoManager.tick();

		/** Si se presiona la tecla de arriba */
		if (KeyInputs.isDown(KeyEvent.VK_UP)) {
			System.out.println("Se presion� la tecla de arriba");
		}

		/** Si se presiona la tecla de abajo */
		if (KeyInputs.isDown(KeyEvent.VK_DOWN)) {
			System.out.println("Se presion� la tecla de abajo");
		}

		/** Si se presiona la tecla de la derecha */
		if (KeyInputs.isDown(KeyEvent.VK_RIGHT)) {
			System.out.println("Se presion� la tecla derecha");
		}

		/** Si se presiona la tecla de la izquierda */
		if (KeyInputs.isDown(KeyEvent.VK_LEFT)) {
			System.out.println("Se presion� la tecla izquierda");
		}

		if (MouseInput.isDown(MouseEvent.BUTTON3)) {
			System.out.println("Se ha presionado la tecla derecha del Mouse");
		}

	}

	/** CONSTRUCTOR DE LA CLASE */
	public Juego() {
		fondo = new Texture("fondo - copia"); // ac� se escribe el nombre de la imagen
		addKeyListener(new KeyInputs()); // Se le agrega el input de las teclas
		MouseInput input = new MouseInput(); // se crea un nuevo MouseInput
		addMouseListener(input); // agrega el mouseListener a la pantalla
		addMouseMotionListener(input); // se agrega el MML al frame
		estadoManager = new ManejadorEstados(); // se crea el estado actual del juego
		INSTANCIA = this; // se crea la nueva instancia con la actual

		estadoManager.agregarEstado(new EstadoMenu()); // INICIA CON EL MENU
		estadoManager.agregarEstado(new GameState()); // Entra ac� cuando se escoge jugar
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
		Graphics graficos = buffer.getDrawGraphics();

		Graphics2D graficos2 = (Graphics2D) graficos;
		graficos2.translate(-6, -28);

		/////////////////////////////////////////////////////

		graficos2.setColor(Color.BLUE);
		graficos2.fillRect(0, 0, ANCHO, ALTURA);
		fondo.render(graficos, newX, 0);
		estadoManager.render(graficos2);

		// int x = 800;
		// int y = 200;
		// int z;
		// for (int i = 0; i < 5; i++) {
		// x += 70;
		// for (int j = i; j <= i; j++) {
		// y += 60;
		// }
		// z = y - 120 * (i);

		////////////////////////////////////////////////////

		/** Hace un dispose de la cola interna de gr�ficos ya dibujados */
		graficos.dispose();

		/** Muestra en pantalla las actualizaciones del buffer */
		buffer.show();

	}

	/** Inicia el loop del juego */
	void iniciar() {
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
	public void quitar() {
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
		/** No hay necesidad de darle click a la ventana para que act�e */
		requestFocus();
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
			newX = (newX == -3040) ? 0 : fondo.getImage().getWidth() - speed;
			/** Define el tiempo actual que lleva corriendo */
			long ahora = System.nanoTime();
			/** Define el tiempo no procesado el buffer */
			noProcesado += (ahora - ultimaVez) / nanosegundosPorTick;
			ultimaVez = ahora;

			/** Se hace un tick cuando */
			if (noProcesado >= 1.0) {
				tick(); // Llama al m�todo tick
				KeyInputs.update();
				MouseInput.update();
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

}