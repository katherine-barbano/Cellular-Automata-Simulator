package controller;

import view.CellFormat.CellColors;

public enum RockPaperScissorState implements State{
  ROCK("Rock"),
  PAPER("Paper"),
  SCISSOR("Scissor");

  private String stateName;
  private CellColors stateColor;

  RockPaperScissorState(String nameOfState) {
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
}
