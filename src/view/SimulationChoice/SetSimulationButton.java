package view.SimulationChoice;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class SetSimulationButton extends Button {
  public static final String FORMAT_BUTTON_PROPERTIES="ChangeSimulationButton";

  public SetSimulationButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }
}
