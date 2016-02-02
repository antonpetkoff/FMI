package red.black.tree;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class TestRedBlackTree {

    static Random rand = new Random();
    RedBlackTree<Integer, Integer> tree;

    public static <T> void swap(final T[] array, final int a, final int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    
    public static <T> void shuffle(T[] array) {
        for (int i = 0; i < array.length; ++i) {
            swap(array, i, i + rand.nextInt(array.length - i));
        }
    }
    
    
    @Before
    public void setUp() {
        tree = new RedBlackTree<Integer, Integer>();
    }
    
//    @Test
//    public void testLeftRotation() {
//        RedBlackTree<Integer, Integer>.Node x = new RedBlackTree<Integer, Integer>(1, 1, RED);
//    }
    
    @Test
    public void testInsertion() {
        int iterations = 20000;
        
        Integer[] arr = new Integer[iterations];
        for (int i = 0; i < iterations; ++i) {
            arr[i] = i;
        }
        shuffle(arr);

        for (int i = 0; i < iterations; ++i) {
            assertTrue(tree.isRedBlackTree());
//            System.out.println("Now inserting: " + arr[i]);
            tree.insert(arr[i], arr[i]);
//            tree.printTree();
            assertTrue(tree.isRedBlackTree());
        }
//        assertTrue(tree.isRedBlackTree());
//        tree.insert(7, 7);
//        tree.printTree();
//        assertTrue(tree.isRedBlackTree());
//        tree.insert(0, 0);
//        tree.printTree();
//        assertTrue(tree.isRedBlackTree());
//        tree.insert(1, 1);
//        assertTrue(tree.isRedBlackTree());
//        tree.printTree();
//        assertTrue(tree.isRedBlackTree());
    }
    
}
