import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Oleada {
	/**
	 *Se declaran las variables a usar dentro del programa
	 */
	private static final String ARCHIVO = "C:\\Users\\maxta\\Desktop\\nombres.txt";
	static Lista<String> nombres = new Lista<String>();
	Lista<Integer> edades = new Lista<Integer>();
	Lista<Dragon> listaDragones = new Lista<Dragon>();
	static Ordenamientos ob = new Ordenamientos();
	/**
	 * Se inicia cada oleada, este es el constructor, toma 500 edades y las añade
	 * como una lista de posibles opciones (Dos dragones no pueden tener la misma edad) 
	 * @param cantidad Cantidad de dragones de esta oleada
	 */
	public Oleada(int cantidad) {
		/**
		 * cada dragón hecho en masa, es añadadido a la lista de Dragones
		 */
		int max = 500;
		for (int i = 1; i < max; i++) {
			edades.insertarFinal(i);
		}
		Dragon[] dragon = new Dragon[cantidad];
		for (int i = 0; i < cantidad; i++) {
			dragon[i] = new Dragon(nombres, nombres, edades);
			dragon[i].verDragon();
			listaDragones.insertarFinal(dragon[i]);
		}
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
	/**
	 * Se crea una oleada de 5 dragones, el objeto ob, será el objeto capaz de ordenar 
	 * las listas según se os indique, cada vez que se necesite ordenar la lista de dragones según edad 
	 * o según velocidad de recarga, se llamará al mpetodo ordenar 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			muestraContenido(ARCHIVO);
			Oleada o1 = new Oleada(5);
			/**
			 * Se envia la lista de dragones a acomodar, y los dragones que han matado
			 * luego con un for se uestra en consola el nuevo orden de la lista
			 */
			ob.Ordenar(o1.listaDragones,1);
			for (int i=0;i<o1.listaDragones.getTamaño();i++) {
				o1.listaDragones.retornar(i).verDragon();
			}
			ob.Ordenar(o1.listaDragones,2);
			for (int i=0;i<o1.listaDragones.getTamaño();i++) {
				o1.listaDragones.retornar(i).verDragon();
			}
			ob.Ordenar(o1.listaDragones,3);
			for (int i=0;i<o1.listaDragones.getTamaño();i++) {
				o1.listaDragones.retornar(i).verDragon();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
