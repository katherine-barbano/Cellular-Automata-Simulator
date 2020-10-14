package view;

import controller.State;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.scene.shape.Rectangle;
import view.CellFormat.CellColors;

public class CellDisplay extends Rectangle {

  State myState;
  private double mySize;
  private CellColors currentColor;
  private GridDisplay myGridDisplay;

  public CellDisplay(State state, double cellSize, GridDisplay gridDisplay){
    super(cellSize,cellSize);
    this.mySize = cellSize;
    this.myState = state;
    this.myGridDisplay = gridDisplay;

    this.currentColor = myState.getStateColor();
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

    myGridDisplay.updateCellInGrid(myGridDisplay.getRowIndex(this), myGridDisplay.getColumnIndex(this),inputState);
  }

  private void handleMouseEvent(){
    setMyState(getNextState(myState));
  }

  private State getNextState(State currentState){
    /*
    try {
      Method valuesMethod = currentState.getClass().getDeclaredMethod("values");
      Object obj = valuesMethod.invoke(null);
      State[] states = (State[]) obj;
      int ordinal = currentState.getOrdinal();
      return states[(ordinal+1)% states.length];
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      System.out.println("Enum.values method not found");
      return currentState;
    }
    */
    return null;
  }

}


