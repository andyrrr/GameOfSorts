public class BinarySearchTree <B extends Comparable<B>>{
    public   NodoArbolBinario<B> root;
    private  String lookstack;
    public BinarySearchTree(){
            this.root = null;
    }

    public boolean isMember(B id){
        NodoArbolBinario<B> current = root;
        while(current!=null){
            if(current.getData()==id){
                    return true;
            }else if(current.getData().compareTo((B)id)>0){
                    current = current.previous;
            }else{
                    current = current.next;
            }
        }
        return false;
    }
    public NodoArbolBinario<B> getMember(B id){
        NodoArbolBinario<B> current = root;
        while(current!=null){
            if(current.getData()==id){
                    return current;
            }else if(current.getData().compareTo((B) id)>0){
                    current = current.previous;
            }else{
                    current = current.next;
            }
        }
        return null;
    }
    
    public boolean delete(B id){
        NodoArbolBinario<B> parent = root;
        NodoArbolBinario<B> current = root;
        boolean isLeftChild = false;
        while(!current.getData().equals(id)){
            parent = current;
            if(current.getData().compareTo((B)id)>0){
                isLeftChild = true;
                current = current.previous;
            }else{
                isLeftChild = false;
                current = current.next;
            }
            if(current ==null){
                return false;
            }
        }
     
        if(current.previous==null && current.next==null){
            if(current==root){
                root = null;
            }
            if(isLeftChild ==true){
                parent.previous = null;
            }else{
                parent.next = null;
            }
        }
 
        else if(current.next==null){
            if(current==root){
                root = current.previous;
            }else if(isLeftChild){
                parent.previous = current.previous;
            }else{
                parent.next = current.previous;
            }
        }
        else if(current.previous==null){
            if(current==root){
                root = current.next;
            }else if(isLeftChild){
                parent.previous = current.next;
            }else{
                parent.next = current.next;
            }
        }else if(current.previous!=null && current.next!=null){

            NodoArbolBinario<B> successor = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.previous = successor;
            }else{
                parent.next = successor;
            }			
            successor.previous = current.previous;
        }		
        return true;		
    }

    public NodoArbolBinario<B> getSuccessor(NodoArbolBinario<B> deleleNode){
        NodoArbolBinario<B> successsor =null;
        NodoArbolBinario<B> successsorParent =null;
        NodoArbolBinario<B> current = deleleNode.next;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.previous;
        }

        if(successsor!=deleleNode.next){
            successsorParent.previous = successsor.next;
            successsor.next = deleleNode.next;
        }
        return successsor;
    }
    public void insert(B id){
        NodoArbolBinario<B> newNode = new NodoArbolBinario<B>(id);
        if(root==null){
            root = newNode;
            return;
        }
        NodoArbolBinario<B> current = root;
        NodoArbolBinario<B> parent = null;
        while(true){
            parent = current;
            if(current.getData().compareTo((B) id)>0){				
                current = current.previous;
                if(current==null){
                    parent.previous = newNode;
                    return;
                }
            }else{
                current = current.next;
                if(current==null){
                    parent.next = newNode;
                    return;
                }
            }
        }
    }
    public void display(){
        display(root);      
    }
    
    private void display(NodoArbolBinario<B> root){
        if(root!=null){
            display(root.previous);
            System.out.print(" " + root.getData());
            display(root.next);
        }
    }
    
    public String displayIn(){
        lookstack = "";
        displayIn(root);
        return lookstack;
    }
    
    private void displayIn(NodoArbolBinario<B> root){
        if(root!=null){
            displayIn(root.previous);
            lookstack += root.getData();
            System.out.print(" " + root.getData());
            displayIn(root.next);
        }
    }
    
    public void displayPre(){
    	
        displayPre(root);      
    }
    
    private void displayPre(NodoArbolBinario<B> root){
        if(root!=null){
            System.out.print(" " + root.getData());
            displayPre(root.previous);
            displayPre(root.next);
        }
    }
    
 
    
    public int findHeight(NodoArbolBinario<B> current) {
    if (current == null) {
        return -1;
    }
        int lefth = findHeight(current.previous);
        int righth = findHeight(current.next);

    if (lefth > righth) {
        return lefth + 1;
    } else {
        return righth + 1;
    }
    }
 
    public static void main (String[] args) {
    	BinarySearchTree<Integer> a1 = new BinarySearchTree<Integer>();
    	Oleada o1 = new Oleada(5);
    	Lista<Integer> listaEdades = new Lista<Integer>();
		for (int i=0;i<o1.listaDragones.getTamaño();i++) {
			listaEdades.insertarFinal(o1.listaDragones.retornar(i).getEdad());
		}
    	for (int i = 0; i < listaEdades.getTamaño(); i++) { 
    		a1.insert(listaEdades.retornar(i));
    	}
    	a1.display();
    	
    }
    

        
        
 

}