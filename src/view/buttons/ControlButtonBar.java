package view.buttons;

import javafx.scene.layout.HBox;

public class ControlButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT=50;
  private PlayPauseButton myPlayPause;
  private StepButton myStep;
  private SaveButton mySave;

  public ControlButtonBar(){
    myPlayPause = new PlayPauseButton();
    this.getChildren().add(myPlayPause);

    myStep = new StepButton();
    this.getChildren().add(myStep);

    mySave = new SaveButton();
    this.getChildren().add(mySave);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }
}
