package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class SlowDownButton extends Button {

  public static final String SLOW_DOWN_BUTTON_PROPERTIES = "SlowDownButton";

  public SlowDownButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(SLOW_DOWN_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }

}
