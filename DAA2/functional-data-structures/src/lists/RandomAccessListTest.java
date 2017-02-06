package lists;

import static junit.framework.Assert.*;
import static lists.RandomAccessList.*;

public class RandomAccessListTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void testIsEmpty() throws Exception {
        assertTrue(isEmpty(empty()));

        assertFalse(isEmpty(cons(1, empty())));
    }

    @org.junit.Test
    public void testCons() throws Exception {

    }

    @org.junit.Test
    public void testHead() throws Exception {

    }

    @org.junit.Test
    public void testTail() throws Exception {

    }

    @org.junit.Test
    public void testGet() throws Exception {

    }

    @org.junit.Test
    public void testUpdate() throws Exception {

    }

}