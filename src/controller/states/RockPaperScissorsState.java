package controller.states;

import controller.State;
import view.CellFormat.CellColors;

public enum RockPaperScissorsState implements State {
  ROCK("Rock"),
  PAPER("Paper"),
  SCISSORS("Scissors");

  private String stateName;
  private CellColors stateColor;

  RockPaperScissorsState(String nameOfState) {
    this.stateName = nameOfState;
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
