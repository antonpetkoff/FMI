package vectors;

public class PersistentVector {

    public static final int BITS = 1;
    public static final int BASE = (int) Math.pow(2, BITS); // a.k.a branching factor

    // TODO: maybe use a Java built-in method
    public Object[] clone(Object[] array, int size) {
        Object[] copy = new Object[size];

        for (int i = 0; i < BASE; ++i) {
            copy[i] = array[i];
        }

        return copy;
    }

    public static class PVector {
        Object[] root;
        int size;

        public PVector(Object[] root, int size) {
            this.root = root;
            this.size = size;
        }
    }

    public static PVector empty() {
        return new PVector(new Object[BASE], 0);
    }

    public static boolean isEmpty(PVector vector) {
        return vector.size == 0;
    }

    // how many levels does a tree with `size` leafs have
    public static int getTreeDepth(int size) {
        return size == 0 ? -1 : (int) Math.ceil(Math.log(size) / Math.log(BASE));
    }

    private static Object getLeaf(PVector vector, int index) {
        Object[] node = vector.root;
        int mostSignificantPart = (int) Math.pow(BASE, getTreeDepth(vector.size) - 1);

        int branch;
        for (int chunk = mostSignificantPart; chunk > 1; chunk /= BASE) {
            branch = (index / chunk) % BASE;

            if (node[branch] == null) {
                System.out.println("Branch " + branch + " doesn't exist! You must create it!");
            }

            node = (Object[]) node[branch];
        }

        return node[index];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 60; ++i) {
            int rDepth = (int) Math.pow(BASE, getTreeDepth(i) - 1);
            System.out.println(i + "\t" + rDepth + "\t depth = " + getTreeDepth(i));
        }
    }
}
