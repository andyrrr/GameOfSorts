import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArbolAVL <B extends Comparable<B>>{
	
	String impresor="";
	NodoAVL<B> A;
	boolean Hh;
	static Logger logger = Logger.getLogger("");
	ConsoleHandler handler = new ConsoleHandler();
	//FileHandler handlerFile = new FileHandler("C:\\Users\\Inspiron 3458\\Documents\\Tec\\Datos_1\\Log.txt",1, 0);
	//Guarda las rotaciones en un archivo
	
	
	//Inserta un elemento en el arbol
	public void Insercion (B data){
		if ((!Miembro (data,A))){
			NodoAVL<B> info = new NodoAVL<B>(data);
			A=InsertarBalanceado(A,info);
		}
		else
			System.out.println("Error autor repetido");
	}
	//Auxiliar de Insercion
	NodoAVL<B> InsertarBalanceado(NodoAVL<B> izquierdo, NodoAVL<B> Nodo){
		NodoAVL<B> N1;
		NodoAVL<B> info = Nodo;
		if (ArbolVacio(izquierdo)){
			izquierdo= info;
			Hh=true;
		}
		else
                    // 2 > 1 = true
                    //("u".compareTo("d")>0)
			if (izquierdo.getData().compareTo(Nodo.getData()) >0){
				izquierdo.Izquierdo=InsertarBalanceado((NodoAVL<B>) izquierdo.Izquierdo,Nodo);
				if (Hh)
					switch(izquierdo.Factbalance){
						case 1:{
							izquierdo.Factbalance= 0;
							Hh=false;
						}	
						break;
						case 0:
							izquierdo.Factbalance= -1; 
						break;
						//se reestructura ya que pasaria a valer-2 y se desequilibra a la izq
						case -1:{
							N1=(NodoAVL<B>) izquierdo.Izquierdo;
							if (N1.Factbalance== -1){   
								izquierdo = RotacionIzquierdaIzquierda(izquierdo,N1);
							}
							else{
								izquierdo = RotacionIzquierdaDerecha(izquierdo,N1);
							}
							Hh = false;
						}
						break;
					}		
			}
			else{	
			if (Nodo.getData().compareTo(izquierdo.getData()) >0){
				izquierdo.Derecho=InsertarBalanceado((NodoAVL<B>) izquierdo.Derecho, Nodo);
				if (Hh)
				switch(izquierdo.Factbalance){
					case -1:
						izquierdo.Factbalance=0;
						Hh=false;	
					break;
					case 0:
						izquierdo.Factbalance=1; 
					break;
					//se reestructura ya que pasaria a valer-2 y se desequilibra a la izq
					case 1:{
						N1=(NodoAVL<B>) izquierdo.Derecho;
						if (N1.Factbalance==1){
							izquierdo = RotacionDerechaDerecha(izquierdo,N1);
						}
						else{
							izquierdo = RotacionDerechaIzquierda(izquierdo,N1);
						}
						Hh = false;
					}
					break;
				}	
				
			}
			else{
				Logger logger = Logger.getLogger("AVL");
				logger.log(Level.WARNING, "Error: No se pueden numeros iguales");
				Hh = false;
			}
		}
	return izquierdo;	
	}
	//retorna si esta vacio
	boolean ArbolVacio(NodoAVL<B> R){
		return (R == null);
	}
	//rota a la derecha
	NodoAVL<B> RotacionDerechaDerecha(NodoAVL<B> N, NodoAVL<B> N1){
		N.Derecho = N1.Izquierdo;
		N1.Izquierdo = N;
		if (N1.Factbalance==1) {
			N.Factbalance=0;
			N1.Factbalance=0;
		}
		else{
			N.Factbalance = 1;
			N1.Factbalance = -1;
		}
		N= N1;
		return N;
	}
	
	NodoAVL<B> RotacionDerechaIzquierda(NodoAVL<B> N, NodoAVL<B> N1){
		NodoAVL<B> N2;
		N2 = (NodoAVL<B>) N1.Izquierdo;
		N.Derecho = N2.Izquierdo;
		N2.Izquierdo=N;
		N1.Izquierdo=N2.Derecho;
		N2.Derecho=N1;
		if (N2.Factbalance==1){
			N.Factbalance=-1;
		}
		else{
			N.Factbalance=0;
		}
		if (N2.Factbalance==-1)
			N1.Factbalance=1;
		else
			N1.Factbalance=0;
		N2.Factbalance=0;
		N=N2;
		return N;
	}
	
	NodoAVL<B> RotacionIzquierdaIzquierda(NodoAVL<B> N, NodoAVL<B> N1){
		N.Izquierdo = N1.Derecho;
		N1.Derecho = N;
		if (N1.Factbalance==-1){
			N.Factbalance=0;
			N1.Factbalance=0;
		}
		else{
			N.Factbalance=-1;
			N1.Factbalance=1;
		}
		N=N1;
		return N;
	}
	
	NodoAVL<B> RotacionIzquierdaDerecha(NodoAVL<B> N, NodoAVL<B> N1){
		NodoAVL<B> N2;
		N2=(NodoAVL<B>) N1.Derecho;
		N.Izquierdo=N2.Derecho;
		N2.Derecho=N;
		N1.Derecho=N2.Izquierdo;
		N2.Izquierdo=N1;
		if (N2.Factbalance==1)
			N1.Factbalance=-1;
		else
			N1.Factbalance=0;
		if (N2.Factbalance==-1)
			N.Factbalance=1;
		else
			N.Factbalance=0;
		N2.Factbalance=0;
		N=N2;
		return N;
	}
	//Para verificar si esta el autor
	boolean Miembro(B data, NodoAVL<B> R){
		NodoAVL<B> Aux = R;
		boolean miembro = false;
		while (Aux != null){
			if (data==Aux.getData()){
				miembro = true;
				Aux = null;
			}

                        if (((B)data).compareTo(Aux.getData()) >0)
                                Aux = (NodoAVL<B>) Aux.Derecho;
                        else{
                                Aux = (NodoAVL<B>) Aux.Izquierdo;
                                if (Aux == null)
                                        miembro = false;
                        }
			
		}
		return miembro;
	}
	//busca la cantidad de nodos de un arbol avl
	int CantidadNodosAVL(NodoAVL<B> A){
		int cont = 0;
		if (A == null) 
			cont = cont;
		else{
			cont = cont + 1 + CantidadNodosAVL((NodoAVL<B>) A.Izquierdo) + CantidadNodosAVL((NodoAVL<B>) A.Derecho);
		}
		return cont;
	}
	//altura	
	public int Altura(NodoAVL<B> raiz){
		if (raiz == null)
		return 0;
		else
		return	1 + Math.max(Altura((NodoAVL<B>) raiz.Izquierdo), Altura((NodoAVL<B>) raiz.Derecho));
	}
        
        
        public void PostOrdenAVL()
        {
            PostOrdenAVL (A);
        }
	//Despliega la informacion en Postorden
	private void PostOrdenAVL (NodoAVL<B> Nodo){
		if (Nodo == null){
			return ;
		}
		else{
			PostOrdenAVL ((NodoAVL<B>) Nodo.Izquierdo);
			PostOrdenAVL ((NodoAVL<B>) Nodo.Derecho);
			impresor=impresor+"Autor: "+Nodo.getData();
		}
	}
        
        public void InordenAVL()
        {
            InordenAVL (A);
        }
	//Despliega la informacion en Inorden
	private void InordenAVL (NodoAVL<B> Nodo){
		if (Nodo == null)
			return;
		else{
			InordenAVL ((NodoAVL<B>) Nodo.Izquierdo);			
			System.out.print(Nodo.getData()+" ");
		
			InordenAVL ((NodoAVL<B>) Nodo.Derecho);
			
		}
		
	}
	

	
    public B get(B data){
        NodoAVL<B> current = A;
        while(current!=null){
            if(current.getData()==data){
            	
                return current.getData();
            }else 
            	if(current.getData().compareTo((B)data) > 0){
                    current = (NodoAVL<B>) current.Izquierdo;
            }else{
                    current = (NodoAVL<B>) current.Derecho;
            }
        }
        return null;
    }    
        
    public static void main(String[] args) {
    	
    	
		logger.log(Level.INFO, "Crea Árbol AVL");
		
    	/***ArbolAVL<Integer> arbol = new ArbolAVL<Integer>();
    	Oleada o1 = new Oleada(5);
    	Lista<Integer> listaEdades = new Lista<Integer>();
		for (int i=0;i<o1.listaDragones.getTamaño();i++) {
			listaEdades.insertarFinal(o1.listaDragones.retornar(i).getEdad());
		}
    	for (int i = 0; i < listaEdades.getTamaño(); i++) { 
    		arbol.Insercion(listaEdades.retornar(i));
    	}
    	arbol.InordenAVL();**/


    }
	
}