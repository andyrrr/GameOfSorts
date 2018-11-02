package sorts;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {

	private static final String ARCHIVO = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\nombresDragones.txt";

	/** Este método es para leer el archivo txt */
	public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
		/** Texto en el archivo TXT */
		String textoDelArchivo;
		/** Estos son los lectores de Java */
		FileReader lector = new FileReader(archivo);
		BufferedReader b = new BufferedReader(lector);
		/** Si el texto leido es diferente de nulo, hace esto */
		while ((textoDelArchivo = b.readLine()) != null) {
			/** Crea un array con los nombres, separa cada nombre por coma */
			String[] nuevoTexto = textoDelArchivo.split(",");
			for (int i = 0; i < nuevoTexto.length; i++) {
				/** Agarra cada posición del array y la imprime */
				System.out.println(nuevoTexto[i]);
				// nombres.insertarFinal(nuevoTexto[i]);
			}
			// System.out.println(nombres.verLista());
		}
		/** Cierra el lector */
		b.close();

	}

	public static void main(String[] args) {
		try {
			muestraContenido(ARCHIVO);
		} catch (FileNotFoundException e) {
			System.err.println("Error al abrir el archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
