package view.DarkLightModeButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Dark Mode Button will allow the user to change the SimulationView to Dark Mode
 * @author Heather Grune (hlg20)
 */
public class DarkModeButton extends Button {

  public static final String DARK_MODE_BUTTON_PROPERTIES = "DarkModeButton";

  public DarkModeButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(DARK_MODE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("dark-button");
  }

}
