package controller;

import view.CellFormat.CellColors;

public class State {

  private StateType stateType;

  public State(StateType stateName) {
    this.stateType = stateName;
  }

  public StateType getStateType(){
    return stateType;
  }

  public void setStateType(StateType inputStateType) { this.stateType = inputStateType; }

  public boolean equals(State otherState) {
    return stateType == otherState.getStateType();
  }

  public boolean equals(StateType otherStateType) {
    return stateType == otherStateType;
  }
}
