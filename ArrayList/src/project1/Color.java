package project1;

/**
  * This class represents color objects and their various representations.
  *
  * @author Mari Galicer
  * @version 09/18/2017
**/

public class Color implements Comparable<Color>{
  // the Color class stores info about a color.

  private String hexVal;
  private String name = null;
  private int red;

  private int green;
  private int blue;

  public Color(String colorHexValue) {
    if(validateHex(colorHexValue)) {
      this.hexVal = colorHexValue;
      this.convertToRGB();
    } else {
      throw new IllegalArgumentException("Please enter a hex value in the correct format.");
    }
  }

  public Color(String colorHexValue, String colorName) {
    if(validateHex(colorHexValue)) {
      this.hexVal = colorHexValue;
      this.convertToRGB();
    } else {
      throw new IllegalArgumentException("Please enter a hex value in the correct format.");
    }
    name = colorName;
  }

  public Color(int red, int green, int blue) {
    if((red < 0 | red > 255) | (green < 0 | green > 255) | (blue < 0 | blue > 255)) {
      throw new IllegalArgumentException("Please enter a valid color value between 0 and 255.");
    } else {
      this.red = red;
      this.green = green;
      this.blue = blue;
      this.convertToHex();
    }
  }

  /**
    * Returns the red RGB value for the Color object.
    *
    * @return int red value.
  **/

  int getRed() {
    // returns the value of the red component
    return red;
  }

  /**
    * Returns the green RGB value for the Color object.
    *
    * @return int green value.
  **/

  int getGreen() {
    // returns the value of the green component
    return green;
  }

  /**
    * Returns the blue RGB value for the Color object.
    *
    * @return int blue value.
  **/

  int getBlue() {
    // returns the value of the blue component
    return blue;
  }

  /**
    * Returns the name of the Color object.
    *
    * @return String name
  **/

  String getName() {
    // returns the name of the color, or null if there is no name associated with this Color object,
    return this.name;
  }

  /**
    * Returns the hex value of the Color object.
    *
    * @return String hexValue
  **/

  String getHexValue() {
    // returns the hexadecimal representation of this Color object in the format #XXXXXX (see above).
    return this.hexVal;
  }

  /**
    * Checks if string passed in is a valid hex string.
    *
    * @param colorHexValue string to check validity for.
    * @return true if the value passed in is a valid hex string, false if not.
  **/

  Boolean validateHex(String colorHexValue) {
    if(colorHexValue.length() == 7) {
      if(colorHexValue.substring(1,7).matches("^[0-9A-Fa-f]+$")) {
        return true;
      }
    }
    return false;
  }

  /**
    * takes the hex values and assigns equivalent RGB values to the object, appropriately padded.
  **/

  private void convertToRGB() {
      this.red = Integer.valueOf(this.hexVal.substring( 1, 3 ), 16 );
      this.green = Integer.valueOf(this.hexVal.substring( 3, 5 ), 16 );
      this.blue = Integer.valueOf(this.hexVal.substring( 5, 7 ), 16 );
  }

  /**
    * takes the RGB values and assigns equivalent hex values to the object, appropriately padded.
  **/

  private void convertToHex() {
    // pad with zeros
    String zeroPad = "00";
    String hex1 = Integer.toHexString(this.red).toUpperCase();
    String hex1p = zeroPad.substring(hex1.length()) + hex1;
    String hex2 = Integer.toHexString(this.green).toUpperCase();
    String hex2p = zeroPad.substring(hex2.length()) + hex2;
    String hex3 = Integer.toHexString(this.blue).toUpperCase();
    String hex3p = zeroPad.substring(hex3.length()) + hex3;
    this.hexVal = "#" + hex1p + hex2p + hex3p;
    System.out.println(this.hexVal);
  }

  /**
    * compares the hex values of two Color objects.
    *
    * @param colorObject the object you want to compare with.
    * @return either true or false if the objects' hex values are the same.
  **/

  Boolean equals(Color colorObject) {
      if(this.getHexValue().equalsIgnoreCase(colorObject.getHexValue())){
        return true;
      }
      return false;
  }

  /**
    * Returns a printable version of the object.
    *
    * @return String representing the color information about an object.
  **/

  public String toString() {
    // pad with spaces
    String redString = String.format("%3s", this.getRed());
    String greenString = String.format("%3s", this.getGreen());
    String blueString = String.format("%3s", this.getBlue());

    String output = this.getHexValue() + ", (" + redString + "," + greenString + "," + blueString + ")";
    if(this.name != null) {
      output = output + ", " + this.getName();
    }

    return output;
  }

  /**
    * Does a comparison of hex values of two Color objects.
    *
    * @param colorVal the Color object to compare to.
    * @return a negative integer, zero, or a positive integer as the specified String is greater than, equal to, or less than this String, ignoring case considerations
  **/

  public int compareTo(Color colorVal) {
    String thisHex = this.getHexValue().substring(1,7).toLowerCase();
    return thisHex.compareToIgnoreCase(colorVal.getHexValue().substring(1,7).toLowerCase());
  }

}
