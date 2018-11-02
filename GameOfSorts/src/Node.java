 public class Node<T> {

        public T information;
        
        public boolean Active;
        
        public String email;
        
        public String nombre;
        
        public float money;

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
                        this.email= " ";
                        this.Active = false;
                        this.money = 0;
                    }
                    if (parts.length>=2)
                    {
                        this.email = parts[1];
                        this.Active = false;
                    }
                    if (parts.length>=3)
                    {
                        this.Active= Boolean.valueOf(parts[2]);
                        this.money = 0;
                    }
                    if (parts.length>=4)
                    {
                        this.money = Float.valueOf(parts[3]);
                    }
                }
                else
                {
                    this.information = information;
                    this.nombre = (String) information;
                    this.email= " ";
                    this.money = 0;
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
        public String getEmail()
        {
            return email;
        }
        public Boolean isActive()
        {
            return Active;
        }
        public void setActive(boolean newActive)
        {
            Active = newActive;
        }
        public void changeEmail(String newEmail)
        {
            email = newEmail;
        }
        public String getAll()
        {
            System.out.println(nombre);
            return nombre + ">" + email + ">" + Active+ ">" + Float.toString(money);
        }
        public void addMoney(float monto)
        {
            this.money += monto;
        }
        
    }
