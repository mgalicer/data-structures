package project3;

/**
 * Represents a queue used for the 
 * search for the way out of a maze algorithm.
 * 
 * @author Mari Galicer
 * based on Goodrich, Data Structures and Algorithms in Java™ Sixth Edition
 */

class PossibleLocationsQueue<E> implements PossibleLocations{
	private E[] data;
	private int front;
	private int size;
	private int capacity = 30;
	
	public PossibleLocationsQueue() { data = (E[]) new Object[capacity]; }
	
	public PossibleLocationsQueue(int cap) {
		if(cap < 1 ) throw new IllegalArgumentException("Capacity of the queue must be at least 1");
		data = (E[]) new Object[cap];
	}
	
	/**
	 * Add a Location object to the queue.
	 * @param s
	 *    object to be added
	 */
	@Override
	public void add(project3.Location s) {
		if(s == null) return;
		if(size == capacity) makeLarger();
		int available = (front + size) % data.length;
		data[available] = (E) s;
		size++;
	}
	
	/**
	 * Remove the next object from the front of the queue.
	 * @return
	 *    the next object, or null if queue is empty
	 */
	@Override
	public project3.Location remove() {
		if(isEmpty()) return null;
		E answer = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		size--;
		return (Location) answer;
	}
	
	/**
	 * Determines if queue is empty or not.
	 * @return
	 *    true, if queue is empty,
	 *    false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/*
	 * makes the underlying array 1.5x bigger when size of the data reaches its capacity.
	 */
	public void makeLarger() {
		E[] temp = (E[]) new Object[(int) (capacity*1.5)];
		for(int i=0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}
	
}