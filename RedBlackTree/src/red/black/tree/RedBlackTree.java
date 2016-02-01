package red.black.tree;

public class RedBlackTree<K extends Comparable<K>, V> implements IRedBlackTree<K, V> {
 
    // TODO: access modifiers
    static final boolean RED = true;
    static final boolean BLACK = false;
    
    public class Node {
        boolean color;
        Node p, left, right;
        K key;
        V value;
    }
    
    private Node root;
    private int size;
    
    public RedBlackTree() {
        // TODO Auto-generated constructor stub
    }
    
    // precondition: y is x's non-null right child
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;           // 1) make y's left subtree be x's right subtree
        if (y.left != null) {
            y.left.p = x;
        }
        y.p = x.p;                  // 2) make y's parent be x's parent
        if (y.p == null) {
            root = y;
        } else if (y == y.p.left) { // y is left child
            y.p.left = y;
        } else {                    // y is right child
            y.p.right = y;
        }
        x.p = y;                    // 3) x and y switch levels
        y.left = x;
    }
    
    // precondition: x is y's non-null left child
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;           // 1) make x's right subtree be y's left subtree
        if (x.right != null) {
            x.right.p = y;
        }
        x.p = y.p;                  // 2) make y's parent be x's parent
        if (x.p == null) {
            root = x;
        } else if (x == x.p.left) { // x is left child
            x.p.left = x;
        } else {                    // x is right child
            x.p.right = x;
        }
        y.p = x;                    // 3) x and y switch levels
        x.right = y;
    }
    
    /**
     * Replace the subtree rooted at u with the subtree rooted at v.
     * @param u     the root of the subtree to be replaced
     * @param v     the root of the replacement subtree
     */
    private void transplant(Node u, Node v) {
        if (u.p == null) {
            root = v;
        } else if (u == u.p.left) {
            u.p.left = v;
        } else {
            u.p.right = v;
        }
        v.p = u.p;
    }
    
    /**
     * Gets the (non-null) node with the smallest key (left-most leaf) in the given subtree.
     * @param x the subtree from which to take the minimum element
     * @return  the node with the smallest key (left-most leaf) in the given subtree
     */
    private Node treeMinimum(Node x) {
        Node iter = x;
        while (iter.left != null) {
            iter = iter.left;
        }
        return iter;
    }
    
    /**
     * Find (the first) node matching the given key.
     * @param key   The key which the found node must match
     * @return      The node matching the given key or null if the node doesn't exist.
     */
    private Node findNode(K key) {
        Node iter = root;
        while (iter != null && !iter.key.equals(key)) {
            int comp = iter.key.compareTo(key);
            if (comp < 0) {
                iter = iter.right;
            } else if (comp > 0) {
                iter = iter.left;
            } else {
                break;
            }
        }
        return iter;    // the found node or null if not found
    }
    
    @Override
    public void insert(K key, V value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void remove(K key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public V get(K key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(K key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

}
