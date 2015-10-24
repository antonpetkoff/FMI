package linked.list;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {

    @Test
    public void testRemovAt() {
        final int N = 10;
        LinkedList list = new LinkedList();
        
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        
        assertEquals(10, list.size());
        list.removeAt(list.get(N - 1));;
        assertEquals(N - 2, list.get(N - 2).element);
        assertEquals(N - 1, list.size());
        
        list.removeAt(list.get(0));
        assertEquals(1, list.get(0).element);
        assertEquals(N - 2, list.size());
        
        // the list now is [1, 2, 3, 4, 5, 6, 7, 8]
        list.removeAt(list.get(N / 2));
        assertEquals(7, list.get(N / 2).element);
        assertEquals(N - 3, list.size());
    }
    
    @Test
    public void testRemoveElement() {
        LinkedList list = new LinkedList();
        list.add(0);
        list.add(1);
        list.add(2);
        
        assertFalse(list.removeElement(3));
        assertEquals(3, list.size());
        
        assertTrue(list.removeElement(1));
        assertEquals(2, list.size());
    }
    
    @Test
    public void testCopy() {
        final int N = 1000;
        LinkedList original = new LinkedList(), copy;
        
        for (int i = 0; i < N; ++i) {
            original.add(i);
        }
        
        copy = original.copy();
        
        assertEquals(copy.size(), original.size());
        
        for (int i = 0; i < N; ++i) {
            assertEquals(original.get(i).element, copy.get(i).element);
            assertNotEquals(original.get(i), copy.get(i));
        }
    }
    
}
