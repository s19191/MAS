package zad01;

public class MyMap<K, V> {
    K key;
    V value;

    public MyMap(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyMap{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}