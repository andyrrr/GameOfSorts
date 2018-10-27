
import java.util.NoSuchElementException;



public class BTree<T extends Comparable<T>> {

    private Node<T> root;

    /**
     * Creates an empty balanced tree.
     */
    public BTree() {
        root = null;
    }

    /**
     * Creates a balances tree using the given node as tree root.
     */
    public BTree(Node<T> root) {
        this.root = root;
    }

    /**
     * Inserts an element into the tree.
     */
    public void insert(T info) {
        insert(info, root, null, false);
    }

    /**
     * Checks whether the given element is already in the tree.
     */
    public boolean isMember(T info) {
        return isMember(info, root);
    }
    public Node<T> getMember(T info) {
        return getMember(info, root);
    }
    /**
     * Removes an elememt from the tree.
     */
    public void delete(T info) {
        delete(info, root);
    }

    /**
     * Returns a text representation of the tree.
     */
    public String toString() {
        return inOrder();
    }

    /**
     * Returns all elements of the tree in in-order traversing.
     */
    public String inOrder() {
        return inOrder(root);
    }

    /**
     * Returns all elements of the tree in pre-order traversing.
     */
    public String preOrder() {
        return preOrder(root);
    }

    /**
     * Returns all elements of the tree in post-order traversing.
     */
    public String postOrder() {
        return postOrder(root);
    }

    /**
     * Returns the height of the tree.
     */
    public int getHeight() {
        return getHeight(root);
    }

    private void insert(T info, Node<T> node, Node<T> parent, boolean right) {

        if (node == null) {
            if (parent == null) {
                root = node = new Node<T>(info, parent);
            } else if (right) {
                parent.right = node = new Node<T>(info, parent);
            } else {
                parent.left = node = new Node<T>(info, parent);
            }
            restructInsert(node, false);
        } else if (info.compareTo(node.information) == 0) {
            node.information = info;
        } else if (info.compareTo(node.information) > 0) {
            insert(info, node.right, node, true);
        } else {
            insert(info, node.left, node, false);
        }
    }

    private boolean isMember(T info, Node<T> node) {

        boolean member = false;

        if (node == null) {
            member = false;
        } else if (info.compareTo(node.information) == 0) {
            member = true;
        } else if (info.compareTo(node.information) > 0) {
            member = isMember(info, node.right);
        } else {
            member = isMember(info, node.left);
        }

        return member;
    }
    private Node<T> getMember(T info, Node<T> node) {

        Node<T> member = null;

        if (node == null) {
            member = null;
        } else if (info.compareTo(node.information) == 0) {
            member = node;
        } else if (info.compareTo(node.information) > 0) {
            member = getMember(info, node.right);
        } else {
            member = getMember(info, node.left);
        }

        return member;
    }

    private void delete(T info, Node<T> node) throws NoSuchElementException {

        if (node == null) {
            throw new NoSuchElementException();
        } else if (info.compareTo(node.information) == 0) {
            deleteNode(node);
        } else if (info.compareTo(node.information) > 0) {
            delete(info, node.right);
        } else {
            delete(info, node.left);
        }
    }

    private void deleteNode(Node<T> node) {

        Node<T> eNode, minMaxNode, delNode = null;
        boolean rightNode = false;

        if (node.isLeaf()) {
            if (node.parent == null) {
                root = null;
            } else if (node.isRightNode()) {
                node.parent.right = null;
                rightNode = true;
            } else if (node.isLeftNode()) {
                node.parent.left = null;
            }
            delNode = node;
        } else if (node.hasLeftNode()) {
            minMaxNode = node.left;
            for (eNode = node.left; eNode != null; eNode = eNode.right) {
                minMaxNode = eNode;
            }
            delNode = minMaxNode;
            node.information = minMaxNode.information;

            if (node.left.right != null) {
                minMaxNode.parent.right = minMaxNode.left;
                rightNode = true;
            } else {
                minMaxNode.parent.left = minMaxNode.left;
            }

            if (minMaxNode.left != null) {
                minMaxNode.left.parent = minMaxNode.parent;
            }
        } else if (node.hasRightNode()) {
            minMaxNode = node.right;
            delNode = minMaxNode;
            rightNode = true;

            node.information = minMaxNode.information;

            node.right = minMaxNode.right;
            if (node.right != null) {
                node.right.parent = node;
            }
            node.left = minMaxNode.left;
            if (node.left != null) {
                node.left.parent = node;
            }
        }
        restructDelete(delNode.parent, rightNode);
    }

    private int getHeight(Node<T> node) {
        int height = 0;

        if (node == null) {
            height = -1;
        } else {
            height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
        return height;
    }

    private String inOrder(Node<T> node) {

        String result = "";
        if (node != null) {
            result = result + inOrder(node.left) + " ";
            result = result + node.information.toString();
            result = result + inOrder(node.right);
        }
        return result;
    }

    private String preOrder(Node<T> node) {

        String result = "";
        if (node != null) {
            result = result + node.information.toString() + " ";
            result = result + preOrder(node.left);
            result = result + preOrder(node.right);
        }
        return result;
    }

    private String postOrder(Node<T> node) {

        String result = "";
        if (node != null) {
            result = result + postOrder(node.left);
            result = result + postOrder(node.right);
            result = result + node.information.toString() + " ";
        }
        return result;
    }

    private void restructInsert(Node<T> node, boolean wasRight) {

        if (node != root) {
            if (node.parent.balance == '_') {
                if (node.isLeftNode()) {
                    node.parent.balance = '/';
                    restructInsert(node.parent, false);
                } else {
                    node.parent.balance = '\\';
                    restructInsert(node.parent, true);
                }
            } else if (node.parent.balance == '/') {
                if (node.isRightNode()) {
                    node.parent.balance = '_';
                } else {
                    if (!wasRight) {
                        rotateRight(node.parent);
                    } else {
                        doubleRotateRight(node.parent);
                    }
                }
            } else if (node.parent.balance == '\\') {
                if (node.isLeftNode()) {
                    node.parent.balance = '_';
                } else {
                    if (wasRight) {
                        rotateLeft(node.parent);
                    } else {
                        doubleRotateLeft(node.parent);
                    }
                }
            }
        }
    }

    private void restructDelete(Node<T> z, boolean wasRight) {

        Node<T> parent;
        boolean isRight = false;
        boolean climb = false;
        boolean canClimb;

        if (z == null) {
            return;
        }

        parent = z.parent;
        canClimb = (parent != null);

        if (canClimb) {
            isRight = z.isRightNode();
        }

        if (z.balance == '_') {
            if (wasRight) {
                z.balance = '/';
            } else {
                z.balance = '\\';
            }
        } else if (z.balance == '/') {
            if (wasRight) {
                if (z.left.balance == '\\') {
                    doubleRotateRight(z);
                    climb = true;
                } else {
                    rotateRight(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            } else {
                z.balance = '_';
                climb = true;
            }
        } else {
            if (wasRight) {
                z.balance = '_';
                climb = true;
            } else {
                if (z.right.balance == '/') {
                    doubleRotateLeft(z);
                    climb = true;
                } else {
                    rotateLeft(z);
                    if (z.balance == '_') {
                        climb = true;
                    }
                }
            }
        }

        if (canClimb && climb) {
            restructDelete(parent, isRight);
        }
    }

    private void rotateLeft(Node<T> a) {

        Node b = a.right;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.right = b.left;
        if (a.right != null) {
            a.right.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.left = a;

        if (b.balance == '_') {
            a.balance = '\\';
            b.balance = '/';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void rotateRight(Node<T> a) {

        Node b = a.left;

        if (a.parent == null) {
            root = b;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = b;
            } else {
                a.parent.right = b;
            }
        }

        a.left = b.right;
        if (a.left != null) {
            a.left.parent = a;
        }

        b.parent = a.parent;
        a.parent = b;
        b.right = a;

        if (b.balance == '_') {
            a.balance = '/';
            b.balance = '\\';
        } else {
            a.balance = '_';
            b.balance = '_';
        }
    }

    private void doubleRotateLeft(Node<T> a) {

        Node<T> b = a.right;
        Node<T> c = b.left;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.right = c.left;
        if (a.right != null) {
            a.right.parent = a;
        }
        b.left = c.right;
        if (b.left != null) {
            b.left.parent = b;
        }

        c.left = a;
        c.right = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            a.balance = '_';
            b.balance = '\\';
        } else if (c.balance == '\\') {
            a.balance = '/';
            b.balance = '_';
        } else {
            a.balance = '_';
            b.balance = '_';
        }

        c.balance = '_';
    }

    private void doubleRotateRight(Node<T> a) {

        Node<T> b = a.left;
        Node<T> c = b.right;

        if (a.parent == null) {
            root = c;
        } else {
            if (a.isLeftNode()) {
                a.parent.left = c;
            } else {
                a.parent.right = c;
            }
        }

        c.parent = a.parent;

        a.left = c.right;
        if (a.left != null) {
            a.left.parent = a;
        }
        b.right = c.left;
        if (b.right != null) {
            b.right.parent = b;
        }

        c.right = a;
        c.left = b;

        a.parent = c;
        b.parent = c;

        if (c.balance == '/') {
            b.balance = '_';
            a.balance = '\\';
        } else if (c.balance == '\\') {
            b.balance = '/';
            a.balance = '_';
        } else {
            b.balance = '_';
            a.balance = '_';
        }
        c.balance = '_';
    }
    
    public String trasOrder() {
        return trasOrder(root);
    }
    
    private String trasOrder(Node<T> node) {

        String result = "";
        if (node != null) {
            result = result + trasOrder(node.right);
            result = result + node.information.toString() + " ";
            result = result + trasOrder(node.left);
            
        }
        return result;
    }
    
    
    private String savePre(Node<T> node) {

        String result = "";
        if (node != null) {
            result = result + node.getAll() + ",";
            result = result + savePre(node.left);
            result = result + savePre(node.right);
        }
        return result;
    }
}
    
    
    