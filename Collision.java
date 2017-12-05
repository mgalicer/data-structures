package project5;

import java.util.ArrayList;

/**
 * represents a single collision (single valid row from the input file)
 *  single parameter to this constructor should be an ArrayList<String> 
 * @author marigalicer
 *
 */

//[03/13/2017, 21:00, BROOKLYN, 11223, 40.59985, -73.9613, (40.59985, -73.9613), , , 2337 CONEY
//ISLAND AVENUE , 0, 0, 0, 0, 0, 0, 0, 0, Driver Inattention/Distraction, Unspecified, , , ,
//3631843, SPORT UTILITY / STATION WAGON, PASSENGER VEHICLE, , ]

public class Collision implements Comparable<Collision>{

	String zip;
	Date date;
	String key;
	int personsInjured, personsKilled, pedestriansInjured, pedestriansKilled, cyclistsInjured, cyclistsKilled, motoristsInjured, motoristsKilled;

	int[] stats = { personsInjured, personsKilled, pedestriansInjured, pedestriansKilled, cyclistsInjured, cyclistsKilled, motoristsInjured, motoristsKilled };
	
	/**
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
		
		date = new Date(entries.get(0));
		// validate zip code		
		if(validateZip(entries.get(3))) zip = entries.get(3);
		// validate num of people involved		
		for(int i=10; i <= 17; i++) {
			if(validateNum(entries.get(i))) {
				stats[i-10] = Integer.parseInt("hello");
			}
		}
		// validate key
		if(validateKey(entries.get(23))) key = entries.get(23); 
		

	}
	
	
	/**
	 * Validates the zipcode entered by the user.
	 * @param userZip
	 * @return
	 */
	public static boolean validateZip(String userZip) throws IllegalArgumentException {
		if(userZip.length() != 5) throw new IllegalArgumentException("Must be valid zip code.");
		for(int i=0; i < userZip.length(); i++) {
			if(!Character.isDigit(userZip.charAt(i))) throw new IllegalArgumentException("Must be valid zip code.");
		}
		return true;
	}
	
	/**
	 * 
	 */
	public static boolean validateNum(String entry) throws IllegalArgumentException {
		if(Integer.parseInt(entry) < 0) throw new IllegalArgumentException("Number of people must be greater than or equal to zero."); 
		return true;
	}
	
	/**
	 * @param key
	 */
	public static boolean validateKey(String key) {
//		if(Intege)
		return true;
	}
	
	/**
	 * @param o 
	 */
	@Override
	public int compareTo(Collision o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	
//	getZip(), getDate(), getKey(), getPersonsInjured(), getPedestriansInjured(), getCyclistInjured(),
//	getMotoristsInjured(), getPersonsKilled(), getPedestriansKilled(), getCyclistKilled(), getMotoristsKilled
//	().

}
