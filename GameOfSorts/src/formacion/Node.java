package formacion;
 public class Node<T> {

        public T information;
        
        public boolean Active;
        
        public String nombre;

        public Node<T> parent;

        public Node<T> left;

        public Node<T> right;

        char balance;

        public Node(T information, Node<T> parent) {
            String temp = (String) information;
                if (temp.contains(">"))
                {
                    String[] parts = temp.split(">");
                    if (parts.length>=1)
                    {
                        this.information = (T) parts[0];
                        this.nombre = parts[0];     
                        
                        this.Active = false;
                        
                    }
                    if (parts.length>=2)
                    {
                        
                        this.Active = false;
                    }
                    if (parts.length>=3)
                    {
                        this.Active= Boolean.valueOf(parts[2]);
                        
                    }
                    if (parts.length>=4)
                    {
                        
                    }
                }
                else
                {
                    this.information = information;
                    this.nombre = (String) information;
                    
                }
            

            
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.balance = '_';
        }
        

        boolean isLeaf() {
            return ((left == null) && (right == null));
        }

        boolean isNode() {
            return !isLeaf();
        }

        boolean hasLeftNode() {
            return (null != left);
        }

        boolean hasRightNode() {
            return (right != null);
        }

        boolean isLeftNode() {
            return (parent.left == this);
        }

        boolean isRightNode() {
            return (parent.right == this);
        }
        public Object getData()
        {
            return this.information;
        }
        
        public Boolean isActive()
        {
            return Active;
        }
        public void setActive(boolean newActive)
        {
            Active = newActive;
        }
        
        public String getAll()
        {
            System.out.println(nombre);
			return nombre;
            
        }       
    }
