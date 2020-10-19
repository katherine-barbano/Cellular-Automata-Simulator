package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;
import view.CellFormat.CellImages;

public enum RockPaperScissorsState implements StateType {
  ROCK(CellColors.BLACK),
  PAPER(CellColors.WHITE),
  SCISSORS(CellImages.POLKA_DOTS);

  private CellFill defaultColor;

  RockPaperScissorsState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}
