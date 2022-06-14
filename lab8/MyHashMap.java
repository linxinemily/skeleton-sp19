import edu.princeton.cs.algs4.SequentialSearchST;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MyHashMap<K, V> implements Map61B<K, V> {
    private double loadFactor;
    private int size;
    private SequentialSearchST<K, V>[] buckets;
    private static int DEFAULT_INITIAL_SIZE = 16;
    private HashSet<K> hashSet;

    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.loadFactor = loadFactor;
        buckets = (SequentialSearchST<K, V>[]) new SequentialSearchST[initialSize];
        for (int i = 0; i < initialSize; i++) {
            buckets[i] = new SequentialSearchST<>();
        }

        hashSet = new HashSet<>();
        this.size = 0;
    }

    /** Removes all the mappings from this map. */
    public void clear() {
        buckets = (SequentialSearchST<K, V>[]) new SequentialSearchST[DEFAULT_INITIAL_SIZE];
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int hashedKey = key.hashCode() % buckets.length;
        return buckets[hashedKey] != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int hashedKey = key.hashCode() % buckets.length;
        return buckets[hashedKey].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        int index = key.hashCode() % buckets.length;
        if (!buckets[index].contains(key)) size++;
        buckets[index].put(key, value);
        hashSet.add(key);

        // check load factor & resizing
        double actualLoadFactor = (double) size / buckets.length;
        if (actualLoadFactor >= loadFactor) {
            resize();
        }
    }

    private void resize() {
        // double the bucket
        SequentialSearchST<K, V>[] newBuckets = new SequentialSearchST[buckets.length * 2];

        for (int i = 0; i < buckets.length * 2; i++) {
            newBuckets[i] = new SequentialSearchST<>();
        }

        // add items into new bucket
        for (SequentialSearchST<K, V> bucket : buckets) {
            for (K key : bucket.keys()) {
                newBuckets[key.hashCode() % newBuckets.length].put(key, bucket.get(key));
            }
        }

        // replace old buckets with new buckets
        buckets = newBuckets;
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return hashSet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> mhm = new MyHashMap<>(4);

        mhm.put(1, "test");
        mhm.put(2, "test");
        mhm.put(2, "test2");
        mhm.put(2, "test_2");
        mhm.put(6, "test6");
//        System.out.println(mhm.get(3));
        for (Integer key : mhm) {
            System.out.println(mhm.get(key));
        }
    }
}
