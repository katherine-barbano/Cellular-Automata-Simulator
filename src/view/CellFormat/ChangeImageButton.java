package view.CellFormat;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Change Image Button can be pressed by the user to set an image for all of the cells with
 * a chosen status.
 * @author Heather Grune (hlg20)
 */
public class ChangeImageButton extends Button {

  public static final String FORMAT_BUTTON_PROPERTIES="ChangeImageButton";

  public ChangeImageButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(FORMAT_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setId("change-image-button");
  }

}
