public class ArrayDeque<T> {
    private T[] array;
    private int size = 0;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /*resize the underlying array to the target capacity*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = array[(i + nextFirst + 1) % 8];
        }
        array = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (array.length == size) {
            resize(array.length * 2);
        }
        array[nextFirst] = item;
        size = size + 1;
        nextFirst = (nextFirst + 7) % 8;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[nextLast] = item;
        nextLast = (nextLast + 1) % 8;
        size = size + 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[(i + nextFirst + 1) % 8] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % 8;
        T x = array[nextFirst];
        array[nextFirst] = null;
        size = size - 1;
        if (size < array.length / 4) {
            resize(array.length / 2);
        }
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast + 7) % 8;
        T x = array[nextLast];
        array[nextLast] = null;
        size = size - 1;
        if (size < array.length / 4) {
            resize(array.length / 2);
        }
        return x;
    }

    public T get(int index) {
        return array[(index + nextFirst + 1) % 8];
    }
}
