package project3;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PossibleLocationsQueueTest {
	PossibleLocationsQueue<Location> queue;
	
	@Before
	public void initialize() {
		queue = new PossibleLocationsQueue<>();
	}
	
	/*
	 * test the constructor
	 */
	
	@Test
	public void testConstructorInput1() {
		testConstructor(-5);
	}
	
	@Test
	public void testConstructorInput2() {
		testConstructor(0);
	}
	
	@Test
	public void testConstructorInput3() {
		testConstructor(10);
	}
	
	/*
	 * Test that it implements interface
	 */
	public void testInterfaceImplementation() {
		if(!(queue instanceof PossibleLocations)) {
			fail("This class does not implement the PossibleLocations interface.");
		}
	}
	
	/*
	 * Test whether the queue is empty after various operations
	 */
	
	@Test
	public void testIsEmpty() {
		assertTrue("Queue should be empty when initialized", queue.isEmpty());
	}
	
	// Queue should not be empty after adding element
	@Test
	public void testIsEmptyAfterAdd() {
		Location [] locs = { new Location(10,10) };
		testAdd(locs);
		assertFalse("Stack should not be empty after adding element", queue.isEmpty());
	}
	
	//	When adding and removing the same amount of elements, queue should be empty
	@Test
	public void testAddRemoveIsEmpty1() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		testAdd(locs);
		testRemove(locs.length);
		assertTrue("Stack should be empty after removing the same amount of elements added", queue.isEmpty());
	}
	
	@Test
	public void testAddRemoveIsEmpty2() {
		Location [] locs = { new Location(1,1) };
		testAdd(locs);
		testRemove(locs.length);
		assertTrue("Stack should be empty after removing the same amount of elements added", queue.isEmpty());
	}
	
	/*
	 * Test that null inputs do not affect underlying data
	 */
	
	// Adding null to empty stack should leave the stack empty	
	@Test 
	public void testAddNullIsEmpty() {
		try {
			queue.add(null);
		} catch (Exception e) {
		}
		assertTrue("Stack should be empty after trying to insert null element", queue.isEmpty());
	}

	// Adding null should not modify the underlying elements	
	@Test
	public void testAddNulltoExistingList() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		int len = locs.length;
		testAdd(locs);
		try {
			queue.add(null);
		} catch( Exception e) { }
		assertEquals("List should be the same length after trying to add null", len, locs.length);
	}
	
	// remove()	should return null if stack is empty
	@Test 
	public void testRemoveEmptyList() {
		assertNull("Remove should return null if stack is empty", queue.remove());
	}
	
	
	/* 
	 * test that adding and removing together happen in the correct order and with the correct objects
	 */
	@Test
	public void firstInFirstOut1() {
		Location [] locs = { new Location(10,1), new Location(2,4), new Location(9,9), new Location(14,2) };
		testFirstInFirstOut(locs);
		
	}
	
	@Test
	public void firstInFirstOut2() {
		Location [] locs = { new Location(9,1) };
		testFirstInFirstOut(locs);
	}
	
	@Test
	public void lastInLastOut1() {
		Location [] locs = { new Location(5,5) };
		lastInLastOut(locs);
	}
	
	@Test
	public void lastInLastOut2() {
		Location [] locs = { new Location(10,1), new Location(2,4), new Location(9,9), new Location(14,2) };
		lastInLastOut(locs);
	}
	
	@Test
	public void testAddRemoveAllElements1() {
		Location [] locs = { };
		testAddRemoveAllElements(locs);
	}
	
	@Test
	public void testAddRemoveAllElements2() {
		Location [] locs = { new Location(2,1), new Location(2,4), new Location(3,7), new Location(4,2), new Location(8,8), new Location(1,8), new Location(7,7)};
		testAddRemoveAllElements(locs);
	}
	
	@Test
	public void testAddRemoveAllElements3() {
		Location [] locs = { new Location(5,5) };
		testAddRemoveAllElements(locs);
	}
	
	/*
	 * helper methods
	 */
	
	// make sure the constructor only takes valid arguments
	private void testConstructor(Integer input) {
		try {
			PossibleLocationsQueue<Location> queue = new PossibleLocationsQueue<>(input);
			assertNotNull("Queue should be created successfully", queue);
		} catch( IllegalArgumentException e ) {
			// correct behavior, this is what is expected to be thrown			
		} catch( Exception e ) {
			fail("constructor threw an unexpected type of exception.");
		}
	}
	
	// first in first out queue principle	
	private void testFirstInFirstOut(Location [] locations) {
		testAdd(locations);
		assertSame("First element added should be the first one removed from the queue", locations[0], queue.remove());
	}
	
	private void lastInLastOut(Location [] locations) {
		testAdd(locations);
		Location[] removed = testRemove(locations.length);
		assertSame("Last element added should be the last one removed from the queue", locations[locations.length - 1], removed[removed.length-1]);
	}
	
	//make sure elements are returned in the same order that they were added	
	private void testAddRemoveAllElements(Location [] locations) {
		testAdd(locations);
		Location[] removed = testRemove(locations.length);
		assertTrue("removed should be in the same order that they were added", Arrays.equals(locations, removed));
	}
	
	// helper method for adding items to the quee	
	private void testAdd(Location [] locations) {
		for(int i = 0; i < locations.length; i++) {
			try {
				queue.add(locations[i]);
			} catch (Exception e) {
				fail("Unexpected exception caught when adding elements from the stack " + Arrays.toString(locations) + ":\n" + e.toString());
			}
		}
		
	}
	
	// helper method for removing items from queue and returning them
	private Location [] testRemove(int numItems) {
		Location [] removedLocations = new Location[numItems];
		for(int i = 0; i < numItems; i++) {
			try {
				removedLocations[i] = queue.remove();
			} catch (Exception e) {
				fail("Unexpected exception caught when removing items from stack :\n" + e.toString());
			}
		}
		
		return removedLocations;
	}

}
