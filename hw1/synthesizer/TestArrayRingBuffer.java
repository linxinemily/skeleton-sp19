package synthesizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArrayRingBuffer {
    @Test
    public void testEquals()
    {
        AbstractBoundedQueue<Double> BQ_1 = new ArrayRingBuffer<>(4);
        BQ_1.enqueue(33.1); // 33.1 null null  null
        BQ_1.enqueue(44.8); // 33.1 44.8 null  null
        BQ_1.enqueue(62.3); // 33.1 44.8 62.3  null
        BQ_1.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        BQ_1.dequeue();

        AbstractBoundedQueue<Double> BQ_2 = new ArrayRingBuffer<>(4);
        BQ_2.enqueue(44.8); // 33.1 44.8 null  null
        BQ_2.enqueue(62.3); // 33.1 44.8 62.3  null
        BQ_2.enqueue(-3.4); // 33.1 44.8 62.3 -3.4

        assertEquals(BQ_1, BQ_2);
    }
}
