package HardTask.Sets;

import HardTask.Maps.MyHashMap;
import HardTask.Maps.MyLinkedHashMap;

public class MyLinkedHashSet extends MyHashSet {

    public MyLinkedHashSet() {
        this.map = new MyLinkedHashMap(MyHashMap.DEFAULT_INITIAL_CAPACITY, MyHashMap.DEFAULT_LOAD_FACTOR, false);
    }

}


