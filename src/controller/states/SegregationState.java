package controller.states;

import controller.State;
import view.CellFormat.CellColors;

public enum SegregationState implements State {
  XAGENT("xagent"),
  OAGENT("oagent"),
  EMPTY("empty");

  private String stateName;
  private CellColors stateColor;

  SegregationState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  public String toString() {
    return this.stateName;
  }

  @Override
  public void setStateColor(CellColors color){
    this.stateColor = color;
  }

  @Override
  public CellColors getStateColor(){
    return stateColor;
  }

  @Override
  public int getOrdinal(){
    return this.ordinal();
  }
}