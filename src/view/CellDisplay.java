package view;

import controller.State;
import controller.StateType;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public class CellDisplay extends Rectangle {

  public static final String STATE_ENUM_SUFFIX = "State";
  public static final String STATE_TYPE_FOLDER ="controller.stateType.";
  private StateType myStateType;
  private CellFill currentColor;
  private GridDisplay myGridDisplay;
  private ResourceBundle myResources;

  public CellDisplay(StateType state, double cellSize, GridDisplay gridDisplay, ResourceBundle resources){
    super(cellSize,cellSize);
    this.myStateType = state;
    this.myGridDisplay = gridDisplay;
    this.myResources = resources;

    this.currentColor = SimulationView.STATE_COLOR_MAP.get(myStateType);
    this.setFill(currentColor.getCellFill());

    getStyleClass().add("cell-display");

    this.setOnMouseClicked(mouseEvent->handleMouseEvent());
  }

  public StateType getMyStateType() { return myStateType; }

  public CellFill getCurrentColor(){ return currentColor; }

  public void setCurrentColor(CellFill newColor) { currentColor = newColor; }

  private void setMyState(StateType inputState){
    myStateType = inputState;
    currentColor = SimulationView.STATE_COLOR_MAP.get(myStateType);
    this.setFill(currentColor.getCellFill());

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
      String exceptionMessage = myResources.getString("StateClassNotFoundMessage");
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setContentText(exceptionMessage);
      alert.showAndWait();
      return currentState;
    }
  }

}


