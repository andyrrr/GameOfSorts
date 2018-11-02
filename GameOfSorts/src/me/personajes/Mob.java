package me.personajes;

import GoS.mundo.Tile;

import estados.GameState;
import rendering.textures.Sprite;

/** Mob es corto para mobile entity */
public abstract class Mob extends Personaje {

	/** Velocidad en X y Y */
	protected double vX, vY;

	/***/
	public Mob(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
	}

	@Override
	public void tick() {
		move();
	}

	/** Cambia de posición */
	public void move() {
		if (!hasHorizontalCollision()) {
			x += vX;
		}
		if (!hasVerticalCollision()) {
			y += vY;
		}
	}

	/***/
	protected boolean hasVerticalCollision() {
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (getBounds().intersects(t.getTop()) && vY > 0) {
				vY = 0;
				return true;
			}
			if (getBounds().intersects(t.getBottom()) && vY < 0) {
				return true;
			}
		}
		return false;
	}

	/***/
	protected boolean hasHorizontalCollision() {
		for (int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if (getBounds().intersects(t.getRight()) && vX < 0) {
				vX = 0;
				return true;
			}
			if (getBounds().intersects(t.getLeft()) && vX > 0) {
				return true;
			}
		}
		return false;
	}

}
