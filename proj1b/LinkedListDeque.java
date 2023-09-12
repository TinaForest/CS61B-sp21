public class LinkedListDeque<T> implements Deque<T>{
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
    public LinkedListDeque() {
        sentinel = new TNode();
    }
    @Override
    public void addFirst(T item) {
        TNode first = new TNode(sentinel, sentinel.next, item);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        TNode last = new TNode(sentinel.prev, sentinel, item);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return (sentinel.next == sentinel);
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        TNode L = sentinel.next;
        while (L != sentinel) {
            System.out.print(L.item + " ");
            L = L.next;
        }
    }
    @Override
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
    @Override
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
    @Override
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

