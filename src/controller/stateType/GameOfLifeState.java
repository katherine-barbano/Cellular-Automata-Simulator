package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public enum GameOfLifeState implements StateType {
  ALIVE(CellColors.BLACK),
  DEAD(CellColors.WHITE);

  private CellFill defaultColor;

  GameOfLifeState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}

