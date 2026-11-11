package HardTask.Maps;

public class LRUCache extends MyLinkedHashMap{

    private final int maxCapacity;

    public LRUCache(int maxCapacity) {
        super(maxCapacity, 0.75f, true);
        this.maxCapacity = maxCapacity;
    }

    boolean removeEldestEntry(Entry first) {
        return size > maxCapacity;
    }

}
