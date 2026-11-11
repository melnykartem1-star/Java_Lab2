package HardTask.Maps;

public class MyLinkedHashMap extends MyHashMap{

    MyLinkedHashMap.Entry head;
    MyLinkedHashMap.Entry tail;
    final boolean accessOrder;

    static class Entry extends MyHashMap.Node {
        MyLinkedHashMap.Entry before, after;
        Entry(int hash, Object key, Object value, MyHashMap.Node next) {
            super(hash, key, value, next);
        }
    }

    public MyLinkedHashMap(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
    }

    Node newNode(int hash, Object key, Object value, Node next) {
        MyLinkedHashMap.Entry entry = new MyLinkedHashMap.Entry(hash, key, value, next);
        linkNodeAtEnd(entry);
        return entry;
    }

    void afterNodeRemoval(MyHashMap.Node n) {
        MyLinkedHashMap.Entry entry = (MyLinkedHashMap.Entry) n;
        MyLinkedHashMap.Entry previousEntry = entry.before;
        MyLinkedHashMap.Entry nextEntry = entry.after;
        entry.before = entry.after = null;
        if (previousEntry == null)
            head = nextEntry;
        else
            previousEntry.after = nextEntry;
        if (nextEntry == null)
            tail = previousEntry;
        else
            nextEntry.before = previousEntry;
    }

    void afterNodeInsertion() {
        MyLinkedHashMap.Entry first = head;
        if (first != null && removeEldestEntry(first)) {
            remove(first.key);
        }
    }

    void afterNodeAccess(MyHashMap.Node e) {
        if (!accessOrder) return;

        MyLinkedHashMap.Entry last = tail;
        MyLinkedHashMap.Entry entry = (MyLinkedHashMap.Entry) e;
        MyLinkedHashMap.Entry previousEntry = entry.before;
        MyLinkedHashMap.Entry nextEntry = entry.after;

        entry.after = null;
        if (previousEntry == null)
            head = nextEntry;
        else
            previousEntry.after = nextEntry;
        if (nextEntry != null)
            nextEntry.before = previousEntry;
        else
            last = previousEntry;
        if (last == null)
            head = entry;
        else {
            entry.before = last;
            last.after = entry;
        }
        tail = entry;
    }

    private void linkNodeAtEnd(MyLinkedHashMap.Entry entry){
        MyLinkedHashMap.Entry last = tail;
        tail = entry;
        if (last == null)
            head = entry;
        else {
            entry.before = last;
            last.after = entry;
        }
    }

    boolean removeEldestEntry(Entry first) {
        return false;
    }

}
