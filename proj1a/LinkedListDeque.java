public class LinkedListDeque<T> {
    private class TNode {
        TNode prev;
        TNode next;
        T item;

        public TNode() {
            prev = this;
            next = this;
        }

        public TNode(TNode x, TNode y, T z) {
            prev = x;
            next = y;
            item = z;
        }
    }

    private TNode sentinel;
    private int size = 0;

    /*Creates an empty linked list deque.*/
    public LinkedListDeque() {
        sentinel = new TNode();
    }

    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        TNode first = new TNode(sentinel, sentinel.next, item);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    /*Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        TNode last = new TNode(sentinel.prev, sentinel, item);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    /*Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return (sentinel.next == sentinel);
    }

    /*Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /*Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        TNode L = sentinel.next;
        while (L != sentinel) {
            System.out.print(L.item + " ");
            L = L.next;
        }
    }

    /*Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        TNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size = size - 1;
        return first.item;
    }

    /*Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        TNode last = sentinel.prev;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        size = size - 1;
        return last.item;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        TNode L = sentinel.next;
        while (index != 0 && L.next != sentinel) {
            L = L.next;
            index = index - 1;
        }
        return L.item;
    }

    /*Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (sentinel.next == sentinel) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(TNode node, int i) {
        while (i > 0 && node.next != sentinel) {
            return getRecursiveHelper(node.next, i - 1);
        }
        return node.item;
    }
}
