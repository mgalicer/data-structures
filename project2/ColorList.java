package project2;

import java.util.ArrayList;

/**
  * This class represents an ColorList built specifically to store and retrieve Color objects.
  *
  * @author Mari Galicer
  * @version 10/08/2017
**/

public class ColorList extends OrderedLinkedList<Color> {
  /**
    * Constructor that initializes an empty list.
  **/
  public ColorList() {
    super();
  }

  public static void main(String[] args) {

  }

  /**
    * Finds the color matching the color name passed in.
    *
    * @param colorName the desired color to find.
    * @return Color that has the same name as colorName, or null if none found.
  **/

  public Color getColorByName(String colorName) {
    return getColor(colorName, "name");
  }

  /**
    * Finds the color matching the hex value passed in.
    *
    * @param colorHexValue the desired color to find.
    * @return Color that has the same hex value, or null if none found.
  **/
  public Color getColorByHexValue(String colorHexValue) {
    return getColor(colorHexValue, "hex");
  }

  /**
    * Finds the color matching the type of argument passed in.
    *
    * @param colorVal, the desired color to find.
    * @param nameType, the format of the color argument passed in.
    * @return Color that has the same nameType, or null if none found.
  **/
  private Color getColor(String colorVal, String nameType) {
    if(colorVal == null) { throw new NullPointerException("Object cannot be null."); }

    Node<Color> head = this.getHead();
    if(head == null) { return null; }
    Node<Color> current = head;
    // iterate through list
    for(int i=0; i < size(); i++) {
      // use different search params depending on second argument
      if(nameType == "name") {
        if(colorVal.equalsIgnoreCase(current.getElement().getName())) { return current.getElement(); }
      } else if(nameType == "hex") {
        if(colorVal.equalsIgnoreCase(current.getElement().getHexValue())) { return current.getElement(); }
      }

      current = current.getNext();
    }

    return null;
  }


}