package project1;

import java.util.Scanner;

/**
  * The ColorConverter class reads in a text file of CSS colors and read in user input to provide appropriate conversions of hex values.
  *
  * @author Mari Galicer
  * @version 09/18/2017
**/

public class ColorConverter {
  public static void main(String[] args) throws Exception {
    if(args.length == 0) {
      System.err.println("Usage Error: the program expects file name as an argument.");
      System.exit(1);
    }

    java.io.File file = new java.io.File(args[0]);

    // read in file from CLI
    if(!file.exists()){
      System.err.println("Error: the file " + args[0] + " cannot be opened.");
      System.exit(1);
    }

    // create colorlist
    ColorList funColors = new ColorList();

    // create scanner to read file
    Scanner userFile = new Scanner(file);

    while (userFile.hasNext()) {
      String line = userFile.nextLine();
      // split the input into the hex value and name
      String[] colorInfo = line.split("\\s*,\\s*");
      // trim whitespace and create new colors
      Color color = new Color(colorInfo[1].trim(), colorInfo[0].trim());
      funColors.add(color);
    }

    userFile.close();

    // create scanner to read file
    Scanner input = new Scanner(System.in);

    // set flag
    Boolean contInput = true;

    while(contInput == true) {
      System.out.println("Enter the color in HEX format (#RRGGBB) or \"quit\" to stop:");
      String userInput = input.next();
      if(userInput.toLowerCase().equals("quit")) {
        break;
      }
      // find color in colorlist by hex value
      Color userColor = funColors.getColorByHexValue(userInput);

      if(userColor != null) {
        System.out.println("Color Information:");
        System.out.println(userColor);
      } else {
        try {
          Color undefColor = new Color(userInput);
          System.out.println("Color Information:");
          System.out.println(undefColor);
          // catch the exception if user puts in a weird value
        } catch(IllegalArgumentException e) {
          System.out.println("Error: This is not a valid color specification.");
        }
      }
    }

  }
}