package view.ControlButtons;


import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Step button will step to the next frame (only when the game is paused).
 * @author Heather Grune (hlg20)
 */
public class StepButton extends Button {

  public static final String STEP_BUTTON_PROPERTIES = "StepButton";

  public StepButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(STEP_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("step-button");
  }


}