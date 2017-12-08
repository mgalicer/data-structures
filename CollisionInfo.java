package project5;
/**
 * 
 * @author marigalicer
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CollisionInfo {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.print("Please pass a data file (and only a data file).");
			System.exit(1);
		}
		
	    //verify that the input file exists and can be read
	    File inputFile = new File (args[0]);
	    
	    if (!(inputFile.exists() && inputFile.canRead())) {
	      System.err.printf("Error: Cannot read file %s.%n", args[0]);
	      System.exit (1);
	    }
	    
	    CollisionsData accidentData = new CollisionsData();
	    readInCSVInput(inputFile, accidentData);
	    
	    String zip = "";
	    Date startDate;
	    Date endDate;
	    Scanner userInput = new Scanner(System.in);
	    while(true) {
	    	do {
		    	System.out.println("Enter a zip code (’quit’ to exit): ");
		    	zip = userInput.nextLine();
		    } while(!validateZip(zip));

		    System.out.println("Enter a start date (MM/DD/YYYY): ");
		    String start = userInput.nextLine();
		    System.out.println("Enter an end date (MM/DD/YYYY): ");
		    String end = userInput.nextLine();
		    
		    try {
		    	startDate = new Date(start);
		    	endDate = new Date(end);
		    	validateDates(startDate, endDate);
		    	System.out.print(accidentData.getReport(zip, startDate, endDate));
		    	
		    } catch (IllegalArgumentException x) {
		    	System.out.println("Invalid date format. Try again.");	
		    }
	    }
	    
	}	

	/**
	 * Validates the zipcode entered by the user.
	 * @param userZip
	 * @return
	 */
	public static boolean validateZip(String userZip) {
		
		if(userZip.equalsIgnoreCase("quit")) System.exit(0);
		if(userZip.length() != 5) {
			System.out.println("Invalid zip code. Try again.");
			return false;
		}
		for(int i=0; i < userZip.length(); i++) {
			if(!Character.isDigit(userZip.charAt(i))) {
				System.out.println("Invalid zip code. Try again.");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Makes sure user enters a valid date range.
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws IllegalArgumentException
	 */
	public static void validateDates(Date startDate, Date endDate) throws IllegalArgumentException {
		if(startDate.compareTo(endDate) >= 0) throw new IllegalArgumentException("Start date must be before end date!");
	}
	
	
	public static void readInCSVInput(File userFile, CollisionsData accidentData) {
	    Scanner in = null;
	   
	    
	    try {
	      in = new Scanner (userFile);
	      // in.useDelimiter(",");
		  if(in.hasNext()) {
			 // skip first line of the CSV			  
		    in.nextLine();
		  }
	      while(in.hasNext()){
	    	  ArrayList<String> arr = splitCSVLine(in.nextLine());
	    	  
	    	  try {
	    		  Collision collision = new Collision(arr);
	    		  accidentData.add(collision);
	    		  
	    	  } catch(IllegalArgumentException e) {
	    		  // do nothing, input is invalid so skip this CSV line	    		  
	    	  }
	    	  
	      }
	      System.out.println(accidentData.toStringTreeFormat());
	      in.close();
	    } catch (FileNotFoundException e) {
	      System.err.print("Error: Cannot read file.");
	      System.exit (1);
	    }
	}
	
	
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true; 
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
			
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
	
		return entries;
	}
}

