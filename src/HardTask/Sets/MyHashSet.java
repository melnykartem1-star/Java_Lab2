package HardTask.Sets;

import HardTask.Maps.MyHashMap;

public class MyHashSet {

    MyHashMap map;
    static final Object PRESENT = new Object();

    public MyHashSet() {
        map = new MyHashMap();
    }

    public MyHashSet(int initialCapacity, float loadFactor) {
        map = new MyHashMap(initialCapacity, loadFactor);
    }

    public boolean add(Object e) {
        return map.put(e, PRESENT) == null;
    }

    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public int size() {
        return map.size();
    }

}
