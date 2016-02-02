package red.black.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedBlackTree<K extends Comparable<K>, V> implements IRedBlackTree<K, V> {
 
    // TODO: access modifiers
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    
    public class Node {
        boolean color;
        Node p, left, right;
        K key;
        V value;
        
        public Node() {
            
        }
        
        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof RedBlackTree.Node) {
                @SuppressWarnings("unchecked")
                Node o = (Node) obj;
                return key.equals(o.key) && value.equals(o.value) && color == o.color
                        && left == o.left && right == o.right;
            }
            return false;
        }
        
        @Override
        public String toString() {
            return "[" + key+ ", " + (color == RED ? "RED" : "BLACK")
                    + ", " + (p == null ? "null parent" : "has parent") + "]";
        }
    }
    
    public class BstPrinter<KK extends Comparable<KK>, VV> {

        public RedBlackTree<KK, VV>.Node root;
        
        public BstPrinter(RedBlackTree<KK, VV>.Node root) {
            this.root = root;
        }
        
        public void printTree() {
            int maxLevel = maxLevel(root);
            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private void printNodeInternal(List<RedBlackTree<KK, VV>.Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            printWhitespaces(firstSpaces);

            List<RedBlackTree<KK, VV>.Node> newNodes = new ArrayList<RedBlackTree<KK, VV>.Node>();
            for (RedBlackTree<KK, VV>.Node node : nodes) {
                if (node != null) {
                    System.out.print(node.key + (node.color == RED ? "R" : "B"));
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        printWhitespaces(1);

                    printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        printWhitespaces(1);

                    printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private int maxLevel(RedBlackTree<KK, VV>.Node node) {
            if (node == null)
                return 0;

            return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
        }

        private boolean isAllElementsNull(List<RedBlackTree<KK, VV>.Node> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }
            return true;
        }

    }
    
    private Node root;
    private int size;
    
    public RedBlackTree() {
        // TODO Auto-generated constructor stub
    }
    
    public Node createNode(K key, V value, boolean color) {
        return new Node(key, value, color);
    }
    
    public void printTree() {
        BstPrinter<K, V> printer = new BstPrinter<K, V>(root);
        printer.printTree();
    }
    
    public void printNode(Node node) {
        (new BstPrinter<K, V>(node)).printTree();
    }
    
    // precondition: y is x's non-null right child
    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;           // 1) make y's left subtree be x's right subtree
        if (y.left != null) {
            y.left.p = x;
        }
        y.p = x.p;                  // 2) make y's parent be x's parent
        if (x.p == null) {
            root = y;
        } else if (x == x.p.left) { // y is left child
            x.p.left = y;
        } else {                    // y is right child
            x.p.right = y;
        }
        x.p = y;                    // 3) x and y switch levels
        y.left = x;
    }
    
    // precondition: x is y's non-null left child
    void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;           // 1) make x's right subtree be y's left subtree
        if (x.right != null) {
            x.right.p = y;
        }
        x.p = y.p;                  // 2) make y's parent be x's parent
        if (y.p == null) {
            root = x;
        } else if (y == y.p.left) { // x is left child
            y.p.left = x;
        } else {                    // x is right child
            y.p.right = x;
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
    
    /**
     * Balancing procedure which restores the properties of the Red-Black Tree
     * after insertion.
     * @param z     The node from which the balancing will start.
     */
    private void insertBalanceUp(Node z) {
        if (z.p == null) {
            return;
        }

        Node uncle = null;
//        printTree();
        while (z.p != null && z.p.color == RED) {   // while not root and parent is red
            if (z.p == z.p.p.left) {
                uncle = z.p.p.right == null ? new Node(null, null, BLACK) : z.p.p.right;
                if (uncle.color == RED) {   // case 1: flip colors
                    z.p.color = BLACK;
                    uncle.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                    continue;
                } else if (z == z.p.right) {    // case 2: transform to case 3
                    z = z.p;
                    leftRotate(z);
                }
                z.p.color = BLACK;  // case 3
                z.p.p.color = RED;
                rightRotate(z.p.p);
            } else {
                uncle = z.p.p.left == null ? new Node(null, null, BLACK) : z.p.p.left;
                if (uncle.color == RED) {   // case 1: flip colors
                    z.p.color = BLACK;
                    uncle.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                    continue;
                } else if (z == z.p.left) { // case 2: transform to case 3
                    z = z.p;
                    rightRotate(z);
                }
                z.p.color = BLACK;  // case 3
                z.p.p.color = RED;
                leftRotate(z.p.p);
            }
        }
        root.color = BLACK;
    }
    
    @Override
    public void insert(K key, V value) {
        if (root == null) {
            root = new Node(key, value, BLACK);
        } else {
            insertNode(new Node(key, value, RED));
        }
        // TODO: size increment? what about equal elements?
    }
    
    private void insertNode(Node newNode) {
        Node iter = root, parent = null;
        while (iter != null) {
            parent = iter;
            int comp = newNode.key.compareTo(iter.key);
            if (comp < 0) {
                iter = iter.left;
            } else if (comp > 0) {
                iter = iter.right;
            } else {
                // TODO: what do we do with equal (repeating) keys?
            }
        }
        
        newNode.p = parent;
        if (parent == null) {
            root = newNode;
        } else if (newNode.key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else if (newNode.key.compareTo(parent.key) > 0) {
            parent.right = newNode;
        }
        
        insertBalanceUp(newNode);
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
        if (x == null) return true;
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
        return areBlackHeightsCorrect(root, blackHeight);
    }
    
    boolean areBlackHeightsCorrect(Node x, int height) {
        if (x == null)   return height == 0;
        if (x.color == BLACK) {
            --height;
        }
        return areBlackHeightsCorrect(x.left, height) && areBlackHeightsCorrect(x.right, height);
    }
    
    boolean isRedBlackTree() {
        return root == null ? true : 
            (root.color == BLACK && isBST() && areRedParentsCorrect() && areBlackHeightsCorrect());
    }
    
}
