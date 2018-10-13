public class HashTable<K,V> {
  int size();

  // nested map entry class
  protected static class Entry<K,V> implements Entry<K,V> {
    private K key;
    private V value;

    public Entry(K k, V v) {
      key = k;
      value = v;
    }
  }

  V get(K key);
  V put(K key, V value);
  V remove(K key);
}