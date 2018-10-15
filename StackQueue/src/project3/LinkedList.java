package project3;

/**
 * Represents a Linked List class used by the Stack class.
 * 
 * @author Mari Galicer
 * based on Goodrich, Data Structures and
Algorithms in Java™ Sixth Edition
 */

public class LinkedList<E> {
	private class Node<E> {
		protected E element;
		protected Node<E> next;
		
		protected Node(E e, Node<E> next) {
			element = e;
			next = next;
		}
		
		protected Node(E e) {
			element = e;
			next = null;
		}
		
		public E getElement( ) { return element; } 
		public Node<E> getNext( ) { return next; } 
		public void setNext(Node<E> n) { next = n; }
	}
	
	private Node<E> head = null;
	private int size = 0;
	
	public LinkedList() {};
	
	/**
	 * Add an object to the list.
	 * @param e
	 *    object to be added
	 */
	public void add(E e) {
		if(e == null) return;
		if(head == null) {
			head = new Node<>(e, null);
		} else {
			Node<E> newElement = new Node<>(e);
			newElement.setNext(head);
			head = newElement;
		}
		size++;

	}
	
	/**
	 * Determines if list is empty or not.
	 * @return
	 *    true, if set is empty,
	 *    false, otherwise.
	 */
	public boolean isEmpty() {
		if(head == null) return true;
		return false;
	}
	
	/**
	 * Remove the next object from the front of the list.
	 * @return
	 *    the next object, or null if the list is empty
	 */
	public E remove() {
		if(head == null) return null;
		if(head.getNext() == null) {
			Node<E> el = head;
			head = null;
			size--;
			return el.getElement();
		}
		Node<E> el = head;
		head = head.getNext();
		return el.getElement();
		
	}
	
	/* 
	 * @return the front of the list
	 */
	public E peek() {
		if(head == null) return null;
		return head.getElement();
	}
	
	/*
	 * @return size of the list
	 */
	public int getSize() {
		return size;
	}

	
}
