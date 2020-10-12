package view;

import static controller.GameOfLifeState.ALIVE;
import static controller.GameOfLifeState.DEAD;

import controller.State;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CellDisplay extends Rectangle {

  State myState;

  public CellDisplay(State state, double cellSize){
    super(cellSize,cellSize);
    myState = state;
    this.setId(state.toString());
    System.out.println(state.toString());
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
