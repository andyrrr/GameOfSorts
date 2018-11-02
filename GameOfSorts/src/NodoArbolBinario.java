
public class NodoArbolBinario<T> 
{
    public T data;
	public NodoArbolBinario<T> next;
	public NodoArbolBinario<T> previous;
	//////////////////////////////////////////////////////
	public NodoArbolBinario (T data)
	{
		this.data = data;
		this.next = null;
		this.previous = null;
	}
	public T getData() 
	{
		return this.data;
	}
	public void setData ( T data )
	{
		this.data = data;
	}
	public NodoArbolBinario<T> getNext()
	{
		return this.next;
	}
	public void setNext( NodoArbolBinario<T> next){
		this.next = next;
	}
	public NodoArbolBinario<T> getPrevious() {
		return previous;
	}
	public void setPrevious( NodoArbolBinario<T> previous ) {
		this.previous = previous;
	}
}
