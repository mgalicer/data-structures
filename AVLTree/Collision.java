package project5;

import java.util.ArrayList;

/**
 * represents a single collision (single valid row from the input file)
 * single parameter to this constructor should be an ArrayList<String> 
 * implements comparable interface 
 * @author marigalicer
 *
 */


public class Collision implements Comparable<Collision>{

	String zip;
	Date date;
	String key;
	int personsInjured, personsKilled, pedestriansInjured, pedestriansKilled, cyclistsInjured, cyclistsKilled, motoristsInjured, motoristsKilled;


	/**
	 * Constructor that validates input.
	 * the date cannot be empty and has to represent a valid Date object (see below)
	 * the zip code has to be a five character string with digits as all of its characters
	 * the number of persons/pedestrians/cyclists/motorists injured/killed has to be a non-negative integer
	 * the unique key has to be a non-empty string
	 * 
	 * @param entries
	 * @throws IllegalArgumentException
	 */

	public Collision (ArrayList<String> entries) throws IllegalArgumentException {
		// make sure entries obj isn't null and has at least 24 elements		
		if(entries == null | entries.size() < 24) throw new IllegalArgumentException("Must pass in a valid ArrayList Object.");
		// create date		
		date = new Date(entries.get(0));
		// validate zip code		
		if(validateZip(entries.get(3))) zip = entries.get(3);
		
		// validate num of people involved		
		personsInjured = validateNum(entries.get(10));
		personsKilled = validateNum(entries.get(11));
		pedestriansInjured = validateNum(entries.get(12));
		pedestriansKilled = validateNum(entries.get(13));
		cyclistsInjured = validateNum(entries.get(14));
		cyclistsKilled = validateNum(entries.get(15));
		motoristsInjured = validateNum(entries.get(16));
		motoristsKilled = validateNum(entries.get(17));
		
		// validate key
		if(validateKey(entries.get(23))) key = entries.get(23); 
		
	}
	
	
	/**
	 * Validates the zipcode entered by the user.
	 * @param userZip
	 * @return boolean if it works
	 */
	public static boolean validateZip(String userZip) throws IllegalArgumentException {
		if(userZip.length() != 5) throw new IllegalArgumentException("Must be valid zip code.");
		for(int i=0; i < userZip.length(); i++) {
			if(!Character.isDigit(userZip.charAt(i))) throw new IllegalArgumentException("Must be valid zip code.");
		}
		return true;
	}
	
	/**
	 * @param entry, part of the Array passed in to the constructor
	 */
	public static int validateNum(String entry) throws IllegalArgumentException {
		if(Integer.parseInt(entry) < 0) throw new IllegalArgumentException("Number of people must be greater than or equal to zero."); 
		return Integer.parseInt(entry);
	}
	
	/**
	 * Validates key.
	 * @param key
	 */
	public static boolean validateKey(String key) throws IllegalArgumentException {
		if(key.length() == 0) throw new IllegalArgumentException("Invalid key, cannot be empty");
		return true;
	}
	
	/**
	 * if two zip codes are different, then the comparison is based on the
	 * zip codes alone. If they are the same, then the comparison is based on the dates. If the date are the same, then the comparison is based
	 * on the unique keys
	 * @param o Collision object
	 */
	@Override
	public int compareTo(Collision o) {
		// QUESTION: comparison based off of string comparison or numeric comparison?		
		int zipDiff = this.getZip().compareTo(o.getZip());
		if(zipDiff != 0) return zipDiff;
		int dateDiff = this.getDate().compareTo(o.getDate());
		if(dateDiff != 0) return dateDiff;
		return this.getKey().compareTo(o.getKey());
	}
	
	/**
	 * Checks for equality of objects.
	 * @param o, object to compare
	 */
	@Override
	public boolean equals(Object o) {
		if(this.compareTo((Collision) o) == 0) return true; 
		return false;
	}
	
	//getter methods	
	public String getZip() { return zip; }
	
	public Date getDate() { return date; }
	
	public String getKey() { return key; }
	
	public int getPersonsInjured() { return personsInjured; }
	
	public int getPedestriansInjured() { return pedestriansInjured; }
	
	public int getCyclistsInjured() { return cyclistsInjured; }
	
	public int getMotoristsInjured() { return motoristsInjured; }
	
	public int getPersonsKilled() { return personsKilled; };
	
	public int getPedestriansKilled() { return pedestriansKilled; }
	
	public int getCyclistKilled() { return cyclistsKilled; }
	
	public int getMotoristsKilled() { return motoristsKilled; }

}
