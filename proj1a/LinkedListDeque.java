public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        public T value;
        public Node next;
        public Node prev;

        public Node(T _value, Node nextNode, Node prevNode) {
            value = _value;
            next = nextNode;
            prev = prevNode;
        }
    }

    private int size;
    private final Node dummy;

    public LinkedListDeque() {
        dummy = new Node(null, null, null);
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        dummy = new Node(null, null, null);
        Node other_head = other.dummy.next;
        Node current = other_head;

        if (current == null) {
            size = 0;
            return;
        }

        do {
            addLast(current.value);
            current = current.next;
        } while (current.prev != other_head.prev);
    }

    @Override
    public void addFirst(T value) {
        Node head = dummy.next;
        Node item = new Node(value, null, null);
        if (head == null) { // if the list is empty
            item.next = item;
            item.prev = item;
        } else {
            Node last = dummy.next.prev;
            item.next = head;
            item.prev = last;
            last.next = item;
            head.prev = item;
        }
        dummy.next = item;
        size += 1;
    }

    @Override
    public void addLast(T value) {
        Node head = dummy.next;
        Node item = new Node(value, null, null);
        if (head == null) {
            dummy.next = item;
            item.next = item;
            item.prev = item;
        } else {
            Node last = dummy.next.prev;
            item.prev = last;
            last.next = item;
            item.next = head;
            head.prev = item;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node head = dummy.next;
        Node current = head; // initial current is head

        if (current == null) {
            return;
        }

        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current.prev != head.prev);

        System.out.println();
    }

    @Override
    public T removeFirst() {
        Node head = dummy.next;
        if (head == null) {
            return null;
        }
        T item = head.value;
        if (size == 1) {
            dummy.next = null;
        } else {
            head.next.prev = head.prev;
            head.prev.next = head.next;
            dummy.next = head.next;
        }
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (dummy.next == null || dummy.next.prev == null) {
            return null;
        }
        Node last = dummy.next.prev;
        T item = last.value;
        if (size == 1) {
            dummy.next = null;
        } else {
            last.prev.next = last.next;
            dummy.next.prev = last.prev; // prev of head become new last
        }
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        int count = 0;
        Node current = dummy.next;

        if (index >= size) {
            throw new IndexOutOfBoundsException("index dose not exists.");
        }

        while (index > count) {
            current = current.next;
            count += 1;
        }

        return current.value;
    }

    public T getRecursive(int index) {
        return getRecursive(index, 0, dummy.next);
    }

    private T getRecursive(int index, int count, Node current) {
        if (index == count) {
            return current.value;
        }
        return getRecursive(index, count + 1, current.next);
    }
}
