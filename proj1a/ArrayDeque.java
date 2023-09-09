public class ArrayDeque<Item> {
    private Item[] array;
    private int size;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        array = (Item[]) new Object[10];
        size = 0;
    }
    /*resize the underlying array to the target capacity*/
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(array, 0, a, 0, size);
        array = a;
    }
    public void addFirst(Item x){
        if (array.length==size) {
            resize(size * 2);
        }
        System.arraycopy(array,0,array,1,size);
        array[0]=x;
        size=size+1;
    }
    /**
     * Inserts X into the back of the list.
     */
    public void addLast(Item x) {
        if (size == array.length) {
            resize(size * 2);
        }
        array[size] = x;
        size = size + 1;
    }
    public boolean isEmpty(){
        return (size==0);
    }
    /**
     * Returns the item from the back of the list.
     */
    public Item getLast() {
        return array[size - 1];
    }
    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }
    public Item removeFirst(){
        Item x=array[0];
        System.arraycopy(array,1,array,0,size-1);
        array[size]=null;
        size=size-1;
        return x;
    }
    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public Item removeLast() {
        Item x = array[size - 1];
        array[size]=null;
        size = size - 1;
        return x;
    }
    public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(array[i]+" ");
        }
    }
    /**
     * Gets the ith item in the list (0 is the front).
     */
    public Item get(int i) {
        return array[i];
    }
} 