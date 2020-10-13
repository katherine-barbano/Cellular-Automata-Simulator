package view.CellFormat;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class ChangeImageButton extends Button {

  public static final String FORMAT_BUTTON_PROPERTIES="ChangeImageButton";

  public ChangeImageButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }

}
