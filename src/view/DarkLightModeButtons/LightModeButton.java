package view.DarkLightModeButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class LightModeButton extends Button {

  public static final String LIGHT_MODE_BUTTON_PROPERTIES = "LightModeButton";

  public LightModeButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(LIGHT_MODE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("light-button");
  }


}
