package controller;

import java.util.List;
import view.CellFormat.CellColors;

public class State {

  public static final CellColors DEFAULT_COLOR = CellColors.BLACK;

  private StateType stateType;
  private CellColors stateColor;

  public State(StateType stateName) {
    this.stateType = stateName;
    this.stateColor = DEFAULT_COLOR;
  }

  public StateType getStateType(){
    return stateType;
  }

  public void setStateType(StateType inputStateType) { this.stateType = inputStateType; }

  public void setStateColor(CellColors color){
    this.stateColor = color;
  }

  public CellColors getStateColor(){
    return stateColor;
  }

  public boolean equals(State otherState) {
    return stateType == otherState.getStateType();
  }

  public boolean equals(StateType otherStateType) {
    return stateType == otherStateType;
  }
}
