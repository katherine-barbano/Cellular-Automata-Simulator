package controller.states;

import controller.State;
import view.CellFormat.CellColors;

public enum PercolationState implements State {
  OPEN("Open"),
  BLOCKED("Blocked"),
  WATER("Water");

  private String stateName;
  private CellColors stateColor;

  PercolationState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
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
