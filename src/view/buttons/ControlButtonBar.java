package view.buttons;

import javafx.scene.layout.HBox;

public class ControlButtonBar extends HBox {

  private PlayPauseButton myPlayPause;
  private StepButton myStep;

  public ControlButtonBar(){
    myPlayPause = new PlayPauseButton();
    this.getChildren().add(myPlayPause);

    myStep = new StepButton();
    this.getChildren().add(myStep);

    this.getStyleClass().add("button-bar");
  }
}
