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

  private int hash(K key) {
    // get the hashcode from the variable's type
    int hc = key.hashCode() & 0x7fffffff;
    // convert that hashcode to fit into the hashtable's buckets
    return hc % buckets;
  }

}