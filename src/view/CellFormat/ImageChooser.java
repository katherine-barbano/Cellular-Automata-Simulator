package view.CellFormat;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class ImageChooser extends Button {

  public static final String IMAGE_CHOOSER_PROPERTIES = "ImageChooserButton";
  private FileChooser myFileChooser;

  public ImageChooser(ResourceBundle resources){
    super();
    String buttonText = resources.getString(IMAGE_CHOOSER_PROPERTIES);
    this.setText(buttonText);

    this.myFileChooser = new FileChooser();
    this.setOnAction(event -> pickImageFile());
  }

  private void pickImageFile(){
    //File selectedFile = myFileChooser.showOpenDialog(stage);

  }

}
