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
    this.setId(state.toString());
    getStyleClass().add("cell-display");
  }

  public State getMyState(){
    return myState;
  }

  public void setMyState(State inputState){
    myState = inputState;
    this.setId(inputState.toString());
  }
}
