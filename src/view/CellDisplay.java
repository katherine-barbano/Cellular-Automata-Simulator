package view;

import static controller.GameOfLifeState.ALIVE;
import static controller.GameOfLifeState.DEAD;

import controller.State;
import java.lang.reflect.Field;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import view.CellFormat.CellColors;

public class CellDisplay extends Rectangle {

  State myState;
  private double mySize;
  private CellColors currentColor;

  public CellDisplay(State state, double cellSize){
    super(cellSize,cellSize);
    mySize = cellSize;
    myState = state;
    currentColor = state.getStateColor();
    this.setFill(currentColor.getCellColor());
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


