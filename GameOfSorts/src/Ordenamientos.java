

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
		
		System.out.println("Lista Desordenada Edades: " + listaEdades.verLista());
		System.out.println("Lista Desordenada Recarga: " + listaRecarga.verLista());
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
		System.out.println("(E)Lista Ordenada con " + lista + ": " + listaEdades.verLista());
		System.out.println("(R)Lista Ordenada con " + lista + ": " + listaRecarga.verLista());
		System.out.println("");
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
		/**
		 * 
		 */
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < n; j++)
				if (listaEdades.retornar(j) < listaEdades.retornar(min_idx))
					min_idx = j;

			// Swap the found minimum element with the first
			// element
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
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partitionQS(listaDragones,listaEdades, menor, mayor);

			// Recursively sort elements before
			// partition and after partition
			quickSort(listaDragones,listaEdades, menor, pi - 1);
			quickSort(listaDragones,listaEdades, pi + 1, mayor);
		}
	}

	int partitionQS(Lista<Dragon> listaDragones,Lista<Integer> listaEdades, int menor, int mayor) {
		int pivot = listaEdades.retornar(mayor);
		int i = (menor - 1); // index of smaller element
		for (int j = menor; j < mayor; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (listaEdades.retornar(j) <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = listaEdades.retornar(i);
				Dragon temp2 = listaDragones.retornar(i);
				listaEdades.modificar(i, listaEdades.retornar(j));
				listaEdades.modificar(j, temp);
				listaDragones.modificar(i, listaDragones.retornar(j));
				listaDragones.modificar(j, temp2);
				// System.out.println(lista.verLista());
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = listaEdades.retornar(i + 1);
		Dragon temp2 = listaDragones.retornar(i + 1);
		listaEdades.modificar(i + 1, listaEdades.retornar(mayor));
		listaEdades.modificar(mayor, temp);
		listaDragones.modificar(i + 1, listaDragones.retornar(mayor));
		listaDragones.modificar(mayor, temp2);
		// System.out.println(lista.verLista());

		return i + 1;
	}

	void insertionSort(Lista<Dragon> listaDragones, Lista<Integer> listaRecarga) {
		int n = listaRecarga.getTamaño();
		for (int i = 1; i < n; ++i) {
			int key = listaRecarga.retornar(i);
			Dragon key2 = listaDragones.retornar(i);
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one position
			 * ahead of their current position
			 */
			while (j >= 0 && listaRecarga.retornar(j) > key) {
				listaRecarga.modificar(j + 1, listaRecarga.retornar(j));
				listaDragones.modificar(j + 1, listaDragones.retornar(j));
				j = j - 1;
			}
			listaRecarga.modificar(j + 1, key);
			listaDragones.modificar(j + 1, key2);
			
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
