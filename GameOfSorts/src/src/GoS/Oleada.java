package GoS;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Oleada {
	private static final String ARCHIVO = "C:\\Users\\Valeria\\Documents\\Segundo Semestre\\Algoritmos y Estructuras de Datos I\\Game of Sorts\\nombresDragones.txt";
	static Lista<String> nombres = new Lista<String>();
	Lista<String> o_nombres = new Lista<String>();
	Lista<Integer> edades = new Lista<Integer>();
	Lista<Integer> velocidad = new Lista<Integer>();

	public Oleada() {

	}

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
				nombres.insertarFinal(nuevoTexto[i]);
			}
		}
		/** Cierra el lector */
		b.close();

	}

	/***/
	public Lista<String> Crear(int cantidad) {

		Dragon[] objetos = new Dragon[cantidad];
		for (int i = 0; i < cantidad; i++) {
			objetos[i] = new Dragon();
			o_nombres.insertarFinal(objetos[i].nombre(nombres));
			edades.insertarFinal(objetos[i].Edad());
			velocidad.insertarFinal(objetos[i].RecargadeFuego());
		}
		System.out.println(o_nombres.verLista());
		System.out.println(edades.verLista());
		System.out.println(velocidad.verLista());
		return null;

	}

	public static void main(String[] args) {
		Oleada o1 = new Oleada();
		try {
			muestraContenido(ARCHIVO);
			o1.Crear(25);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
