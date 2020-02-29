import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] queue;
    // construct an empty randomized queue
    public RandomizedQueue(){
        queue = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    private void resize(int capacity){
        Item[] resized = (Item[]) new Object[capacity];
        for(int i = 0; i < size; ++i){
            resized[i] = queue[i];
        }
        queue = resized;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null)
            throw new IllegalArgumentException();

        if (size == queue.length){
            resize(2*queue.length);
        }
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        int index = StdRandom.uniform(size);
        Item removed = queue[index];

        if (index != size - 1)
            queue[index] = queue[size - 1];

        --size;
        queue[size] = null;

        if (size > 0 && size == queue.length / 4)
            resize(queue.length / 2);

        return removed;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        // Store Items in a ResizingArray
        private int itsize = size;
        private final Item[] iter;

        private RandomizedIterator() {
            iter = (Item[]) new Object[itsize];
            for (int i = 0; i < itsize; i++)
                iter[i] = queue[i];
        }

        @Override
        public boolean hasNext() { return itsize > 0; }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            int index = StdRandom.uniform(itsize);

            Item ans = iter[index];

            if (index != itsize - 1)
                iter[index] = iter[itsize - 1];

            itsize--;
            iter[itsize] = null;
            return ans;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        // hello world
    }
}
