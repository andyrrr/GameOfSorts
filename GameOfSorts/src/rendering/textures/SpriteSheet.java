package rendering.textures;

public class SpriteSheet {

	private Texture texture;

	private int ancho, altura;

	/**
	 * Recibe la imagen
	 * 
	 * @param texture es la imagen que se carga
	 * @param size    es el tamaño que se le asigna
	 */
	public SpriteSheet(Texture texture, int size) {
		this(texture, size, size);
	}

	/** Constructor que recibe la imagen con su respectivo ancho y la altura */
	public SpriteSheet(Texture texture, int ancho, int altura) {
		this.texture = texture;
		this.ancho = ancho;
		this.altura = altura;
	}

	/** Devuelve la imagen actual */
	public Texture getTexture() {
		return texture;
	}

	/** Devuelve el ancho de la imagen */
	public int getAncho() {
		return ancho;
	}

	/** Devuelve la altura de la imagen */
	public int getAltura() {
		return altura;
	}

}
