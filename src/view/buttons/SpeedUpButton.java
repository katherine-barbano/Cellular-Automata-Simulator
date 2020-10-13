package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Speed Up Button will speed up the animation.
 */
public class SpeedUpButton extends Button {

  public static final String SPEED_UP_BUTTON_PROPERTIES = "SpeedUpButton";

  public SpeedUpButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(SPEED_UP_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }
}
