import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size = 0;
    private Node root;

    private class Node {
        K key;
        V val;
        Node left;
        Node right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return search(key, root) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        Node n = search(key, root);
        return n != null ? n.val : null;
    }

    private Node search(K key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) > 0) {
            return search(key, node.right);
        } else if (key.compareTo(node.key) < 0) {
            return search(key, node.left);
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            insert(key, value, root);
        }
        size++;
    }

    private Node insert(K key, V value, Node node) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = insert(key, value, node.right);
        } else if (key.compareTo(node.key) < 0) {
            node.left = insert(key, value, node.left);
        }
        return node;
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
