package sorts;

import java.util.Vector;

@SuppressWarnings("unused")
public class OleadaPrueba {
	private static int numDeOleada = 1;
	private static int cantDeDragones = 100;
	private int numAlineacion = 0;
	private static String alineacionActual;
	private static Vector<String> alineaciones = new Vector<String>();

	public static void agregarAlineaciones() {
		String posiblesAlineaciones = "SelectionSort,insertionSort,quickSort,arbolBin,avl";
		String[] lista = posiblesAlineaciones.split(",");
		for (int i = 0; i < lista.length; i++) {
			alineaciones.add(lista[i]);
		}
		System.out.println(alineaciones.toString());
	}

	public static void main(String[] args) throws Exception {
		agregarAlineaciones();
	}

}
