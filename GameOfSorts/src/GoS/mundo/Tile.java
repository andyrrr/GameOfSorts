package GoS.mundo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import rendering.textures.Sprite;

public class Tile {

	protected Sprite sprite;
	protected float x, y;
	protected boolean solid;

	public Tile(float x, float y, Sprite sprite) {
		super();
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.solid = true;
	}

	/** Función para dibujar */
	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		g.setColor(Color.RED);
		g.draw(getTop());
		g.setColor(Color.BLUE);
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
