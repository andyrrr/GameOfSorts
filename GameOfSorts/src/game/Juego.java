package game;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Juego extends JPanel implements Runnable, Variables {

	private Dimension d;
	private ArrayList<Dragon> dragones;
	private Jugador jugador;
	private Disparo disparo;

	private final int X_INICIAL_DRAGON = 300;
	private final int Y_INICIAL_DRAGON = 5;
	private int direccion = -1;
	private int direccionY = -1;
	private int muertes = 0;
	private ImageIcon fondo = new ImageIcon(
			"C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\landscape.png");

	private int numAlineacion = 1;
	private boolean jugando = true;
	private final String imagenExplosion = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\bomb - copia.png";
	private String mensaje = "Game Over";

	private Thread animator;

	/** Constructor del juego */
	public Juego() {
		initJuego();
	}

	/** Inicia el juego */
	private void initJuego() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		d = new Dimension(ANCHO_FRAME, ALTURA_FRAME);
		setSize(d);

		gameInit();
		setDoubleBuffered(true);
	}

	@Override
	public void addNotify() {

		super.addNotify();
		gameInit();
	}

	public void gameInit() {
		dragones = new ArrayList();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				Dragon dragon = new Dragon(X_INICIAL_DRAGON + 18 * j, Y_INICIAL_DRAGON + 18 * i, i + 1);
				dragones.add(dragon);
			}
		}

		/**
		 * Acá agrega los dragones a la lista y les asigna las coordenadas para la
		 * posicion en el frame
		 */

		jugador = new Jugador();
		disparo = new Disparo();

		if (animator == null || !jugando) {

			animator = new Thread(this);
			animator.start();
		}
	}

	public void dibujarDragones(Graphics g) {

		Iterator<?> it = dragones.iterator();

		for (Dragon dragon : dragones) {

			if (dragon.isVisible()) {

				g.drawImage(dragon.getImage(), dragon.getX(), dragon.getY(), this);
			}

			if (dragon.isMuriendo()) {
				dragon.matar();
				crearDragones();
			}
		}
	}

	public void dibujarJugador(Graphics g) {

		if (jugador.isVisible()) {

			g.drawImage(jugador.getImage(), jugador.getX(), jugador.getY(), this);
		}

		if (jugador.isMuriendo()) {

			jugador.matar();
			jugando = false;
		}
	}

	public void dibujarDisparo(Graphics g) {

		if (disparo.isVisible()) {
			g.drawImage(disparo.getImage(), disparo.getX(), disparo.getY(), this);
		}
	}

	public void dibujarFuego(Graphics g) {

		for (Dragon dragon : dragones) {

			Dragon.Fuego fuego = dragon.getFuego();

			if (!fuego.isDestruido()) {

				g.drawImage(fuego.getImage(), fuego.getX(), fuego.getY(), this);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (jugando) {
			dibujarDragones(g);
			dibujarJugador(g);
			dibujarDisparo(g);
			dibujarFuego(g);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void gameOver() {

		Graphics g = this.getGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, ANCHO_FRAME, ALTURA_FRAME);

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, ANCHO_FRAME / 2 - 30, ANCHO_FRAME - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, ANCHO_FRAME / 2 - 30, ANCHO_FRAME - 100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		GameOfSorts.areaDeTexto.setText("Game Over");
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(mensaje, (ANCHO_FRAME - metr.stringWidth(mensaje)) / 2, ANCHO_FRAME / 2);
	}

	public void cicloMovimiento() {

		if (muertes == NUM_DRAGONES_POR_MATAR) {

			jugando = false;
			mensaje = "Usted ha ganado!";
			GameOfSorts.areaDeTexto.setText("Usted ha ganado");
		}

		// MOVIMIENTO DEL JUGADOR
		jugador.moverse();

		// DISPARO DEL JUGADOR Y COLISION CON DRAGON DEL DISPARO
		if (disparo.isVisible()) {

			int disparoX = disparo.getX();
			int disparoY = disparo.getY();

			for (Dragon dragon : dragones) {

				int dragonX = dragon.getX();
				int dragonY = dragon.getY();

				if (dragon.isVisible() && disparo.isVisible()) {
					if (disparoX >= (dragonX) && disparoX <= (dragonX + ANCHO_DRAGON) && disparoY >= (dragonY)
							&& disparoY <= (dragonY + ALTURA_DRAGON)) {
						ImageIcon ii = new ImageIcon(imagenExplosion);
						dragon.setImage(ii.getImage());
						dragon.setMuriendo(true);
						disparo.matar();
						GameOfSorts.areaDeTexto.setText("Ha disparado a un dragon");
						muertes++;

					}
				}
			}

			int x = disparo.getX();
			x += 4;

			if (x > 535) {
				disparo.matar();
			} else {
				disparo.setX(x);
			}
		}

		// DRAGONES

		for (Dragon dragon : dragones) {

			if (dragon.isVisible()) {
				int x = dragon.getX();

				if (x <= 0 && direccion != -1) {
					dragon.setY(dragon.getY());
					jugando = false;
					mensaje = "Los dragones han cruzado la línea!";
				}

			}

		}

		Iterator<?> it = dragones.iterator();

		while (it.hasNext()) {

			Dragon dragon = (Dragon) it.next();

			if (dragon.isVisible()) {

				int y = dragon.getY();

				if (y <= 5 && direccionY != 1) {

					direccionY = 1;

					Iterator<?> i2 = dragones.iterator();

					while (i2.hasNext()) {

						Dragon a = (Dragon) i2.next();
						a.setX(a.getX() - 15);
					}
				}
				if (y >= 490 - ALTURA_DRAGON && direccionY != -1) {

					direccionY = -1;

					Iterator<?> i2 = dragones.iterator();

					while (i2.hasNext()) {

						Dragon a = (Dragon) i2.next();
						a.setX(a.getX() - 15);
					}
				}

				dragon.moverAutomaticamente(direccionY);

			}
		}

		// FUEGO
		Random generadorNum = new Random();

		for (Dragon dragon : dragones) {

			int disparos = generadorNum.nextInt(500);
			Dragon.Fuego fuego = dragon.getFuego();

			if (disparos == OPORTUNIDAD && dragon.isVisible() && fuego.isDestruido()) {

				fuego.setDestruido(false);
				fuego.setX(dragon.getX());
				fuego.setY(dragon.getY());
			}

			int fuegoX = fuego.getX();
			int fuegoY = fuego.getY();
			int jugadorX = jugador.getX();
			int jugadorY = jugador.getY();

			if (jugador.isVisible() && !fuego.isDestruido()) {

				if (fuegoX >= (jugadorX) && fuegoX <= (jugadorX + ANCHO_JUGADOR) && fuegoY >= (jugadorY)
						&& fuegoY <= (jugadorY + ALTURA_JUGADOR)) {
					ImageIcon ii = new ImageIcon(imagenExplosion);
					jugador.setImage(ii.getImage());
					jugador.setMuriendo(true);
					fuego.setDestruido(true);
				}
			}

			if (!fuego.isDestruido()) {

				fuego.setX(fuego.getX() - 1);

				if (fuego.getX() <= 0) {
					fuego.setDestruido(true);
				}
			}
		}
	}

	@Override
	public void run() {

		long beforeTime, diferenciaTiempo, sleep;

		beforeTime = System.currentTimeMillis();

		while (jugando) {

			repaint();
			cicloMovimiento();

			diferenciaTiempo = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - diferenciaTiempo;

			if (sleep < 0) {
				sleep = 2;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Error en el Thread del server");
			}

			beforeTime = System.currentTimeMillis();

		}

		gameOver();
	}

	private void crearDragones() {
		ArrayList<Dragon> dragones2 = new ArrayList<Dragon>();
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {

			jugador.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {

			jugador.keyPressed(e);

			int x = jugador.getX();
			int y = jugador.getY();

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_SPACE) {

				if (jugando) {
					disparo = new Disparo(x, y);
					GameOfSorts.areaDeTexto.setText("Ha disparado");
				}
			}
		}
	}
}