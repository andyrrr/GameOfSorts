package GoS;
import java.io.Serializable;

public class Lista<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Nodo<T> inicio;
	Nodo<T> fin;
	private int tama�o;
	
	public Lista() {
		inicio=null;
		fin=null;
	}
	/***
	 * Verifica si el nodo inicio y el nodo final son nulos, para saber si est� el objeto lista est� vac�o
	 */
	public boolean estaVacia() {
		return this.inicio==null;
	}
	/**
	 * @param valor nuevo valor que va a ser insertado 
	 * la funci�n valida primero si el nodo inicial es nulo, si es as� el valor va a pasar a ser el nodo inicial
	 * se va a crear un nodo con el valor a ingresar, y se va a declara como el siguiente del nodo final
	 * luego nodo final se declara como el nodo actual
	 */
	public void insertarFinal(T valor) {
		Nodo<T> actual;
		if(estaVacia()) {
			actual = new Nodo<T>(valor);
			inicio= actual;
			fin=actual;
			tama�o ++;
		} else {
			actual = new Nodo<T>(valor);
			fin.setSiguiente(actual);
			fin=actual;
			tama�o++;
		}
	}
	/**
	 * @param i posici�n que se quiere obtener
	 * @return valor en la posici�n i 
	 * Se crea un nodo temporal que inicia en el nodo inicial, se crea un while que va a pasar el nodo temporal al nodo iguiente, y as� se recorrer� la lista
	 * cada vez que se sobreescriba el nodo temporal se le sumar� uno a el lugar, que indica la posici�n del elemento, cuando se llegue al indice (i), se retorna el valor
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
	 * Verifica si la lista est� vac�a, si es as� retorna 0 (Hay cero elementos)
	 * sino, crea un nodo temporal, y crea una variable tama�o como 1(Porque se sabe que no est� vac�a)
	 * busca el nodo que no tiene siguiente, y cada vez que pasa de nodo a nodo le suma uno al tama�o, cuando encuentra este nodo sin siguiente(Nodo fin o final)
	 * sale del ciclo y retorna el tama�o, es decir, el n�mero de elementos o nodos que se pasaron hasta llegar al nodo final 
	 * @return Cu�ntos elementos tiene la lista
	 */
	public int getTama�o(){
        return tama�o;
    }
	/**
	 * Busca dentro de la lista si ya existe el valor ingresado
	 * Se crea un nodo temporal que recorrer� la lista, y busca si el valor del nodo temporal es igual al valor ingresado, si es as�, retorna true, 
	 * sino, contin�a con el nodo siguiente, si termin� de recorrer la lista y no encontr� ninguna igualdad, retorna falso
	 * @param valor elemento a buscar dentro de la lista
	 * @return Valor booleano, est� o no
	 */
	public boolean contieneNum(int valor) {
		for (int i=0;i<tama�o;i++) {
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
	 * Se toma una posicion y un valor del tipo de nodo que se va a modificar, se recorrer� la lista
	 * hasta llegar a la posici�n se�alada y se le un nuevo valor al nodo en esa posici�n, el nuevo 
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
        // Verifica si la posici�n ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion < tama�o){
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
            // Disminuye el contador de tama�o de la lista.
            tama�o--;
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