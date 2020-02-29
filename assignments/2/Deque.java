import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item>  implements Iterable<Item> {
    // construct an empty deque
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque()
    {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {

        if (item == null){
            throw new IllegalArgumentException();
        }

        Node<Item> oldFirst = first;
        Node<Item> newItem = new Node<>(item);
        if (oldFirst != null){
            newItem.next = oldFirst;
            oldFirst.prev = newItem;
        }
        else {
            last = newItem;
        }

        first = newItem;
        size++;

    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        Node<Item> oldLast = last;
        Node<Item> newItem = new Node<>(item);

        if (oldLast != null){
            newItem.prev = oldLast;
            oldLast.next = newItem;
        }else{
            first = newItem;
        }

        last = newItem;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        Node<Item> oldFirst = first;
        first = first.next;
        if (first!=null){
            first.prev = null;
        }else{
            last = null;
        }

        oldFirst.next = null;
        size--;
        return oldFirst.getItem();

    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        Node<Item> oldLast = last;
        last = last.prev;
        if (last != null){
            last.next = null;
        }else{
            first = null;
        }
        oldLast.prev = null;
        size--;
        return oldLast.getItem();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current ==null){
                throw new NoSuchElementException();
            }

            Item item = current.getItem();
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args){
        //UNIT TESTING
//        Deque<Integer> deque = new Deque<Integer>();
//        deque.addLast(1);
//        System.out.println(deque.removeFirst());
//        System.out.println(deque.isEmpty());
//        deque.addLast(4);
//        StdOut.println(deque.removeFirst());
//
//        deque.addFirst(5);
//        deque.removeLast();
//        deque.addFirst(5);
//        deque.removeLast();


    }


    private static class Node<Item>{

        private final Item item;
        private Node<Item> next;
        private Node<Item> prev;


        public Node(){
            this.item = null;
            this.next = null;
            this.prev = null;
        }

        public Node(Item item){
            this.item = item;
            this.next = null;
            this.prev = null;
        }


        public Item getItem() {
            return item;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }

        public Node<Item> getPrev() {
            return prev;
        }

        public void setPrev(Node<Item> prev) {
            this.prev = prev;
        }
    }


}
