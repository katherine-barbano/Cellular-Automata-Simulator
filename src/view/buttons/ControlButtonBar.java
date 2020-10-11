package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT=50;

  private Button myPlayPause;
  private Button myStep;
  private Button mySpeedUp;
  private Button mySlowDown;

  public ControlButtonBar(ResourceBundle bundle){

    this.myPlayPause = new PlayPauseButton(bundle);
    this.getChildren().add(myPlayPause);

    this.myStep = new StepButton(bundle);
    this.getChildren().add(myStep);

    this.mySpeedUp = new SpeedUpButton(bundle);
    this.getChildren().add(mySpeedUp);

    this.mySlowDown = new SlowDownButton(bundle);
    this.getChildren().add(mySlowDown);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  public Button getMyStep() {
    return myStep;
  }

  public Button getMyPlayPause() {
    return myPlayPause;
  }

  public Button getSpeedUpButton() {
    return mySpeedUp;
  }

  public Button getSlowDownButton() {
    return mySlowDown;
  }
}
