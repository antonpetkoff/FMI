package vectors;

public class PersistentVector {

    public static final int BITS = 5;
    public static final int BRANCHING_FACTOR = (int) Math.pow(2, BITS);

    public static class Node implements Cloneable {
        Object[] pointers;

        public Node() {
            pointers = new Object[BRANCHING_FACTOR];
        }

        @Override
        public Node clone() {
            Node copy = new Node();

            for (int i = 0; i < BRANCHING_FACTOR; ++i) {
                copy.pointers[i] = this.pointers[i];
            }

            return copy;
        }
    }

    public static class PVector {
        Node root;
        int size;

        public PVector(Node root, int size) {
            this.root = root;
            this.size = size;
        }
    }

    public static PVector empty() {
        return new PVector(new Node(), 0);
    }

    public static boolean isEmpty(PVector vector) {
        return vector.size == 0;
    }

    private static Node getLeaf(PVector vector, int index) {
        // TODO:
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Hi vector");
    }

}
