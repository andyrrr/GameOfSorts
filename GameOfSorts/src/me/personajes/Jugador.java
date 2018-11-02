package me.personajes;

import java.awt.event.KeyEvent;

import estados.GameState;
import input.KeyInputs;
import rendering.textures.Sprite;

public class Jugador extends Mob {

	public Jugador(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
		
		
	}

	@Override
	public void tick() {
		if (KeyInputs.isDown(KeyEvent.VK_DOWN)) {
			vY = 2;
		}
		if (KeyInputs.isDown(KeyEvent.VK_UP)) {
			vY = -2;
		}
		if (KeyInputs.isDown(KeyEvent.VK_RIGHT)) {
			vX = 2;
		}
		if (KeyInputs.isDown(KeyEvent.VK_LEFT)) {
			vX = -2;
		}

		if (KeyInputs.wasReleased(KeyEvent.VK_UP) || KeyInputs.wasReleased(KeyEvent.VK_DOWN)) {
			vY = 0;
		}
		if (KeyInputs.wasReleased(KeyEvent.VK_RIGHT) || KeyInputs.wasReleased(KeyEvent.VK_LEFT)) {
			vX = 0;
		}

		super.tick();
	}

}
