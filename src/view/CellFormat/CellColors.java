package view.CellFormat;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * All Possible colors to fill the cells.
 * @author Heather Grune (hlg20)
 */
public enum CellColors implements CellFill{
  BLACK(Color.BLACK),
  WHITE(Color.WHITE),
  RED(Color.web("#FF8371")),
  ORANGE(Color.web("#FFAD71")),
  YELLOW(Color.web("#FEF47D")),
  GREEN(Color.web("#CDFE9B")),
  BLUE(Color.web("#80C5FF")),
  PURPLE(Color.web("#E0BCFF"));

  private Paint cellFill;

  CellColors(Paint color) {
    this.cellFill = color;
  }

  /**
   * Get the color associated with the enum value
   * @return a paint fill color
   */
  public Paint getCellFill() {
    return this.cellFill;
  }

  /**
   * Override toString method.
   * @return Name of color with capitalized first letter
   */
  @Override
  public String toString(){
    String lowercaseName = this.name().toLowerCase();
    String firstLetter = this.name().substring(0,1);
    return firstLetter + lowercaseName.substring(1);
  }

}
