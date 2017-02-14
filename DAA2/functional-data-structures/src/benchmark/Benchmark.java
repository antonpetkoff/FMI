package benchmark;


import lists.RandomAccessList;
import lists.RandomAccessList.Tree;
import vectors.PersistentVector;

import java.util.Random;

import static lists.RandomAccessList.cons;
import static vectors.PersistentVector.PVector;
import static vectors.PersistentVector.conj;

public class Benchmark {

    public static long listCons(int size) {
        Tree list = RandomAccessList.empty();
        long start = System.nanoTime();

        for (int i = 0; i < size; ++i) {
            list = cons(i, list);
        }

        return System.nanoTime() - start;
    }

    public static long listRandomAccess(int listSize, int accessCount) {
        Tree list = RandomAccessList.empty();
        for (int i = 0; i < listSize; ++i) {
            list = cons(i, list);
        }
        Random rand = new Random();

        long start = System.nanoTime();

        for (int i = 0; i < accessCount; ++i) {
            RandomAccessList.get(list, rand.nextInt(listSize));
        }

        return System.nanoTime() - start;
    }

    public static long listLinearAccess(int listSize) {
        Tree list = RandomAccessList.empty();
        for (int i = 0; i < listSize; ++i) {
            list = cons(i, list);
        }

        long start = System.nanoTime();

        for (int i = 0; i < listSize; ++i) {
            RandomAccessList.get(list, i);
        }

        return System.nanoTime() - start;
    }

    public static long listRandomUpdate(int listSize, int updateCount) {
        Tree list = RandomAccessList.empty();
        for (int i = 0; i < listSize; ++i) {
            list = cons(i, list);
        }
        Random rand = new Random();

        long start = System.nanoTime();

        for (int i = 0; i < updateCount; ++i) {
            list = RandomAccessList.update(list, rand.nextInt(listSize), rand.nextInt(listSize));
        }

        return System.nanoTime() - start;
    }

    public static long listLinearUpdate(int listSize) {
        Tree list = RandomAccessList.empty();
        for (int i = 0; i < listSize; ++i) {
            list = cons(i, list);
        }

        long start = System.nanoTime();

        for (int i = 0; i < listSize; ++i) {
            list = RandomAccessList.update(list, i, i + 1);
        }

        return System.nanoTime() - start;
    }

    public static long listTail(int listSize) {
        Tree list = RandomAccessList.empty();
        for (int i = 0; i < listSize; ++i) {
            list = cons(i, list);
        }

        long start = System.nanoTime();

        for (int i = 0; i < listSize; ++i) {
            list = RandomAccessList.tail(list);
        }

        return System.nanoTime() - start;
    }

    public static long vectorConj(int size) {
        PVector vector = PersistentVector.empty();
        long start = System.nanoTime();

        for (int i = 0; i < size; ++i) {
            vector = conj(vector, i);
        }

        return System.nanoTime() - start;
    }

    public static long vectorRandomAccess(int vectorSize, int accessCount) {
        PVector vector = PersistentVector.empty();
        for (int i = 0; i < vectorSize; ++i) {
            vector = conj(vector, i);
        }
        Random rand = new Random();

        long start = System.nanoTime();

        for (int i = 0; i < accessCount; ++i) {
            PersistentVector.get(vector, rand.nextInt(vectorSize));
        }

        return System.nanoTime() - start;
    }

    public static long vectorLinearAccess(int vectorSize) {
        PVector vector = PersistentVector.empty();
        for (int i = 0; i < vectorSize; ++i) {
            vector = conj(vector, i);
        }

        long start = System.nanoTime();

        for (int i = 0; i < vectorSize; ++i) {
            PersistentVector.get(vector, i);
        }

        return System.nanoTime() - start;
    }

    public static long vectorRandomUpdate(int vectorSize, int updateCount) {
        PVector vector = PersistentVector.empty();
        for (int i = 0; i < vectorSize; ++i) {
            vector = conj(vector, i);
        }
        Random rand = new Random();

        long start = System.nanoTime();

        for (int i = 0; i < updateCount; ++i) {
            vector = PersistentVector.update(vector, rand.nextInt(vectorSize), rand.nextInt(vectorSize));
        }

        return System.nanoTime() - start;
    }

    public static long vectorLinearUpdate(int vectorSize) {
        PVector vector = PersistentVector.empty();
        for (int i = 0; i < vectorSize; ++i) {
            vector = conj(vector, i);
        }

        long start = System.nanoTime();

        for (int i = 0; i < vectorSize; ++i) {
            vector = PersistentVector.update(vector, i, i + 1);
        }

        return System.nanoTime() - start;
    }

    public static long vectorPop(int vectorSize) {
        PVector vector = PersistentVector.empty();
        for (int i = 0; i < vectorSize; ++i) {
            vector = conj(vector, i);
        }

        long start = System.nanoTime();

        for (int i = 0; i < vectorSize; ++i) {
            vector = PersistentVector.pop(vector);
        }

        return System.nanoTime() - start;
    }


    public static void main(String[] args) {
        System.out.println("listCons: " + listCons((int) 1e+6));
        System.out.println("listRandomAccess: " + listRandomAccess((int) 1e+7, (int) 1e+7));
        System.out.println("listLinearAccess: " + listLinearAccess((int) 1e+7));
        System.out.println("listRandomUpdate: " + listRandomUpdate((int) 1e+6, (int) 1e+6));
        System.out.println("listLinearUpdate: " + listLinearUpdate((int) 1e+7));
        System.out.println("listTail: " + listTail((int) 1e+7));

        System.out.println();

        System.out.println("vectorConj: " + vectorConj((int) 1e+6));
        System.out.println("vectorRandomAccess: " + vectorRandomAccess((int) 1e+7, (int) 1e+7));
        System.out.println("vectorLinearAccess: " + vectorLinearAccess((int) 1e+7));
        System.out.println("vectorRandomUpdate: " + vectorRandomUpdate((int) 1e+6, (int) 1e+6));
        System.out.println("vectorLinearUpdate: " + vectorLinearUpdate((int) 1e+7));
        System.out.println("vectorPop: " + vectorPop((int) 1e+7));
    }
}
