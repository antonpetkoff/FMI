package vectors;

import org.junit.Test;

import static org.junit.Assert.*;
import static vectors.PersistentVector.*;

public class PersistentVectorTest {
    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(empty()));
    }

    @Test
    public void testConjGet() {
        final int SIZE = 1000;
        PVector vector = empty();

        for (int i = 0; i < SIZE; ++i) {
            vector = conj(vector, i);
            assertEquals(i + 1, vector.size);

            for (int j = 0; j < i; ++j) {
                assertEquals(j, get(vector, j));
            }
        }
    }

}