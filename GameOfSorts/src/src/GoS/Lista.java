package GoS;
import java.io.Serializable;

public class Lista<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Nodo<T> inicio;
	Nodo<T> fin;
	private int tamaño;
	
	public Lista() {
		inicio=null;
		fin=null;
	}
	/***
	 * Verifica si el nodo inicio y el nodo final son nulos, para saber si está el objeto lista está vacío
	 */
	public boolean estaVacia() {
		return this.inicio==null;
	}
	/**
	 * @param valor nuevo valor que va a ser insertado 
	 * la función valida primero si el nodo inicial es nulo, si es así el valor va a pasar a ser el nodo inicial
	 * se va a crear un nodo con el valor a ingresar, y se va a declara como el siguiente del nodo final
	 * luego nodo final se declara como el nodo actual
	 */
	public void insertarFinal(T valor) {
		Nodo<T> actual;
		if(estaVacia()) {
			actual = new Nodo<T>(valor);
			inicio= actual;
			fin=actual;
			tamaño ++;
		} else {
			actual = new Nodo<T>(valor);
			fin.setSiguiente(actual);
			fin=actual;
			tamaño++;
		}
	}
	/**
	 * @param i posición que se quiere obtener
	 * @return valor en la posición i 
	 * Se crea un nodo temporal que inicia en el nodo inicial, se crea un while que va a pasar el nodo temporal al nodo iguiente, y así se recorrerá la lista
	 * cada vez que se sobreescriba el nodo temporal se le sumará uno a el lugar, que indica la posición del elemento, cuando se llegue al indice (i), se retorna el valor
	 * de el nodo temporal  
	 */
	public T retornar(int posicion){
		int lugar=0;
		Nodo<T> temp = this.inicio;
		while (lugar<posicion) {
			temp=temp.siguiente;
			lugar++;
		}
		return temp.valor;
	}
	/**
	 * Verifica si la lista está vacía, si es así retorna 0 (Hay cero elementos)
	 * sino, crea un nodo temporal, y crea una variable tamaño como 1(Porque se sabe que no está vacía)
	 * busca el nodo que no tiene siguiente, y cada vez que pasa de nodo a nodo le suma uno al tamaño, cuando encuentra este nodo sin siguiente(Nodo fin o final)
	 * sale del ciclo y retorna el tamaño, es decir, el número de elementos o nodos que se pasaron hasta llegar al nodo final 
	 * @return Cuántos elementos tiene la lista
	 */
	public int getTamaño(){
        return tamaño;
    }
	/**
	 * Busca dentro de la lista si ya existe el valor ingresado
	 * Se crea un nodo temporal que recorrerá la lista, y busca si el valor del nodo temporal es igual al valor ingresado, si es así, retorna true, 
	 * sino, continúa con el nodo siguiente, si terminá de recorrer la lista y no encontró ninguna igualdad, retorna falso
	 * @param valor elemento a buscar dentro de la lista
	 * @return Valor booleano, está o no
	 */
	public boolean contieneNum(int valor) {
		for (int i=0;i<tamaño;i++) {
			if (this.retornar(i).equals(valor)) {
				return true;
			} 
		}
		return false;	
	}
	
	
	public String verLista() {
		String ListaFinal="";
		if (estaVacia()) {
			return "";
		}else {
			Nodo<T> temp = this.inicio;
			while(temp.siguiente!=null) {
				ListaFinal = ListaFinal+String.valueOf(temp.valor)+",";
				temp=temp.siguiente;
			}
			ListaFinal = ListaFinal+String.valueOf(temp.valor);
			return ListaFinal;
		}
	}
	/**
	 * Se toma una posicion y un valor del tipo de nodo que se va a modificar, se recorrerá la lista
	 * hasta llegar a la posición señalada y se le un nuevo valor al nodo en esa posición, el nuevo 
	 * valor es definido por el usuario
	 * @param posicion lugar del nodo a cambiar
	 * @param valor Valor a asignar al nuevo nodo
	 */
	public void modificar(int posicion, T valor) {
		int lugar=0;
		Nodo<T> temp = this.inicio;
		while (lugar<posicion) {
			temp=temp.siguiente;
			lugar++;
		}
		temp.setValor(valor);
	}
	public void removerPorPosicion(int posicion){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion < tamaño){
            // Consulta si el nodo a eliminar es el primero
            if(posicion == 0){
                // Elimina el primer nodo apuntando al siguinte.
                inicio = inicio.getSiguiente();
            }
            // En caso que el nodo a eliminar este por el medio 
            // o sea el ultimo
            else{
                // Crea una copia de la lista.
                Nodo<T> aux = inicio;
                // Recorre la lista hasta lleger al nodo anterior al eliminar.
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getSiguiente();
                }
                // Guarda el nodo siguiente al nodo a eliminar.
                Nodo<T> siguiente = aux.getSiguiente();
                // Elimina la referencia del nodo apuntando al nodo siguiente.
                aux.setSiguiente(siguiente.getSiguiente());
            }
            // Disminuye el contador de tamaño de la lista.
            tamaño--;
        }
    }
	/**
	 * Borra todos los datos de la lista redifiniendo el nodo de inicio y el nodo final como nulos
	 */
	public void limpiar() {
		this.inicio=null;
		this.fin=null;
	}
	
	public Nodo<T> getInicio() {
		return inicio;
	}

	public void setInicio(Nodo<T> inicio) {
		this.inicio = inicio;
	}

	public Nodo<T> getFin() {
		return fin;
	}

	public void setFin(Nodo<T> fin) {
		this.fin = fin;
	}
	public static void main(String[] args) throws Exception {
   
	}
}