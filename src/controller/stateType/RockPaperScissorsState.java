package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum RockPaperScissorsState implements StateType {
  ROCK(CellColors.BLACK),
  PAPER(CellColors.WHITE),
  SCISSORS(CellColors.POLKA_DOTS);

  private CellColors defaultColor;

  RockPaperScissorsState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor() {
    return defaultColor;
  }
}
