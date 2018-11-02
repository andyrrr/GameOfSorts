package sorts;
import java.io.*;

public class Nodo<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	T valor;
	Nodo<T> siguiente;
	/**
	 * @param valor
	 * @param next
	 */
	public Nodo(T valor) {
		super();
		this.valor = valor;
		this.siguiente = null;
	}
	public T getValor() {
		return valor;
	}
	public void setValor(T valor) {
		this.valor = valor;
	}
	public Nodo<T> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}
	
	
}