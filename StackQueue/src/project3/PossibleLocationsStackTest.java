package project3;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PossibleLocationsStackTest {
	PossibleLocationsStack<Location> stack;
	
	// create a new stack for each test	
	@Before
	public void initialize() {
		stack = new PossibleLocationsStack<Location>();
	}
	
	/*
	 * Test that the class implements interface
	 */
	public void testInterfaceImplementation() {
		if(!(stack instanceof PossibleLocations)) {
			fail("This class does not implement the PossibleLocations interface.");
		}
	}
	
	// Stack should be initialized to be empty
	@Test
	public void testIsEmpty() {
		assertTrue("Stack should be empty when initialized", stack.isEmpty());
	}
	
	// stack should not be empty after adding element
	@Test
	public void testIsEmptyAfterAdd() {
		Location [] locs = { new Location(10,10) };
		testAdd(locs);
		assertFalse("Stack should not be empty after adding element", stack.isEmpty());
	}
	
	/*
	 * Test that null inputs do not affect underlying data
	 */
	
	// Adding null to empty stack should leave the stack empty	
	@Test 
	public void testAddNullIsEmpty() {
		try {
			stack.add(null);
		} catch (Exception e) {
		}
		assertTrue("Stack should be empty after trying to insert null element", stack.isEmpty());
	}

	// Adding null should not modify the underlying elements	
	@Test
	public void testAddNulltoExistingList() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		int len = locs.length;
		testAdd(locs);
		try {
			stack.add(null);
		} catch( Exception e) { }
		assertEquals("List should be the same length after trying to add null", len, locs.length);
	}
	
	
	// remove()	should return null if stack is empty
	@Test 
	public void testRemoveEmptyList() {
		assertNull("Remove should return null if stack is empty", stack.remove());
	}
	
	//	When adding and removing the same amount of elements, stack should be empty
	@Test
	public void testAddRemoveIsEmpty1() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		testAdd(locs);
		testRemove(locs.length);
		assertTrue("Stack should be empty after removing the same amount of elements added", stack.isEmpty());
	}
	
	@Test
	public void testAddRemoveIsEmpty2() {
		Location [] locs = { new Location(1,1) };
		testAdd(locs);
		testRemove(locs.length);
		assertTrue("Stack should be empty after removing the same amount of elements added", stack.isEmpty());
	}

	// Stack remove() should always return the last element that was added
	@Test
	public void testAddRemoveTop1() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		testAddRemoveTop(locs);
	}
	
	@Test
	public void testAddRemoveTop2() {
		Location [] locs = { new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4), new Location(1,1), new Location(2,2), new Location(3,3), new Location(4,4) };
		testAddRemoveTop(locs);
	}
	
	@Test
	public void testAddRemoveTop3() {
		Location [] locs = { new Location(1,1) };
		testAddRemoveTop(locs);
	}
	
	/* 
	 * test that adding and removing together happen in the correct order and with the correct objects
	 */
	
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
	
	//	Checks that remove() removes the last element added 
	private void testAddRemoveTop(Location [] locations) {
		testAdd(locations);
		assertSame("Top of stack should return the last element added", locations[locations.length-1], stack.remove());
	}
	
	// 	Elements removed should be in the opposite order that they were added
	private void testAddRemoveAllElements(Location [] locations) {
		testAdd(locations);
		Location[] removed = testRemove(locations.length);
		assertTrue("removed should be in the opposite order that they were added", Arrays.equals(locations, removed));
	}
	
	// helper method for adding items to stack
	private void testAdd(Location [] locations) {
		for(int i = 0; i < locations.length; i++) {
			try {
				stack.add(locations[i]);
			} catch (Exception e) {
				fail("Unexpected exception caught when adding elements from the stack " + Arrays.toString(locations) + ":\n" + e.toString());
			}
		}
		
	}
	
	// helper method for removing items from stack and returning them
	private Location [] testRemove(int numItems) {
		Location [] removedLocations = new Location[numItems];
		for(int i = numItems - 1; i >= 0; i--) {
			try {
				removedLocations[i] = stack.remove();
			} catch (Exception e) {
				fail("Unexpected exception caught when removing items from stack :\n" + e.toString());
			}
		}
		
		return removedLocations;
	}


}
