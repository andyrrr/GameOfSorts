package GoS;

public class Dragon {
	public String nombre;
	public Dragon() {
		
	}
	public String nombre(Lista<String> nombres) {
	
		int numero = nombres.getTamaño();
		int aleatorio = (int) Math.floor(Math.random()*(numero));
		String seleccion = (String) nombres.retornar(aleatorio);
		nombres.removerPorPosicion(aleatorio);
		String nombre = seleccion;
		return nombre;
	}	
	public String apellido(Lista<String> apellidos) {
		int numero = apellidos.getTamaño();
		int aleatorio = (int) Math.floor(Math.random()*(numero));
		String seleccion = (String) apellidos.retornar(aleatorio);
		apellidos.removerPorPosicion(aleatorio);
		String apellido = seleccion;
		return apellido ;		
	}
	
	public int RecargadeFuego() {
		int fuego =  (int) Math.floor(Math.random()*(100)+1);
		return fuego;
	}
	
	public int Edad() {
		Lista<Integer> edades = new Lista<Integer>();
		int max = 1000;
		for (int i = 1;i<max;i++) {
			edades.insertarFinal(i);
		}
		int numero = edades.getTamaño();
		int aleatorio = (int) Math.floor(Math.random()*(numero));
		int seleccion = edades.retornar(aleatorio);
		edades.removerPorPosicion(aleatorio);
		int edad = seleccion;
		return edad;
	}	
	public int Resistencia() {
		int resistencia =  (int) Math.floor(Math.random()*(3)+1);
		return resistencia;
	}
	
	
	public static void main(String[] args) {
		
		
	
		/***
		 * System.out.println("Nombre: " + d1.nombre(nombres)+ " " + d1.nombre(apellidos) + " -- " +"Velocidad de recarga de fuego: " +d1.RecargadeFuego() +" -- "+ "Edad: " + d1.Edad()+" -- " + "Resistencia: "+ d1.Resistencia());                                                                    
		 
		System.out.println("Nombre: " +d2.nombre(nombres)+ " " + d2.nombre(apellidos) + " -- " +"Velocidad de recarga de fuego: " + d2.RecargadeFuego() +" -- "+ "Edad: " + d2.Edad()+" -- " + "Resistencia: "+d2.Resistencia());
		System.out.println("Nombre: " +d3.nombre(nombres)+ " " + d3.nombre(apellidos) + " -- " +"Velocidad de recarga de fuego: " + d3.RecargadeFuego() +" -- "+ "Edad: " + d3.Edad()+" -- " + "Resistencia: "+d3.Resistencia());
		System.out.println("Nombre: " +d4.nombre(nombres)+ " " + d4.nombre(apellidos) + " -- " +"Velocidad de recarga de fuego: " + d4.RecargadeFuego() +" -- "+ "Edad: " + d4.Edad()+" -- " + "Resistencia: "+d4.Resistencia());
		System.out.println("Nombre: " +d5.nombre(nombres)+ " " + d5.nombre(apellidos) + " -- " +"Velocidad de recarga de fuego: " + d5.RecargadeFuego() +" -- "+ "Edad: " + d5.Edad()+" -- " +"Resistencia: "+ d5.Resistencia());
		*/
	}		
		}
		
		
		
