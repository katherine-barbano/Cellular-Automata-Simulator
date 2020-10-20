package view.CellFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import view.SimulationView;

/**
 * The CellImages enum defines all possible images that the user can choose to fill the cell.
 */
public enum CellImages implements CellFill {

  STARRY_NIGHT(),
  POLKA_DOTS(),
  FIRE(),
  FISH(),
  ROCK(),
  PAPER(),
  SCISSORS(),
  SHARK(),
  TREE();

  public static final String IMAGE_PROPERTIES_SUFFIX = "_image";
  public static final String IMAGE_BUNDLE="Images";
  private ResourceBundle resourceBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+IMAGE_BUNDLE );
  private Paint cellFill;


  CellImages(){
    String imageKey = this.toString() + IMAGE_PROPERTIES_SUFFIX;
    String imageFilePath = resourceBundle.getString(imageKey);
    try {
      setCellImage(imageFilePath);
    } catch (FileNotFoundException e) {
      String exceptionMessage = resourceBundle.getString("ImageNotFoundFromFilePathException");
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText(exceptionMessage);
      alert.showAndWait();
    }
  }


  private void setCellImage(String filePath) throws FileNotFoundException {
    FileInputStream selectedFile = null;
    selectedFile = new FileInputStream(filePath);
    Image cellImage = new Image(selectedFile);
    cellFill = new ImagePattern(cellImage);
  }

  /**
   * Get the image associated with the enum value
   * @return JavaFX Paint for the image
   */
  public Paint getCellFill() {
    return this.cellFill;
  }

  /**
   * Overrides the to string method to produce an all lowercase String
   * @return lowercase string representing enum value
   */
  @Override
  public String toString(){
    return this.name().toLowerCase();
  }

  /**
   * Used in testing to test invalid files
   * @param path path to resource bundle
   */
  public void setResourceBundle(String path){
    resourceBundle=ResourceBundle.getBundle(path);
  }
}
