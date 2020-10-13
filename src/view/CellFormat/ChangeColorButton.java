package view.CellFormat;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Change Color Button will be used to
 */
public class ChangeColorButton extends Button {

  public static final String FORMAT_BUTTON_PROPERTIES="ChangeColorButton";

  public ChangeColorButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("change-color-button");
  }

}
