package rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/** Clase donde se dibujan todos los personajes */
public class Sprite {

	/** Importa la clase BufferedImage y crea un objeto que será la imagen */
	private BufferedImage image;

	/** Constructor de la clase */
	public Sprite(SpriteSheet spriteSheet, int x, int y) {
		this.image = spriteSheet.getTexture().getImage().getSubimage(
				(x * spriteSheet.getAncho()) - spriteSheet.getAncho(),
				(y * spriteSheet.getAltura()) - spriteSheet.getAltura(), (spriteSheet.getAncho()),
				(spriteSheet.getAltura()));

	}

	/** Constructor de la clase */
	public Sprite(String nombre) {
		/** Se crea un objeto en Texture que es donde se importan las imágenes */
		Texture tex = new Texture(nombre);
		image = tex.getImage(); // pide la imagen por medio de un método en Texture

	}

	/**
	 * Con esto se dibuja el Sprite creado
	 * 
	 * @param x recibe la coordenada en x
	 * @param y recibe la coordenada en y
	 */
	public void render(Graphics g, double x, double y) {
		g.drawImage(image, (int) x, (int) y, null);

	}

	public int getWidth() {
		return image.getWidth();
	}

	public int getHeight() {
		return image.getHeight();
	}
}
