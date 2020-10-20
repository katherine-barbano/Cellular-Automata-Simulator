package view.ControlButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Slow Down Button will slow down the speed of the animation.
 */
public class SlowDownButton extends Button {

  public static final String SLOW_DOWN_BUTTON_PROPERTIES = "SlowDownButton";

  public SlowDownButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(SLOW_DOWN_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("slow-down");
  }

}
