package view.CellFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class ImageChooser extends Button {

  public static final String IMAGE_CHOOSER_PROPERTIES = "ImageChooserButton";
  public static final String IMAGE_PATH_PLACEHOLDER = "data/images/Starsinthesky.jpg";
  private FileChooser myFileChooser;
  private FileInputStream mySelectedFile;
  private Image myImage;

  public ImageChooser(ResourceBundle resources){
    super();
    String buttonText = resources.getString(IMAGE_CHOOSER_PROPERTIES);
    this.setText(buttonText);

    this.myFileChooser = new FileChooser();
    this.setOnAction(event -> pickImageFile());
  }

  private void pickImageFile(){
    //File selectedFile = myFileChooser.showOpenDialog(stage);
    try {
      mySelectedFile = new FileInputStream(IMAGE_PATH_PLACEHOLDER);
      myImage = new Image(mySelectedFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException();
    }

  }

  public Image getChosenImage(){
    return myImage;
  }

}
