package formacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class triangular {
	int niveles;
	int posicion;
	public void empezar(int largo, int ancho, Lista<Dragon>lista) {
		niveles = cantidadniveles(lista); 
		System.out.println(lista);
		System.out.println("Hay estos niveles: "+niveles);
		
		
		for (int actual=0; actual<niveles;actual++) {
			ArrayList<Integer> elementos = new ArrayList<Integer>();
			for(int j=0; j<cantidadDragones(actual);j++) {
				posicion++;
				lista.retornar(posicion).setX(ancho/(cantidadDragones(actual)+1)*(j+1));
				lista.retornar(posicion).setY(largo/(2*niveles)*(actual+1)+largo/2);
				
				System.out.println("cox: "+(ancho/(cantidadDragones(actual)+1)*(j+1)));
				System.out.println("coy: "+(largo/(2*niveles)*(actual+1)+largo/2));
				System.out.println("------------------------");
			}
			
			System.out.println("nivel " + actual + ": "+ elementos);
		}
		System.out.println("nivel " + (niveles) + ": "+ lista);
	}

	public int cantidadniveles(Lista<Dragon> lista) {
		int cantidad = lista.getTamaño();
		int nivel=0;
		while (cantidad>Math.pow(2, nivel)) {
			cantidad= (int) (cantidad-(Math.pow(2, nivel)));
			nivel++;
		}
		return nivel;
	}
	
	public int cantidadDragones(int nivel) {
		int final1 = (int) Math.pow(2, nivel);
		return final1;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		triangular t = new triangular();
		Oleada o1 = new Oleada(15);
		t.empezar(600,800,o1.listaDragones);
	}
}
