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
        // first check if key is null or list is empty
        if(key == null) throw new IllegalArgumentException("Delete method does not accept null values");
        if(size == 0) return;

        // if list has only one node to be removed, remove it
        if(head.key.equals(key)) {
            head = null;
            size--;
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

    public void put(K key, V val) {
        if(val == null) throw new IllegalArgumentException("Put method does not accept null values");

        Node current = head;

        while(current != null) {
            if((current.key).equals(key)) {
                current.value = val;
                return;
            }
            current = current.next;
        }

        head = new Node(key, val, head);

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

}