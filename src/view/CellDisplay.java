package view;

import static controller.GameOfLifeState.ALIVE;
import static controller.GameOfLifeState.DEAD;

import controller.State;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class CellDisplay extends Rectangle {

  public CellDisplay(State state,double cellSize){
    super(cellSize, cellSize);
    this.setId();
    getStyleClass().add("cell-display");
  }

  private String getIdFromState(State input){
    switch(input){
      case DEAD: return "dead";
      case ALIVE: return "alive";
      default: return "none";
    }
  }

}
