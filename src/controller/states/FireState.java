package controller.states;

import controller.State;
import view.CellFormat.CellColors;

public enum FireState implements State {
  TREE("Tree"),
  BURNING("Burning"),
  EMPTY("Empty");

  private String stateName;
  private CellColors stateColor;

  FireState(String nameOfState) {
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
