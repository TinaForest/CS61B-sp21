package synthesizer;
// task: Make sure to make this class a part of the synthesizer package
// package <package name>;


import java.util.Iterator;

//task: Make sure to make this class and all of its methods public
//task: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // task: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // task: Enqueue the item. Don't forget to increase fillCount and update last.
        if (!isFull()) {
            rb[last] = x;
            last = (last + 1) % capacity;
            fillCount = fillCount + 1;
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // task: Dequeue the first item. Don't forget to decrease fillCount and update
        if (!isEmpty()) {
            T tmp = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity;
            fillCount = fillCount - 1;
            return tmp;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // task: Return the first item. None of your instance variables should change.
        if (!isEmpty()) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
    }

    // task: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return new ArbIterator();
    }

    private class ArbIterator implements Iterator<T> {
        int p;

        public ArbIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return (p < fillCount);
        }

        @Override
        public T next() {
            T t = rb[((first + p) % capacity)];
            p = p + 1;
            return t;
        }
    }
}
