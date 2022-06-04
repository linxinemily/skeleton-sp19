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
        Node dummyNode = new Node(null, null);
        root = remove(key, root, dummyNode);
        if (dummyNode.key != null) size--;
        return dummyNode.val;
    }

    private Node remove(K key, Node node, Node deletedNode) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right, deletedNode);
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left, deletedNode);
        } else if (key.compareTo(node.key) == 0) { // found the node
            deletedNode.key = node.key;
            deletedNode.val = node.val;
            // case1: has no children, just delete the node
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left != null && node.right == null) { // case2: has one child - left
                return node.left;
            } else if (node.left == null && node.right != null) { // case2: has one child - right
                return node.right;
            } else if (node.left != null && node.right != null) { // case2: has two children
                Node max = getMax(node.left);
                K temp_key = node.key;
                V temp_val = node.val;
                node.key = max.key;
                node.val = max.val;
                max.key = temp_key;
                max.val = temp_val;
                node.left = remove(key, node.left, deletedNode);
            }
        }
        return node;
    }

    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
    }
}
