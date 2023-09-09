public class ArrayDeque<T> {
    private T[] array;
    private int size = 0;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        array = (T[]) new Object[10];
    }

    /*resize the underlying array to the target capacity*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(array, 0, a, 0, size);
        array = a;
    }

    public void addFirst(T item) {
        if (array.length == size) {
            resize(size * 2);
        }
        System.arraycopy(array, 0, array, 1, size);
        array[0] = item;
        size = size + 1;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(T item) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[size] = item;
        size = size + 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the number of Ts in the list.
     */
    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T x = array[0];
        System.arraycopy(array, 1, array, 0, size - 1);
        array[size - 1] = null;
        size = size - 1;
        if (size < array.length / 4) {
            resize(size / 4);
        }
        return x;
    }

    /**
     * Deletes T from back of the list and
     * returns deleted T.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T x = array[size - 1];
        array[size - 1] = null;
        size = size - 1;
        if (size < array.length / 4) {
            resize(size / 4);
        }
        return x;
    }

    /**
     * Gets the ith T in the list (0 is the front).
     */
    public T get(int index) {
        return array[index];
    }
}
