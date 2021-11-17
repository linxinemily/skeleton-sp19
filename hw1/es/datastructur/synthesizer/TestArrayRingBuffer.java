package es.datastructur.synthesizer;

public class TestArrayRingBuffer {
    public static void main(String[] args) {
        BoundedQueue<Double> x = new ArrayRingBuffer<>(4);
        x.enqueue(33.1); // 33.1 null null  null
        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(62.3); // 33.1 44.8 62.3  null
        x.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        x.dequeue();     // 44.8 62.3 -3.4  null (returns 33.1)
    }
}
