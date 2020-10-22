package view.SimulationChoice;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The set simulation button will be pressed by the user to change the simulation to the selected
 * type.
 * @author Heather Grune (hlg20)
 */
public class SetSimulationButton extends Button {
  public static final String FORMAT_BUTTON_PROPERTIES="ChangeSimulationButton";

  public SetSimulationButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("simulation-button");
  }
}
