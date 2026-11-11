package HardTask.Lists;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList implements MyList {

    private int size = 0;
    private MyLinkedList.Node first;
    private MyLinkedList.Node last;

    private class Node {
        Object item;
        MyLinkedList.Node next;
        MyLinkedList.Node prev;

        Node(MyLinkedList.Node prev, Object element, MyLinkedList.Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class MyLinkedIterator implements Iterator<MyLinkedList.Node>{
        private MyLinkedList.Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public MyLinkedList.Node next(){
            if(hasNext()){
                Node res = node;
                node = node.next;
                return res;
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void add(Object e){
        Objects.requireNonNull(e, "Null elements are not allowed");
        MyLinkedList.Node lastNode = last;
        MyLinkedList.Node newNode = new MyLinkedList.Node(lastNode, e, null);
        last = newNode;
        if (lastNode == null)
            first = newNode;
        else
            lastNode.next = newNode;
        size++;

    }

    public void prepend(Object e){
        Objects.requireNonNull(e, "Null elements are not allowed");
        MyLinkedList.Node fisrtNode = first;
        MyLinkedList.Node newNode = new MyLinkedList.Node(null, e, fisrtNode);
        first = newNode;
        if(fisrtNode == null){
            last = newNode;
        }
        else{
            fisrtNode.prev = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, Object element){
        Objects.requireNonNull(element, "Null elements are not allowed");
        if(!isPositionIndex(index)){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            prepend(element);
        } else if (index == size) {
            add(element);
        }else {
            MyLinkedList.Node nextNode = find(index);
            MyLinkedList.Node prevNode = nextNode.prev;
            MyLinkedList.Node newNode = new MyLinkedList.Node(prevNode, element, nextNode);
            prevNode.next = newNode;
            nextNode.prev = newNode;
            size++;
        }
    }

    @Override
    public void addAll(Object[] c){
        for (Object o : c) {
            add(o);
        }
    }

    @Override
    public void addAll(int index, Object[] c){
        if(!isPositionIndex(index)){
            throw new IndexOutOfBoundsException();
        }
        int indexShift = 0;
        for (Object o : c) {
            add(index + indexShift, o);
            indexShift++;
        }
    }

    @Override
    public Object get(int index) {
        if(!isElementIndex(index)){
            throw new IndexOutOfBoundsException();
        }
        return find(index).item;
    }

    public Object removeLast(){
        MyLinkedList.Node lastNode = last;
        if(lastNode == null){throw new NoSuchElementException("No elements in list");}
        MyLinkedList.Node prevNode = lastNode.prev;
        if(prevNode != null){
            last = prevNode;
            prevNode.next = null;
        }
        else{
            last = null;
            first = null;
        }
        lastNode.prev = null;
        size--;
        return lastNode.item;
    }

    public Object removeFirst(){
        MyLinkedList.Node firstNode = first;
        if(firstNode == null){throw new NoSuchElementException("No elements in list");}
        MyLinkedList.Node nextNode = firstNode.next;
        if(nextNode != null){
            first = nextNode;
            nextNode.prev = null;
        }
        else{
            last = null;
            first = null;
        }
        firstNode.next = null;
        size--;
        return firstNode.item;
    }

    @Override
    public Object remove(int index) {
        MyLinkedList.Node node;
        if(!isElementIndex(index)){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }else {
            node = find(index);
            MyLinkedList.Node prevNode = node.prev;
            MyLinkedList.Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            node.prev = null;
            node.next = null;
            size--;
        }
        return node.item;
    }

    @Override
    public void set(int index, Object element) {
        Objects.requireNonNull(element, "Null elements are not allowed");
        if(!isElementIndex(index)){
            throw new IndexOutOfBoundsException();
        }
        MyLinkedList.Node nextNode = find(index);
        nextNode.item = element;
    }

    @Override
    public int indexOf(Object o) {
        Objects.requireNonNull(o, "Null elements are not allowed");
        return find(o);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (MyLinkedList.Node x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    private MyLinkedList.Node find(int index){
        MyLinkedIterator listIterator = new MyLinkedIterator();
        int count = 0;
        while (listIterator.hasNext()){
            if(count == index){
                return listIterator.next();
            }
            listIterator.next();
            count++;
        }
        throw new IllegalStateException("Node not found");
    }

    private int find(Object o){
        MyLinkedIterator listIterator = new MyLinkedIterator();
        int count = 0;
        while (listIterator.hasNext()){
            MyLinkedList.Node node = listIterator.next();
            if(node.item.equals(o)){
                return count;
            }
            count++;
        }
        return -1;
    }

    private boolean isPositionIndex(int index){
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index){
        return index >= 0 && index < size;
    }
}
