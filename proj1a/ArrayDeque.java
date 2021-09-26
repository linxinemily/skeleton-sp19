public class ArrayDeque<T> {
    private T[] list;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        int initSize = 8;
        list = (T[]) new Object[initSize];
        size = 0;
        nextFirst = initSize / 2 - 1;
        nextLast = initSize / 2;
    }

    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        T[] new_list =  (T[]) new Object[size + 2];
        nextFirst = 0;
        nextLast = size + 1;
        for (int i = 0; i < size; i ++) {
            new_list[i+1] = (T) other.get(i);
        }
        list = new_list;
    }

    public void addLast(T value) {
        list[nextLast] = value;
        //  when next last index equals to the last index of array, make circular
        nextLast = nextLast == list.length - 1 ? 0 : nextLast + 1;
        size += 1;
        // check if array is full or not
        if (nextLast == nextFirst) {
            resize(size * 2);
        }
    }

    public void addFirst(T value) {
        list[nextFirst] = value;
        nextFirst = nextFirst == 0 ? list.length - 1 : nextFirst - 1;
        size += 1;
        if (nextLast == nextFirst) {
            resize(size * 2);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = nextFirst >= list.length - 1 ? 0 : nextFirst + 1;
        int count = 0;
        while (size > count) {
            System.out.print(list[i] + " ");
            i = i == list.length - 1 ? 0 : i + 1;
            count++;
        }
        System.out.println();
    }

    public T removeFirst() {
        int index = nextFirst >= list.length - 1 ? 0 : nextFirst + 1;
        T value = list[index];
        if (value == null) {
            return null;
        }
        list[index] = null;
        nextFirst = index;
        size -= 1;
        checkSpace();
        return value;
    }

    public T removeLast() {
        int index = nextLast == 0 ? list.length - 1 : nextLast - 1;
        T value = list[index];
        if (value == null) {
            return null;
        }
        list[index] = null;
        nextLast = index;
        size -= 1;
        checkSpace();
        return value;
    }

    public T get(int index) {
        int real_index = index + nextFirst + 1;
        if (real_index >= list.length) {
            real_index -= list.length;
        }
        return list[real_index];
    }

    private void resize(int list_size) {
        T[] newList = (T[]) new Object[list_size];
        int i = 1;
        int j = nextFirst >= list.length - 1 ? 0 : nextFirst + 1;
        while (size >= i) {
            newList[i] = list[j];
            j = j == list.length - 1 ? 0 : j + 1;
            i++;
        }
        list = newList;
        nextFirst = 0;
        nextLast = i;
    }

    private void checkSpace() {
        // For arrays of length 16 or more, usage factor should always be at least 25%
        if (list.length < 16) {
            return;
        }
        while (size / list.length < 0.25) {
            resize(size / 2);
        }
    }
}
