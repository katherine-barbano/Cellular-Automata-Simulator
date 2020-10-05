package view.buttons;

import javafx.scene.control.Button;

public class SaveButton extends Button {

  public static final String SAVE_BUTTON_TEXT = "Save";

  public SaveButton(){
    super(SAVE_BUTTON_TEXT);
    this.setOnAction(event -> save());
  }

  private void save(){

  }

}
