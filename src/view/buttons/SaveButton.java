package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class SaveButton extends Button {

  public static final String SAVE_BUTTON_PROPERTIES = "SaveButton";

  public SaveButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(SAVE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setOnAction(event -> save());
  }

  private void save(){

  }

}
