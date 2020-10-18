package view;

import controller.State;
import controller.StateType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import view.CellFormat.CellColors;

public class CellDisplay extends Rectangle {

  public static final String STATE_ENUM_SUFFIX = "State";
  public static final String STATE_TYPE_FOLDER ="controller.stateType.";
  private StateType myStateType;
  private CellColors currentColor;
  private GridDisplay myGridDisplay;

  public CellDisplay(StateType state, double cellSize, GridDisplay gridDisplay){
    super(cellSize,cellSize);
    this.myStateType = state;
    this.myGridDisplay = gridDisplay;

    this.currentColor = SimulationView.STATE_COLOR_MAP.get(myStateType);
    this.setFill(currentColor.getCellColor());
    getStyleClass().add("cell-display");

    this.setOnMouseClicked(mouseEvent->handleMouseEvent());
  }

  public StateType getMyStateType() { return myStateType; }

  public CellColors getCurrentColor(){ return currentColor; }

  private void setMyState(StateType inputState){
    myStateType = inputState;
    currentColor = SimulationView.STATE_COLOR_MAP.get(myStateType);
    this.setFill(currentColor.getCellColor());

    myGridDisplay.updateCellInGrid(myGridDisplay.getRowIndex(this), myGridDisplay.getColumnIndex(this),inputState);
  }

  private void handleMouseEvent(){
    setMyState(getNextState(myStateType));
  }

  private StateType getNextState(StateType currentState){

    try {
      Class<?> StateEnum = Class.forName(STATE_TYPE_FOLDER + myGridDisplay.getMySimulationType()+STATE_ENUM_SUFFIX);
      List<StateType> states = Arrays.asList((StateType[])StateEnum.getEnumConstants());
      int currentStateIndex = states.indexOf(currentState);
      return states.get((currentStateIndex+1)% states.size());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return currentState;
    }
  }

}


