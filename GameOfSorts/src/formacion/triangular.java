package formacion;

import java.util.ArrayList;

public class triangular {
	ArrayList<Integer> lista = new ArrayList<Integer>();
	int niveles;
	public void empezar(int largo, int ancho) {
		for (int i = 0; i < 100; i++) {
			lista.add(i);
		}
		niveles = cantidadniveles(lista); 
		System.out.println(lista);
		System.out.println("Hay estos niveles: "+niveles);
		
		
		for (int actual=0; actual<niveles;actual++) {
			ArrayList<Integer> elementos = new ArrayList<Integer>();
			for(int j=0; j<cantidadDragones(actual);j++) {
				
				elementos.add(lista.get(0));
				lista.remove(0);
				System.out.println("cox: "+(ancho/(cantidadDragones(actual)+1)*elementos.size()));
				System.out.println("coy: "+(largo/(2*niveles)*(actual+1)+largo/2));
				System.out.println("------------------------");
			}
			
			System.out.println("nivel " + actual + ": "+ elementos);
		}
		System.out.println("nivel " + (niveles) + ": "+ lista);
	}

	public int cantidadniveles(ArrayList<Integer> lista) {
		int cantidad = lista.size();
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
	
	
	public static void main(String[] args) {
		triangular t = new triangular();
		t.empezar(600,800);
	}
}
