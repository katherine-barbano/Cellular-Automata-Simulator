package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.layout.HBox;

public class SimulationButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  private GameOfLifeButton myGameOfLife;

  public SimulationButtonBar(ResourceBundle resources){
    myGameOfLife = new GameOfLifeButton(resources);
    this.getChildren().add(myGameOfLife);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

}
