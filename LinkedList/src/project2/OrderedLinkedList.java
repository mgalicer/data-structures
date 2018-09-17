package project2;
@SuppressWarnings("unchecked")
/**
  * This class represents a singly linked list implementation of OrderedList<E> interface.
  *
  * @param <E> the type of elements in this list
  * @author Mari Galicer
  * @version 10/08/2017
**/

public class OrderedLinkedList<E extends Comparable<E>> implements OrderedList<E> {
  /**
    * This class represents a Node to be used internally within the Ordered Linked List class.
    *
    * @param <E> the type of elements in each node
  **/

  public static void main(String[] args) {
    OrderedLinkedList<Integer> list = new OrderedLinkedList<Integer>();
    list.add(2);
    list.add(1);
    list.add(4);
    list.add(7);
    list.add(5);
    list.add(8);
    System.out.println(list.toString());
  }

  protected static class Node<E extends Comparable<E>> {
    private E element;
    private Node<E> next;

    protected Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    protected Node(E e) {
      element = e;
      next = null;
    }

    public E getElement() { return element; }

    public Node<E> getNext() { return next; }

    public void setNext(Node<E> n) { next = n; }
  }

  private Node<E> head = null;

  private int size = 0;

  /**
    * Constructor that initializes an empty list.
  **/
  public OrderedLinkedList() {}

  /**
    * A public accessor for a list's head.
  **/
  public Node<E> getHead() { return head; }

  /**
     * Adds the specified element to  this list in a sorted order.
     *
     * <p>The element added must implement Comparable<E> interface. This list
     * does not permit null elements.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws ClassCastException if the class of the specified element
     *         does not implement Comparable<E>
     * @throws NullPointerException if the specified element is null
     */
  public boolean add(E e) {
    if(e == null ) throw new NullPointerException("Cannot add null to the linked list.");
    if(!(e instanceof Comparable)) throw new ClassCastException("Element must implement Comparable interface.");

    // initialize current to the head of the list and create new node
    Node<E> current = head;
    Node<E> newNode = new Node<>(e);

    //if there aren't any elements, set the new node as the head
    if(current == null) {
      head = newNode;
      size++;
      return true;
    }

    // check if element is new head
    if(current.getElement().compareTo(e) > 0) {
      newNode.setNext(current);
      head = newNode;
      size++;
      return true;
    }

    while(current != null) {
      // if new el < current
      if(current.getElement().compareTo(e) > 0) {
        newNode.setNext(current);
        size++;
        return true;
      }
      // if we've hit the last element in the list, add it to the end
      if(current.getNext() == null) {
        current.setNext(newNode);
        size++;
        return true;
      }
      current = current.getNext();
    }

    return true;
  }

  /**
   * Removes all of the elements from this list.
   * The list will be empty after this call returns.
   */
  public void clear() {
    head = null;
    size = 0;
  }

  /**
   * Returns a shallow copy of this list. (The elements
   * themselves are not cloned.)
   *
   * @return a shallow copy of this list instance
   * @throws CloneNotSupportedException - if the object's class does
   *         not support the Cloneable interface
   */
  public Object clone() throws CloneNotSupportedException {
    if(!(this instanceof Cloneable)) { throw new CloneNotSupportedException("Object must implement Cloneable interface in order to be cloned."); }

    @SuppressWarnings("unchecked")
    OrderedLinkedList<E> newList = (OrderedLinkedList<E>)super.clone();
    if(size > 0) {
      newList.head = new Node<>(head.getElement());
      Node<E> walk = head.getNext( );
      Node<E> otherTail = newList.head;
      while (walk != null) {
        Node<E> newest = new Node<>(walk.getElement( ), null);
        otherTail.setNext(newest); // link previous node to this one
        otherTail = newest;
        walk = walk.getNext( );
      }
    }

    return newList;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws ClassCastException if the type of the specified element
   *         is incompatible with this list
   * @throws NullPointerException if the specified element is null
   */
  public boolean contains(Object o) {
    if(o == null) { throw new NullPointerException("Object cannot be null."); }
    if(head == null) { return false; }
    // if(!(o instanceof head.getElement())){ throw new ClassCastException("Object passed in must be compatible with type in list."); }
    Node<E> current = head;
    for(int i=0; i < size; i++) {
      if(o.equals(current.getElement())) { return true; }
      current = current.getNext();
    }

    return false;
  }

  /**
   * Compares the specified object with this list for equality.  Returns
   * {@code true} if and only if the specified object is also a list, both
   * lists have the same size, and all corresponding pairs of elements in
   * the two lists are <i>equal</i>.  (Two elements {@code e1} and
   * {@code e2} are <i>equal</i> if {@code e1.equals(e2)}.)
   * In other words, two lists are defined to be
   * equal if they contain the same elements in the same order.<p>
   *
   * @param o the object to be compared for equality with this list
   * @return {@code true} if the specified object is equal to this list
   */

  public boolean equals(Object o) {
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    OrderedLinkedList<E> other = (OrderedLinkedList<E>) o; // use nonparameterized type
    if (size != other.size) return false;

    Node<E> walkA = head; // traverse the primary list
    Node<E> walkB = other.head; // traverse the secondary list
    while (walkA != null) {
      if (!walkA.getElement( ).equals(walkB.getElement( ))) return false; //mismatch
      walkA = walkA.getNext( );
      walkB = walkB.getNext( );
    }
    return true; // if we reach this, everything matched successfully
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range
   * <tt>(index < 0 || index >= size())</tt>
   */
  public E get(int index) {
    if(index < 0 || index >= size) { throw new IndexOutOfBoundsException("Index is out of bounds."); }

    Node<E> current = head;
    for(int i=0; i <= index; i++) {
      if(i == index) {
        return current.getElement();
      }
      current = current.getNext();
    }
    return null;
  }

  /**
   * Returns the index of the first occurrence of the specified element
   * in this list, or -1 if this list does not contain the element.
   *
   * @param o element to search for
   * @return the index of the first occurrence of the specified element in
   *         this list, or -1 if this list does not contain the element
   */
  public int indexOf (Object o) {
    if(head == null || o == null) { return -1; }
    Node<E> current = head;
    // iterate through list until equal comparator finds matching object.
    for(int i=0; i < size; i++) {
      if(o.equals(current.getElement())) { return i; }
      current = current.getNext();
    }

    return -1;
  }

  /**
   * Removes the element at the specified position in this list.  Shifts any
   * subsequent elements to the left (subtracts one from their indices if such exist).
   * Returns the element that was removed from the list.
   *
   * @param index the index of the element to be removed
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException  if the index is out of range
   * <tt>(index < 0 || index >= size())</tt>
   */

  public E remove (int index) {
    if(index < 0 || index >= size) { throw new IndexOutOfBoundsException("Index is out of bounds."); }
    Node<E> current = head;
    int i = 0;
    // if there is only one element in the list, return it
    if(index == 0) {
      if(current.getNext() == null) {
        head = null;
        return current.getElement();
      } else {
        head = current.getNext();
        return current.getElement();
      }
    }

    Node<E> removedElement = head;

    while(current.getNext() != null) {
      if(i + 1 == index) {
        // get the next next element in order to replace the current.next pointer
        if(current.getNext().getNext() != null) {
          // save the removed element to return later
          removedElement = current.getNext();
          // set the next element to skip over the removed element
          current.setNext(current.getNext().getNext());
        } else {
          removedElement = current.getNext();
          current.setNext(null);
        }
      }
      i++;
      current = current.getNext();
    }
    size--;
    return removedElement.getElement();

  }

  /**
   * Removes the first occurrence of the specified element from this list,
   * if it is present.  If this list does not contain the element, it is
   * unchanged.  More formally, removes the element with the lowest index
   * {@code i} such that
   * <tt>(o.equals(get(i))</tt>
   * (if such an element exists).  Returns {@code true} if this list
   * contained the specified element (or equivalently, if this list
   * changed as a result of the call).
   *
   * @param o element to be removed from this list, if present
   * @return {@code true} if this list contained the specified element
   * @throws ClassCastException if the type of the specified element
   *         is incompatible with this list
   * @throws NullPointerException if the specified element is null and this
   *         list does not permit null elements
   */
  public boolean remove (Object o) {
    if(o == null) { throw new NullPointerException("Argument cannot be null."); }
    if(head == null) { return false; }
    // if(!(o instanceof head.getElement().getClass())) { throw new ClassCastException("Argument must be instance of list class."); }
    Node<E> current = head;
    if(o.equals(head.getElement())) {
      head = null;
      size--;
      return true;
    }

    Node<E> removedElement = head;

    while(current.getNext() != null) {
      if(o.equals(current.getNext().getElement())) {
        // get the next next element in order to replace the current.next pointer
        if(current.getNext().getNext() != null) {
          current.setNext(current.getNext().getNext());
          size--;
          return true;
        } else {
          current.setNext(null);
          size--;
          return true;
        }
      }
      current = current.getNext();
    }
    return false;
  }

  /**
   * Returns the number of elements in this list.
   *
   * @return the number of elements in this list
   */
  public int size() { return size; }

  /**
   * Returns a string representation of this list.  The string
   * representation consists of a list of the list's elements in the
   * order they are stored in this list, enclosed in square brackets
   * (<tt>"[]"</tt>).  Adjacent elements are separated by the characters
   * <tt>", "</tt> (comma and space).  Elements are converted to strings
   * by the {@code toString()} method of those elements.
   *
   * @return a string representation of this list
   */
  public String toString() {
    if(head == null) { return "[]"; }

    Node<E> current = head;
    String listString = "[";

    while(current.getNext() != null) {
      listString += current.getElement().toString();
      listString += ", ";
      current = current.getNext();
    }

    listString += current.getElement().toString();

    listString += "]";
    return listString;
  }
}