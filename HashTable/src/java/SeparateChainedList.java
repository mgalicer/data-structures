public class SeparateChainedList<K, V> {
    private int size;
    private Node head;

    private class Node {
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SeparateChainedList() {}

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void delete(K key) {
        if(key == null) throw new IllegalArgumentException("Delete method does not accept null values");
        if(head.key.equals(key)) {
            head = head.next;
            return;
        }

        Node current = head;
        while(current != null) {
            if(current.)
        }
    }

    public V get(K key) {

        Node current = head;

        while(current != null) {
            if((current.key).equals(key)) return current.value;
            current = current.next;
        }

        return null;
    }

    public boolean isEmpty() { return size == 0; }

    public V put(K key, V value) {

    }

    public int size() { return size; }

    public String toString() {

    }

}