package view.CellFormat;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import view.SimulationView;

/**
 * All Possible colors and images to fill the cells.
 */
public enum CellColors implements CellFill{
  BLACK(Color.BLACK),
  WHITE(Color.WHITE),
  RED(Color.RED),
  ORANGE(Color.ORANGE),
  YELLOW(Color.YELLOW),
  GREEN(Color.GREEN),
  BLUE(Color.BLUE),
  PURPLE(Color.PURPLE);

  public static final String IMAGE_PROPERTIES_SUFFIX = "_image";
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+SimulationView.RESOURCE_BUNDLE);
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
