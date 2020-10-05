package view;


import javafx.scene.control.Button;

public class StepButton extends Button {

  public static final String STEP_BUTTON_TEXT = "Step";

  public StepButton(){
    super(STEP_BUTTON_TEXT);
    this.setOnAction(event -> step());
  }

  private void step(){

  }

}