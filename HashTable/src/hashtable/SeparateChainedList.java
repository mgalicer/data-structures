package hashtable;

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

    public V delete(K key) {
        // first check if key is null or list is empty
        if(key == null) throw new IllegalArgumentException("Delete method does not accept null values");
        if(size == 0) return null;

        // if list has only one node to be removed, remove it
        if(key.equals(head.key)) {
            V val = head.value;
            head = null;
            size--;
            return val;
        }

        Node current = head;
        Node next = current.next;

        while(next != null && !(next.key.equals(key))) {
            current = next;
            next = next.next;
        }

        if(next != null) {
            current.next = next.next;
            size--;
            return next.value;
        }
        return null;
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

    public V put(K key, V val) {
        if(val == null || key == null) throw new IllegalArgumentException("Put method does not accept null values");

        Node current = head;

        while(current != null) {
            if((current.key).equals(key)) {
                V oldVal = current.value;
                current.value = val;
                return oldVal;
            }
            current = current.next;
        }

        head = new Node(key, val, head);
        size++;
        return null;
    }

    public int size() { return size; }

    public String toString() {
        if(head == null) return "";
        Node current = head;

        StringBuilder listAsString = new StringBuilder();

        while(current != null) {
            listAsString.append(current.key + "=" + current.value + ", ");
            current = current.next;
        }

        return listAsString.toString();
    }

    public static void main(String[] args) {

    }

}