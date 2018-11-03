package formacion;

public class Dragon {
	String nombre;
	String apellido;
	int recarga;
	int edad;
	int resistencia;
	Dragon hijo1, hijo2;
	int hi=0;
	int cox=0;
	int coy=0;
	
	/**
	 * Se crea un objeto dragón con sus distintos atributos, el constructor recibe las listas con 
	 * los dato usados o a usar
	 * @param nombres Lista de nombres del txt que se pueden usar 
	 * @param apellidos Lista de apellidos del txt que se pueden usar 
	 * @param edades Lista de edades que se pueden usar 
	 */
	public Dragon(Lista<String> nombres, Lista<String> apellidos, Lista<Integer> edades) {
		setNombre(nombres);
		setApellido(apellidos);
		setRecarga();
		setEdad(edades);
		setResistencia();
	}

	public String getNombre() {
		return nombre;
	}
	
	/*public void setHijos(Dragon hijo1, Dragon hijo2) {
		setHijo1(hijo1);
		setHijo1(hijo2);
		setHi(2);
		this.hijo1=hijo1;
		this.hijo2=hijo2;
		this.hi=2;
	}*/
	public int getHi() {
		return hi;
	}

	public void setHi(int hi) {
		this.hi = hi;
	}

	public Dragon getHijo1() {
		return hijo1;
	}

	public void setHijo1(Dragon hijo1) {
		this.hijo1 = hijo1;
	}

	public Dragon getHijo2() {
		return hijo2;
	}

	public void setHijo2(Dragon hijo2) {
		this.hijo2 = hijo2;
	}

	/**
	 * Toma el tamaño de la lisa, toma una posición aleatoria, 
	 * toma el valor de la posición se la asigna al nombre y elimina ese 
	 * dato asinado de la lista a usar
	 * @param nombres Listas de nombres a usar
	 */
	public void setNombre(Lista<String> nombres) {
		int numero = nombres.getTamaño();
		int aleatorio = (int) Math.floor(Math.random() * (numero));
		String seleccion = (String) nombres.retornar(aleatorio);
		//nombres.removerPorPosicion(aleatorio);
		this.nombre = seleccion;
	}

	public String getApellido() {
		return apellido;
	}
	/**
	 * Toma el tamaño de la lisa, toma una posición aleatoria, 
	 * toma el valor de la posición se la asigna al apellido y elimina ese 
	 * dato asinado de la lista a usar
	 * @param apellidos
	 */
	public void setApellido(Lista<String> apellidos) {
		int aleatorio = (int) Math.floor(Math.random() * (apellidos.getTamaño()));
		String seleccion = (String) apellidos.retornar(aleatorio);
		//apellidos.removerPorPosicion(aleatorio);
		this.apellido = seleccion;
	}

	public int getRecarga() {
		return recarga;
	}
	/**
	 * Toma un número aleatorio de 1 a 100 y se lo asigna al objeto como 
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
	 * Toma el tamaño de la istal, escoge una posición al azar
	 * se le define el valor de esta posición la edad del objeto 
	 * @param edades lista de edades a usar
	 */
	public void setEdad(Lista<Integer> edades) {
		int aleatorio = (int) Math.floor(Math.random() * (edades.getTamaño()));
		int seleccion = edades.retornar(aleatorio);
		edades.removerPorPosicion(aleatorio);
		this.edad = seleccion;
	}

	public int getResistencia() {
		return resistencia;
	}
	/**
	 * Se toma un número al azar entre 1 y 3 y se le define como la 
	 * resistencia de cada dragón a ser atacado
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
		System.out.println("Padre de: " + this.hi);
		System.out.println("");
	}

}
