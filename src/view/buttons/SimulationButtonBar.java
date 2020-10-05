package view.buttons;

import javafx.scene.layout.HBox;

public class SimulationButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  private GameOfLifeButton myGameOfLife;

  public SimulationButtonBar(){
    myGameOfLife = new GameOfLifeButton();
    this.getChildren().add(myGameOfLife);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

}
