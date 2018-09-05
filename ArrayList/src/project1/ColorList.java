package project1;

import java.util.ArrayList;

/**
  * This class represents an ArrayList built specifically to store and retrieve Color objects.
  *
  * @author Mari Galicer
  * @version 09/18/2017
**/

public class ColorList extends ArrayList<Color> {
  public ColorList() {
    super();
  }

  /**
    * Finds the color matching the color name passed in.
    *
    * @param colorName the desired color to find.
    * @return Color that has the same name as colorName, or null if none found.
    * @throws NullPointerException in the case that no match is found.
  **/

  public Color getColorByName(String colorName) {
    // initialize to null
    Color returnColor = null;

    // loop through list to find the element
    for(int i = 0; i < super.size(); i++) {
      Color currColor = super.get(i);
      try {
        // compare colors by case insensitive name
        if(currColor.getName().toUpperCase().equals(colorName.toUpperCase())) {
          returnColor = currColor;
        }
      } catch(NullPointerException e) {
        // continue to return null
      }
    }
    return returnColor;
  }

  /**
    * Finds the color matching the hex value passed in.
    *
    * @param colorHexValue the desired color to find.
    * @return Color that has the same hex value, or null if none found.
  **/

  public Color getColorByHexValue(String colorHexValue) {
    Color returnColor = null;
    for(int i = 0; i < super.size(); i++) {
      Color currColor = super.get(i);
      try {
        if(currColor.getHexValue().toUpperCase().equals(colorHexValue.toUpperCase())) {
          returnColor = currColor;
        }
      } catch(NullPointerException e) {
        // continue to return null
      }
    }
    return returnColor;
  }


}