
public class Dragon {
	String nombre;
	String apellido;
	int recarga;
	int edad;
	int resistencia;
	int X;
	int Y;
	
	/**
	 * Se crea un objeto drag�n con sus distintos atributos, el constructor recibe las listas con 
	 * los dato usados o a usar
	 * @param nombres Lista de nombres del txt que se pueden usar 
	 * @param apellidos Lista de apellidos del txt que se pueden usar 
	 * @param edades Lista de edades que se pueden usar 
	 */
	public Dragon(Lista<String> nombres, Lista<String> apellidos, Lista<Integer> edades, int X, int Y) {
		setNombre(nombres);
		setApellido(apellidos);
		setRecarga();
		setEdad(edades);
		setResistencia();
		this.X= X;
		this.Y=Y;

	}

	public String getNombre() {
		return nombre;
	}
	/**
	 * Toma el tama�o de la lisa, toma una posici�n aleatoria, 
	 * toma el valor de la posici�n se la asigna al nombre y elimina ese 
	 * dato asinado de la lista a usar
	 * @param nombres Listas de nombres a usar
	 */
	public void setNombre(Lista<String> nombres) {
		int numero = nombres.getTama�o();
		int aleatorio = (int) Math.floor(Math.random() * (numero));
		String seleccion = (String) nombres.retornar(aleatorio);
		nombres.removerPorPosicion(aleatorio);
		this.nombre = seleccion;
	}

	public String getApellido() {
		return apellido;
	}
	/**
	 * Toma el tama�o de la lisa, toma una posici�n aleatoria, 
	 * toma el valor de la posici�n se la asigna al apellido y elimina ese 
	 * dato asinado de la lista a usar
	 * @param apellidos
	 */
	public void setApellido(Lista<String> apellidos) {
		int aleatorio = (int) Math.floor(Math.random() * (apellidos.getTama�o()));
		String seleccion = (String) apellidos.retornar(aleatorio);
		apellidos.removerPorPosicion(aleatorio);
		this.apellido = seleccion;
	}

	public int getRecarga() {
		return recarga;
	}
	/**
	 * Toma un n�mero aleatorio de 1 a 100 y se lo asigna al objeto como 
	 * velocidade de recarga
	 */
	public void setRecarga() {
		int fuego = (int) Math.floor(Math.random() * (100) + 1);
		this.recarga = fuego;
	}

	public int getEdad() {
		return edad;
	}
	/**
	 * Toma el tama�o de la istal, escoge una posici�n al azar
	 * se le define el valor de esta posici�n la edad del objeto 
	 * @param edades lista de edades a usar
	 */
	public void setEdad(Lista<Integer> edades) {
		int aleatorio = (int) Math.floor(Math.random() * (edades.getTama�o()));
		int seleccion = edades.retornar(aleatorio);
		edades.removerPorPosicion(aleatorio);
		this.edad = seleccion;
	}

	public int getResistencia() {
		return resistencia;
	}
	/**
	 * Se toma un n�mero al azar entre 1 y 3 y se le define como la 
	 * resistencia de cada drag�n a ser atacado
	 */

	public void setResistencia() {
		int resistencia = (int) Math.floor(Math.random() * (3) + 1);
		this.resistencia = resistencia;
	}
	/**
	 * Muestra los atributos definidos de cada objeto
	 */
	public void verDragon() {
		System.out.println("Nombre: " + nombre + " " + apellido);
		System.out.println("Edad: " + edad);
		System.out.println("Velocidad de recarga: " + recarga);
		System.out.println("Resistencia: " + resistencia);
		System.out.println("X:" + X );
		System.out.println("Y:" + Y );
		System.out.println("");
		
	}

}
