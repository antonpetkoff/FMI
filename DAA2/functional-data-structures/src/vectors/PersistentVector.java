package vectors;

import java.util.ArrayList;

public class PersistentVector {

    public static final int BITS = 1;
    public static final int BASE = (int) Math.pow(2, BITS); // a.k.a branching factor

    // TODO: maybe use a Java built-in method
    public static Object[] clone(Object[] array, int size) {
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

        @Override
        public String toString() {
            ArrayList<Integer> list = new ArrayList<>(this.size);

            for (int i = 0; i < this.size; ++i) {
                list.add(get(this, i));
            }

            return list.toString();
        }
    }

    public static PVector empty() {
        return new PVector(new Object[BASE], 0);
    }

    public static boolean isEmpty(PVector vector) {
        return vector.size == 0;
    }

    // TODO: closestPowerOfBase(0, 2) under-flows the integer
    private static int closestPowerOfBase(int n, int base) {
        return (int) Math.ceil(Math.log(n) / Math.log(base));
    }

    // how many levels does a tree with `size` leafs have
    private static int getTreeDepth(int size) {
        return size == 0 ? -1 : closestPowerOfBase(size, BASE);
    }

    public static int get(PVector vector, int index) {
        if (index >= vector.size) {
            throw new IndexOutOfBoundsException();
        }

        return (int) getLeafValue(vector, index);
    }

    private static Object getLeafValue(PVector vector, int index) {
        Object[] node = vector.root;
        int mostSignificantPart = (int) Math.pow(BASE, getTreeDepth(vector.size) - 1);

        for (int chunk = mostSignificantPart; chunk > 1; chunk /= BASE) {
            node = (Object[]) node[(index / chunk) % BASE];
        }

        return node[index % BASE];
    }

    /**
     * (with side effects!)
     *
     * Modify the given tree, represented by its root, by copying the specified path
     * and return the leaf. The passed root then has the copied path.
     *
     * This method would usually work on a copy of the root, i.e. the passed root
     * is a copy of the original root.
     *
     * @param root  Represents the tree which will have a path copied.
     * @param leafCount The count of the leafs in the passed tree.
     *                  Needed for calculating which branches to take from the root to the leaf.
     * @param index Identifies the leaf node uniquely in the given tree (trie).
     * @return {Object[]} The leaf which path from the root has been copied.
     */
    private static Object[] createPathCopiedLeaf(Object[] root, int leafCount, int index) {
        Object[] node = root;   // only node from the root to the leaf will be cloned/created
        int mostSignificantPart = (int) Math.pow(BASE, getTreeDepth(leafCount) - 1);
        int childBranch;

        for (int chunk = mostSignificantPart; chunk > 1; chunk /= BASE) {
            childBranch = (index / chunk) % BASE;

            if (node[childBranch] == null) { // the child is missing and must be created
                node[childBranch] = new Object[BASE];   // TODO: a more uniform way of creating nodes
            } else {    // the child must be copied
                node[childBranch] = clone((Object[]) node[childBranch], BASE);
            }

            node = (Object[]) node[childBranch];    // move one level down
        }

        return node;    // we have reached the leaf
    }

    private static boolean isPowerOfBase(int n, int base) {
        return n == (int) Math.pow(base, closestPowerOfBase(n, base));
    }

    public static PVector conj(PVector vector, int value) {
        Object[] newRoot = null;
        Object[] pathCopiedLeaf = null;

        if (vector.size < BASE) {   // the root is the leaf
            newRoot = clone(vector.root, BASE);
            pathCopiedLeaf = newRoot;
        } else if (isPowerOfBase(vector.size, BASE)) {  // all leafs are full
            newRoot = new Object[BASE];
            newRoot[0] = vector.root;   // the newRoot adds one more level of depth
            pathCopiedLeaf = createPathCopiedLeaf(newRoot, vector.size + 1, vector.size);
        } else {    // at least one empty leaf exists
            newRoot = clone(vector.root, BASE);
            pathCopiedLeaf = createPathCopiedLeaf(newRoot, vector.size + 1, vector.size);
        }

        pathCopiedLeaf[vector.size % BASE] = value; // append the new value

        return new PVector(newRoot, vector.size + 1);
    }

    public static void main(String[] args) {
        PVector v1 = conj(empty(), 1);
        PVector v2 = conj(v1, 2);
        PVector v3 = conj(v2, 3);
        PVector v4 = conj(v3, 4);
        PVector v5 = conj(v4, 5);

        System.out.println(get(v5, 4));
        System.out.println(v5);
    }
}
