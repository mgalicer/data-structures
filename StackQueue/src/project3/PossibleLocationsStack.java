package project3;
/**
 * Represents a set of spaces used for the 
 * search for the way out of a maze algorithm.
 * 
 * @author Mari Galicer
 * based on Goodrich, Data Structures and
Algorithms in Java™ Sixth Edition
 */

class PossibleLocationsStack<Location> implements PossibleLocations{
	LinkedList stack = new LinkedList<Location>();
	
	/**
	 * Add a Location object to the stack.
	 * @param s
	 *    object to be added
	 */
	@Override
	public void add(project3.Location s) {
		stack.add(s);
	}

	/**
	 * Remove the next object from the top of the stack.
	 * @return
	 *    the next object, or null if stack is empty
	 */
	@Override
	public project3.Location remove() {
		return (project3.Location) stack.remove();
	}
	
	/**
	 * Determines if stack is empty or not.
	 * @return
	 *    true, if stack is empty,
	 *    false, otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty(); 
	}
	
	/* 
	 * @return the size of the list.
	 */
	public int getSize() {
		return stack.getSize();
	}
}