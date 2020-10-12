package view;

import static controller.GameOfLifeState.ALIVE;
import static controller.GameOfLifeState.DEAD;

import controller.State;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    setMyState(getNextState(myState));
  }

  private State getNextState(State currentState){
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
  }

}


