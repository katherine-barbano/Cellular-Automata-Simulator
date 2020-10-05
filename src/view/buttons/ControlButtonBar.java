package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.layout.HBox;

public class ControlButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT=50;
  private PlayPauseButton myPlayPause;
  private StepButton myStep;
  private SaveButton mySave;
  private ResourceBundle myResources;

  public ControlButtonBar(ResourceBundle bundle){

    myPlayPause = new PlayPauseButton(bundle);
    this.getChildren().add(myPlayPause);

    myStep = new StepButton(bundle);
    this.getChildren().add(myStep);

    mySave = new SaveButton(bundle);
    this.getChildren().add(mySave);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }
}
