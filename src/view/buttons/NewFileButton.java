package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class NewFileButton extends Button {

  public static final String NEW_FILE_BUTTON_PROPERTIES = "NewFileButton";

  public NewFileButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(NEW_FILE_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }
}
