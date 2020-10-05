package view.buttons;

import javafx.scene.layout.HBox;

public class ControlButtonBar extends HBox {

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

    this.getStyleClass().add("button-bar");
  }
}
