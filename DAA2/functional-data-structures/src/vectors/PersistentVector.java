package vectors;

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
    }

    public static PVector empty() {
        return new PVector(new Object[BASE], 0);
    }

    public static boolean isEmpty(PVector vector) {
        return vector.size == 0;
    }

    public static int closestPowerOfBase(int n, int base) {
        return (int) Math.ceil(Math.log(n) / Math.log(base));
    }

    // how many levels does a tree with `size` leafs have
    public static int getTreeDepth(int size) {
        return size == 0 ? -1 : closestPowerOfBase(size, BASE);
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

    public static PVector conj(PVector vector, int value) {
        PVector result = new PVector(clone(vector.root, BASE), vector.size + 1);
        Object[] pathCopiedLeaf = null;

        if (vector.size < BASE) {   // the root is the leaf
            pathCopiedLeaf = result.root;   // we have already cloned the root
        } else if (vector.size == closestPowerOfBase(vector.size, BASE)) {  // all leafs are full
            Object[] newRoot = new Object[32];  // TODO: uniform node creation
            newRoot[0] = vector.root;   // the newRoot adds one more level of depth
            pathCopiedLeaf = createPathCopiedLeaf(newRoot, result.size, result.size - 1);
        } else {    // at least one empty leaf exists
            pathCopiedLeaf = createPathCopiedLeaf(result.root, result.size, result.size - 1);
        }

        pathCopiedLeaf[vector.size % BASE] = value; // append the new value
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(closestPowerOfBase(0, 2));
//        for (int i = 0; i < 60; ++i) {
//            int rDepth = (int) Math.pow(BASE, getTreeDepth(i) - 1);
//            System.out.println(i + "\t" + rDepth + "\t depth = " + getTreeDepth(i));
//        }
        PVector v = conj(empty(), 1);
    }
}
