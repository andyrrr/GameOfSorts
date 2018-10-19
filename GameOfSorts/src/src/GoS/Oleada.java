package GoS;

public class Oleada {
	
	public Oleada() {
		
	}
	public Lista<String> Crear(int cantidad){
		Lista<String> o_nombres = new Lista<String>();
        Lista<Integer> edades = new Lista<Integer>();
        Lista<Integer> velocidad = new Lista<Integer>();
        Lista<String> nombres = new Lista<String>();
		nombres.insertarFinal("Drogon");
        nombres.insertarFinal("Viserion");
        nombres.insertarFinal("Rhaegal");
        nombres.insertarFinal("Smaug");
        nombres.insertarFinal("Amaru");
        
		Dragon [] objetos = new Dragon[cantidad];
		for (int i=0;i<cantidad;i++) {
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
		o1.Crear(5);
	}
}



