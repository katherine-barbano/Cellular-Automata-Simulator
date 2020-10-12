package view.CellFormat;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public enum CellColors {
  BLACK(Color.BLACK),
  WHITE(Color.WHITE),
  RED(Color.RED),
  ORANGE(Color.ORANGE),
  YELLOW(Color.YELLOW),
  GREEN(Color.GREEN),
  BLUE(Color.BLUE),
  PURPLE(Color.PURPLE),
  STARRY_NIGHT("data/images/Starsinthesky.jpg"),
  POLKA_DOTS("data/images/polkadots.png");


  private Paint cellColor;

  CellColors(Paint color) {
    this.cellColor = color;
  }

  CellColors(String filePath){
    FileInputStream selectedFile = null;
    try {
      selectedFile = new FileInputStream(filePath);
      Image cellImage = new Image(selectedFile);
      cellColor = new ImagePattern(cellImage);
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFound in CellColors");
      cellColor = Color.GRAY;
    }

  }

  public Paint getCellColor() {
    return this.cellColor;
  }

}
