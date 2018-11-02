package rendering.textures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import me.utilidades.managers.TextureManager;

public class Texture {

	/** Nos permite facilitar la carga de la imagen */
	private final static Map<String, TextureManager> textMap = new HashMap<String, TextureManager>();
	private TextureManager manager; // acá maneja el fondo actual
	private String nombreDelArchivo; // Nombre del archivo

	public Texture(String nombreDelArchivo) {
		/** Busca la imagen guardada en la memoria */
		TextureManager imagenAnterior = textMap.get(nombreDelArchivo);

		/** Si ya hay imagen, la mantiene */
		if (imagenAnterior != null) {
			manager = imagenAnterior; // imagenNueva = imagenActual
			manager.addReference(); // suma para ver cuántas veces se ha llamado

			/** Si no encuentra la imagen */
		} else {
			try {
				System.out.println("Cargando la imagen: " + nombreDelArchivo);
				/** Carga la imagen */
				manager = new TextureManager(ImageIO.read(new File(
						"C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\"
								+ nombreDelArchivo + ".png")));
				/** Atrapa la excepción */
			} catch (IOException ex) {
				/** Si hay error al cargar la imagen */
				System.err.println("Error al cargar la imagen");

			}

		}
	}

	@Override
	protected void finalize() throws Throwable {
		if (manager.removeReference() && !nombreDelArchivo.isEmpty()) {
			textMap.remove(nombreDelArchivo);
			System.out.println("Eliminando el fondo actual de la memoria " + nombreDelArchivo);

			super.finalize();
		}
	}

	/** Acá dibuja la imagen */
	public void render(Graphics g, double x, double y) {
		/** Busca la imagen cargada, se le asignan las coordenadas */
		g.drawImage(manager.getImage(), (int) x, (int) y, null);
	}

	/** Devuelve la imagen cuando se llama el método */
	public BufferedImage getImage() {
		return manager.getImage();
	}

}
