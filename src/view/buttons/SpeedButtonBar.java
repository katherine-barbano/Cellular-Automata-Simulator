package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SpeedButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT=50;
  private Button mySpeedUp;
  private Button mySlowDown;

  public SpeedButtonBar(ResourceBundle bundle){
    this.mySpeedUp = new SpeedUpButton(bundle);
    this.getChildren().add(mySpeedUp);

    this.mySlowDown = new SlowDownButton(bundle);
    this.getChildren().add(mySlowDown);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  public Button getSpeedUpButton() {
    return mySpeedUp;
  }

  public Button getSlowDownButton() {
    return mySpeedUp;
  }

}
