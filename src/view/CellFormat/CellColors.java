package view.CellFormat;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


/**
 * All Possible colors and images to fill the cells.
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

  public Paint getCellFill() {
    return this.cellFill;
  }

  @Override
  public String toString(){
    String lowercaseName = this.name().toLowerCase();
    String firstLetter = this.name().substring(0,1);
    return firstLetter + lowercaseName.substring(1);
  }

}
