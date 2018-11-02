package me.utilidades.managers;

import java.awt.image.BufferedImage;

/** Maneja las diferentes imágenes que se van a utilizar */
public class TextureManager extends ResourceManager {

	/** Se crea el objeto imagen */
	private BufferedImage imagen;

	/** Se crea el nuevo manejador */
	public TextureManager(BufferedImage image) {
		this.imagen = image;
	}

	/** Devuelve la imagen actual cuando se llama al método */
	public BufferedImage getImage() {
		return imagen;
	}
}
