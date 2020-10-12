package controller;

import view.CellFormat.CellColors;

public enum PercolationState implements State{
  OPEN("Open"),
  BLOCKED("Blocked");

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
}
