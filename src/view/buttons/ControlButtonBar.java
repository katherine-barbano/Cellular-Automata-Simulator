package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * The ControlButtonBar contains all of the buttons that control the flow of the Simulation, including
 * Play/Pause, Step, SpeedUp, SlowDown.
 */
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

  /**
   * Accessor for Step Button
   * @return Step Button
   */
  public Button getMyStep() {
    return myStep;
  }

  /**
   * Accessor for Play/Pause Button
   * @return play/pause button
   */
  public Button getMyPlayPause() {
    return myPlayPause;
  }

  /**
   * Accessor for Speed Up Button
   * @return SpeedUp Button
   */
  public Button getSpeedUpButton() {
    return mySpeedUp;
  }

  /**
   * Accessor for Slow Down Button
   * @return SlowDown Button
   */
  public Button getSlowDownButton() {
    return mySlowDown;
  }
}
