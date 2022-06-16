import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {
    private MyHashMap<Integer, String> buildHashMap() {
        MyHashMap<Integer, String> hashMap = new MyHashMap<>();
        // given
        hashMap.put(1, "test");
        hashMap.put(2, "test");
        hashMap.put(2, "test2");
        hashMap.put(2, "test_2");
        hashMap.put(6, "test6");
        hashMap.put(7, "test7");
        hashMap.put(19, "test19");
        return hashMap;
    }

    @Test
    public void test_can_remove_by_key() {
        // given
        MyHashMap<Integer, String> hashMap = buildHashMap();
        // when
        hashMap.remove(2);
        // then
        Assert.assertEquals(4, hashMap.size());
        Assert.assertFalse(hashMap.containsKey(2));
    }

    @Test
    public void test_can_move_by_key_and_value_success()
    {
        // given
        MyHashMap<Integer, String> hashMap = buildHashMap();
        // when
        hashMap.remove(2, "test_2");
        // then
        Assert.assertEquals(4, hashMap.size());
        Assert.assertFalse(hashMap.containsKey(2));
    }

    @Test
    public void test_can_move_by_key_and_value_failed()
    {
        // given
        MyHashMap<Integer, String> hashMap = buildHashMap();
        // when
        hashMap.remove(2, "test"); // the value is not matched
        // then
        Assert.assertEquals(5, hashMap.size());
        Assert.assertTrue(hashMap.containsKey(2));
    }
}
