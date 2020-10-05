package view;

import javafx.scene.layout.HBox;

public class SimulationButtonBar extends HBox {

  private GameOfLifeButton myGameOfLife;

  public SimulationButtonBar(){
    myGameOfLife = new GameOfLifeButton();
    this.getChildren().add(myGameOfLife);

    this.getStyleClass().add("button-bar");
  }

}
