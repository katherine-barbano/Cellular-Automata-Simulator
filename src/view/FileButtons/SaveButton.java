package view.FileButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Save Button will save the current tile configuration to a file.
 */
public class SaveButton extends Button {

  public static final String SAVE_BUTTON_PROPERTIES = "SaveButton";

  public SaveButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(SAVE_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }

}
