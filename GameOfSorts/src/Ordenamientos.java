
public class Ordenamientos {
	int dragonesMatados = 0;
	String lista;
/**
 * Se reciba la lista de dragones para ser acomodados y el número de dragones muertos, paa saber en cuál ordenamiento
 * se debe de enviar
 * Se crean dos listas, una con la edad de cada dragón, por cada dragón, se le agrea la edad asignada a una 
 * nueva lista lo mismo sucede con la lista de recargas
 * @param listaDragones Lista de ojetos Dragón para ser acomodados
 * @param dragonesMatados Cambtidad de dragones que han muerto
 */
	public void Ordenar(Lista<Dragon> listaDragones, int dragonesMatados) {
		Lista<Integer> listaEdades = new Lista<Integer>();
		for (int i=0;i<listaDragones.getTamaño();i++) {
			listaEdades.insertarFinal(listaDragones.retornar(i).getEdad());
		}
		Lista<Integer> listaRecarga = new Lista<Integer>();
		for (int i=0;i<listaDragones.getTamaño();i++) {
			listaRecarga.insertarFinal(listaDragones.retornar(i).getRecarga());
		}
		
		//System.out.println("Lista Desordenada Edades: " + listaEdades.verLista());
		//System.out.println("Lista Desordenada Recarga: " + listaRecarga.verLista());
		/**
		 * Estos son los ciclos de ordenamiento, cuando el ciclo termine vuelvve al principio, se cambia el String
		 * lista por la cuál fue utilizada para ordnar, se usarán 3 algoritmos de ordenamiento y dos álboles
		 * Se trabajarán con las listas en tiempo real, es decir, no se harán copias, todo se trabajará en la misma dirección 
		 * de la lista original 
		 */
		if (dragonesMatados % 4 == 1) {
			selectionSort(listaDragones,listaEdades);
			lista = "Selection Sort";
		} else if (dragonesMatados % 4 == 2) {
			
			insertionSort(listaDragones,listaRecarga);
			lista = "Insertion Sort";
		} else if (dragonesMatados % 4 == 3) {
			quickSort(listaDragones, listaEdades, 0, listaEdades.getTamaño() - 1);
			lista = "Quick Sort";
		}
		//System.out.println("(E)Lista Ordenada con " + lista + ": " + listaEdades.verLista());
		//System.out.println("(R)Lista Ordenada con " + lista + ": " + listaRecarga.verLista());
		//System.out.println("");
	}
	
	/**
	 * El selection Sort funcionará con las edades, por lo tanto recibe la lista de edades para poder acomodalos, y a la misma vez la 
	 * lista de objeto Dragón para ser modificada
	 * @param listaDragones
	 * @param listaEdades
	 */
	void selectionSort(Lista<Dragon> listaDragones, Lista<Integer> listaEdades) {
		int n = listaEdades.getTamaño();

		// One by one move boundary of unsorted subarray
		/**Se toma cada valor y se mueve dentro de las listas desordenadas
		 */
		for (int i = 0; i < n - 1; i++) {
			/**Encuentra el mínimo elemento desordenado y se compara con otro hasta encontrar otro elemento desordenado
			 */
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (listaEdades.retornar(j) < listaEdades.retornar(min_idx))
					min_idx = j;

			/**
			 * Cambia el mínimo elemento encontrado con el primero dentr de mbas listas, y así se asegura de que los dragones 
			 * quedan acomodados con respecto a la listas de edades
			 */
			int temp = listaEdades.retornar(min_idx);
			listaEdades.modificar(min_idx, listaEdades.retornar(i));
			listaEdades.modificar(i, temp);
			
			Dragon temp2 = listaDragones.retornar(min_idx);
			listaDragones.modificar(min_idx, listaDragones.retornar(i));
			listaDragones.modificar(i, temp2);
		}
	}

	void quickSort(Lista<Dragon> listaDragones, Lista<Integer> listaEdades, int menor, int mayor) {
		if (menor < mayor) {
			/**
			 * pi es la partición de la lista en ds mitades, es la clase que hace todo el trabajo
			 */
			
			int pi = partitionQS(listaDragones,listaEdades, menor, mayor);

			/**
			 * Se hace la recursividad del algoritmo pero con listas divididas a la mitad
			 */
			quickSort(listaDragones,listaEdades, menor, pi - 1);
			quickSort(listaDragones,listaEdades, pi + 1, mayor);
		}
	}

	int partitionQS(Lista<Dragon> listaDragones,Lista<Integer> listaEdades, int menor, int mayor) {
		/**
		 * Se declara un pivote, que va a ser el valor del último elemnto de la lista envida elemento 
		 */
		int pivote = listaEdades.retornar(mayor);
		int i = (menor - 1); // posición del elemento menor
		for (int j = menor; j < mayor; j++) {
			/**
			 * Se da la condición, si el valor en la posición j es menor o igual al pivote
			 * y se aumenta el contador i, que será el menor desordenado
			 */
			if (listaEdades.retornar(j) <= pivote) {
				i++;

				/**Se cambia de posición el i(posición del menor) con j (Elemento evaluado) 
				 * y se cre un nodo temporal que tomará el valor de i para luego colocarlo en la posición j 
				 * Se hace lo mismo con lista de dragones, se modifican posiciones y nodos (En este caso de tipo Dragon)
				 */
				int temp = listaEdades.retornar(i);
				Dragon temp2 = listaDragones.retornar(i);
				listaEdades.modificar(i, listaEdades.retornar(j));
				listaEdades.modificar(j, temp);
				
				listaDragones.modificar(i, listaDragones.retornar(j));
				listaDragones.modificar(j, temp2);
			}
		}

		/**
		 * En caso que terminen de recorrer y no haya cumplido la condición, se intercambia el valor de 
		 * la posición i+1 con el elemnto mayor, y se realiza lo mismo con la lista de dragones 
		 * y se devuelve la posición i +1
		 */
		int temp = listaEdades.retornar(i + 1);
		Dragon temp2 = listaDragones.retornar(i + 1);
		listaEdades.modificar(i + 1, listaEdades.retornar(mayor));
		listaEdades.modificar(mayor, temp);
		listaDragones.modificar(i + 1, listaDragones.retornar(mayor));
		listaDragones.modificar(mayor, temp2);
		return i + 1;
	}

	void insertionSort(Lista<Dragon> listaDragones, Lista<Integer> listaRecarga) {
		int n = listaRecarga.getTamaño();
		/** Se toma cada elemnto y se copia el valor en la variable temporales
		 */
		for (int i = 1; i < n; ++i) {
			int temp = listaRecarga.retornar(i);
			Dragon temp2 = listaDragones.retornar(i);
			int j = i - 1;
			/**
			 * Mueve los elementos que son mayores que el temporal, y se repite hasta que no hayan elementos 
			 * menores antes del temporal
			 */
			while (j >= 0 && listaRecarga.retornar(j) > temp) {
				listaRecarga.modificar(j + 1, listaRecarga.retornar(j));
				listaDragones.modificar(j + 1, listaDragones.retornar(j));
				j = j - 1;
			}
			/**
			 * Se modifican las posiciones j de ambas listas con los emporales 
			 */
			listaRecarga.modificar(j + 1, temp);
			listaDragones.modificar(j + 1, temp2);
			
		}
	}

	static Lista<Integer> convertir(String lista1) {
		Lista<Integer> listaFinal = new Lista<Integer>();
		String[] li = lista1.split(",");
		for (int i = 0; i < li.length; i++) {
			listaFinal.insertarFinal(Integer.valueOf(li[i]));
		}
		return listaFinal;
	}

	/*public static void main(String args[]) {
		String lista1 = "3,60,35,2,45,320,5";
		String lista2 = "7,9,24,5,233,77,68";
		String lista3 = "55,3,6,1,8,95,0,47";
		Lista<Integer> listaSS = Ordenamientos.convertir(lista1);
		Lista<Integer> listaQS = Ordenamientos.convertir(lista2);
		Lista<Integer> listaIS = Ordenamientos.convertir(lista3);
		Ordenamientos ob1 = new Ordenamientos(listaSS, 1);
		Ordenamientos ob2 = new Ordenamientos(listaQS, 2);
		Ordenamientos ob3 = new Ordenamientos(listaIS, 3);
	}*/
}
