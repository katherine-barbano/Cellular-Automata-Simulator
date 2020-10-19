package view.CellFormat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import view.SimulationView;

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
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+IMAGE_BUNDLE );
  private Paint cellFill;


  CellImages(){
    String imageKey = this.toString() + IMAGE_PROPERTIES_SUFFIX;
    String imageFilePath = resourceBundle.getString(imageKey);
    setCellImage(imageFilePath);
  }

  public void setCellImage(String filePath){
    FileInputStream selectedFile = null;
    try {
      selectedFile = new FileInputStream(filePath);
      Image cellImage = new Image(selectedFile);
      cellFill = new ImagePattern(cellImage);
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFound in CellColors");
      cellFill = Color.GRAY;
    }
  }

  public Paint getCellFill() {
    return this.cellFill;
  }

  @Override
  public String toString(){
    return this.name().toLowerCase();
  }
}
