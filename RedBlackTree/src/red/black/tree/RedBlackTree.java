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
        // TODO
    }
    
    private void insertNode(Node x, K key) {
        Node iter = root, parent = null;
        while (iter != null) {
            parent = iter;
            int comp = key.compareTo(iter.key);
            if (comp < 0) {
                iter = iter.left;
            } else if (comp > 0) {
                iter = iter.right;
            } else {
                // TODO: what do we do with equal (repeating) keys?
            }
        }
        // TODO: work in progress
        
    }

    @Override
    public void remove(K key) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public V get(K key) {
        if (key == null) throw new NullPointerException("null keys disallowed!");
        Node target = findNode(key);
        return target == null ? null : target.value;
    }

    @Override
    public boolean contains(K key) {
        return findNode(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    boolean isBST() {
        return isBST(root);
    }
    
    boolean isBST(Node x) {
        boolean flag = true;
        if (x.left != null) {
            flag = flag && x.left.key.compareTo(x.key) <= 0 && isBST(x.left);
        }
        if (x.right != null) {
            flag = flag && x.key.compareTo(x.right.key) <= 0 && isBST(x.right);
        }
        return flag;
    }
    
    boolean areRedParentsCorrect() {
        return areRedParentsCorrect(root);
    }
    
    boolean areRedParentsCorrect(Node x) {
        boolean flag = true;
        if (x != null && x.color == RED) {
            flag = flag && (x.left == null ? true : x.left.color == BLACK && areRedParentsCorrect(x.left))
                        && (x.right == null ? true : x.right.color == BLACK && areRedParentsCorrect(x.right));
        }
        return flag;
    }
    
    boolean areBlackHeightsCorrect() {
        int blackHeight = 0;
        Node iter = root;
        while (iter != null) {
            if (iter.color == BLACK) ++blackHeight;
            iter = iter.left;
        }
        areBlackHeightsCorrect(root, blackHeight);
        return false;
    }
    
    boolean areBlackHeightsCorrect(Node x, int height) {
        if (x == null && height == 0)   return true;
        if (x.color == BLACK) {
            --height;
        }
        return areBlackHeightsCorrect(x.left, height - 1) && areBlackHeightsCorrect(x.right, height - 1);
    }
    
    boolean isRedBlackTree() {
        return root == null ? true : 
            (root.color == BLACK && isBST() && areRedParentsCorrect() && areBlackHeightsCorrect());
    }
    
}
