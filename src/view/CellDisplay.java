package view;

import static controller.GameOfLifeState.ALIVE;
import static controller.GameOfLifeState.DEAD;

import controller.State;
import javafx.scene.shape.Shape;

public class CellDisplay extends Shape {

  State myState;

  public CellDisplay(State state, double cellSize){
    super();
    myState = state;
    this.setId(getIdFromState(state));
    getStyleClass().add("cell-display");
  }

  private String getIdFromState(State input){
    if (DEAD.equals(input)) {
      return "dead";
    } else if (ALIVE.equals(input)) {
      return "alive";
    }
    return "none";
  }

  public State getMyState(){
    return myState;
  }
}
