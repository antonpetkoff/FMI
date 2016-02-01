package red.black.tree;

import org.junit.Test;

public class TestRedBlackTree {

    public static boolean isBST(RedBlackTree<Integer, Integer>.Node x) {
        if (x == null) return true;
        
        boolean flag = true;
        if (x.left != null) {
            flag = flag && x.left.value <= x.value && isBST(x.left);
        }
        if (x.right != null) {
            flag = flag && x.value <= x.right.value && isBST(x.right);
        }
        return flag;
    }
    
    @Test
    public void test() {
        
    }
    
}
