package linked.list;


public class LinkedList {
    
    public static class Node {
        public int element;
        public Node right;
        public Node left;
        
        public Node(int element) {
            this.element = element;
        }
    }
    
    private Node head;
    private Node tail;
    
    public void add(int element) {
        if (this.head == null) {
            this.head = new Node(element);
            this.tail = this.head;
        }
        else {
            Node node = new Node(element);
            this.tail.right = node;
            node.left = this.tail;
            this.tail = node;
        }
    }
    
    public Node get(int index) {
        if (this.head == null)
            return null;
        
        Node current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.right;
        }
        return current;
    }
    
    public Node nodeOf(int element) {
        Node current = this.head;
        while (current != null) {
            if (current.element == element) 
                return current;
            current = current.right;
        }
        return null;
    }
    
    public void insert(int element, Node left) {
        Node node = new Node(element);
        if (left == null) {
            this.head.left = node;
            node.right = this.head;
            this.head = node;
        }
        else {
            node.left = left;
            node.right = left.right;
            left.right = node;
            node.right.left = node;
        }
    }
    
    public int size() {
        int size = 0;
        Node current = this.head;
        while (current != null) {
            size++;
            current = current.right;
        }
        return size;
    }
    
    public void removeFirst() {
        if (head != null) {
            head = head.right;
            if (head != null) {
                head.left = null;
            } else {
                tail = null;        // if the last node was removed
            }
        }
    }
    
    public void removeLast() {
        if (tail != null) {
            tail = tail.left;
            if (tail != null) {
                tail.right = null;
            } else {
                head = null;        // if the last node was removed
            }
        }
    }
    
    // Removes the given node from the list.
    public void removeAt(Node node) {
        if (node.left == null) {            // node is head
            removeFirst();
        } else if (node.right == null) {    // node is tail
            removeLast();
        } else {                            // node is in the middle
            node.left.right = node.right;
            node.right.left = node.left;
        }
    }
    
    /**
     * Removes the first element with value @element if such an element exists
     * and returns true. Otherwise, returns false.
     */
    public boolean removeElement(int element) {
        Node node = nodeOf(element);
        
        if (node != null) {
            removeAt(node);
            return true;
        }
        
        return false;
    }
    
    // Returns a copy of the entire list.
    public LinkedList copy() {
        LinkedList list = new LinkedList();
        Node iter = head;
        
        while (iter != null) {
            list.add(iter.element);
            iter = iter.right;
        }
        
        return list;
    }
    
}