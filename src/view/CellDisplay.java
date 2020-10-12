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
    currentColor = myState.getStateColor();
    this.setFill(currentColor.getCellColor());
    getStyleClass().add("cell-display");

    this.setOnMouseClicked(mouseEvent->handleMouseEvent());
  }

  public State getMyState(){
    return myState;
  }

  public void setMyState(State inputState){
    myState = inputState;
    currentColor = myState.getStateColor();
    this.setFill(currentColor.getCellColor());
  }

  private void handleMouseEvent(){

  }

}


