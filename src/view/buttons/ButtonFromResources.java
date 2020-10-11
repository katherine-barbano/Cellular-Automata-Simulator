package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class ButtonFromResources extends Button {

  public ButtonFromResources(ResourceBundle resources, String propertiesKey){
    super();
    String buttonText = resources.getString(propertiesKey);
    this.setText(buttonText);
  }
}
