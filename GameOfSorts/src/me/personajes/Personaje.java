package me.personajes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import estados.GameState;
import rendering.textures.Sprite;

/**
 * Clase para TODOS los personajes que se usen. Ejemeplo: dragones, grifos, etc
 */
public abstract class Personaje {

	protected double x, y; // Coordenadas en x y y de los personajes
	protected Sprite sprite; // Sprite o imagen que se utilizará
	protected GameState state; // Define el estado del juego

	/** Constructor del personaje que se va a utilizar */
	public Personaje(Sprite sprite, double x, double y, GameState state) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.state = state;
		state.addPersonaje(this);
	}

	/** Actualización en pantalla */
	public abstract void tick();

	/** Con esto se dibuja al personaje */
	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		g.setColor(Color.RED);
		g.draw(getTop());
		g.setColor(Color.CYAN);
		g.draw(getBottom());
		g.setColor(Color.GREEN);
		g.draw(getLeft());
		g.setColor(Color.ORANGE);
		g.draw(getRight());
	}

	/** Devuelve las medidas del rectángulo */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
	}

	/***/
	public Rectangle getTop() {
		return new Rectangle((int) x + 4, (int) y, sprite.getWidth() - 6, 4);
	}

	/***/
	public Rectangle getBottom() {
		return new Rectangle((int) x + 6, (int) (y + sprite.getHeight() - 4), sprite.getWidth() - 6, 4);
	}

	/***/
	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 4, (int) y + 6, 4, sprite.getHeight() - 6);
	}

	/***/
	public Rectangle getLeft() {
		return new Rectangle((int) x, (int) y + 6, 4, sprite.getHeight() - 6);
	}

}
