package view.FileButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * New File Button will be used to Upload a new Configuration file
 */
public class NewFileButton extends Button {

  public static final String NEW_FILE_BUTTON_PROPERTIES = "NewFileButton";

  public NewFileButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(NEW_FILE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("new-file-button");
  }
}
