package controller;

import java.util.List;
import view.CellFormat.CellColors;

public class State {

  private String stateName;
  private CellColors stateColor;

  public State(String stateName) {
    this.stateName = stateName;
  }

  public String toString() {
    return this.stateName;
  }

  public void setStateColor(CellColors color){
    this.stateColor = color;
  }

  public CellColors getStateColor(){
    return stateColor;
  }

  public boolean equals(State otherState) {
    return stateName.equals(otherState.toString());
  }

  public boolean equals(String otherName) {
    return stateName.equals(otherName);
  }
}
