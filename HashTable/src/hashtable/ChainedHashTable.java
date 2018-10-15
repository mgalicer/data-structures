package hashtable;

public class ChainedHashTable<K,V> {
  private int size;
  private int buckets;
  private SeparateChainedList<K, V>[] table;

  public ChainedHashTable() {
    this(10);
  }

  public ChainedHashTable(int n) {
    this.buckets = n;
    table = (SeparateChainedList<K, V>[]) new SeparateChainedList[n];

    for(int i=0; i < n; i++)
        table[i] = new SeparateChainedList<K,V>();
  }


  public V get(K key) {
    if(key == null) throw new IllegalArgumentException("Cannot get null value.");

    SeparateChainedList list = table[hash(key)];
    return (V)list.get(key);
  }

  public int getSize() {
    return size;
  }

  private int hash(K key) {
    // get the hashcode from the variable's type
    int hc = key.hashCode() & 0x7fffffff;
    // convert that hashcode to fit into the hashtable's buckets
    return hc % buckets;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public V put(K key, V value) {
    if(key == null || value == null) throw new IllegalArgumentException("Cannot insert null value into hashtable.");

    SeparateChainedList list = table[hash(key)];
    size++;
    return (V)list.put(key, value);
  }

  public V remove(K key) {
    SeparateChainedList list = table[hash(key)];
    return (V)list.delete(key);
  }

  public String toString() {
     StringBuilder tableString = new StringBuilder();

     for(int i = 0; i < buckets; i++) {
      tableString.append(table[i].toString());
     }

     return tableString.toString();
  }

  public static void main(String[] args) {
    ChainedHashTable<String, Integer> cht = new ChainedHashTable<String, Integer>();
    cht.put("hello", 2);
    System.out.println(cht);
    cht.remove("hello");
    System.out.println(cht);

  }

}