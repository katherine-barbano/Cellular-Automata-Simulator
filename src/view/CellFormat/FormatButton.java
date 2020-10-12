package view.CellFormat;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class FormatButton extends Button {

  public static final String FORMAT_BUTTON_PROPERTIES="FormatButton";

  public FormatButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
  }

}
