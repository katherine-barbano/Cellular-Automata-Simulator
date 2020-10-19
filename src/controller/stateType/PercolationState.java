package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public enum PercolationState implements StateType {
  OPEN(CellColors.WHITE),
  WATER(CellColors.BLUE),
  BLOCKED(CellColors.BLACK);

  private CellFill defaultColor;

  PercolationState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}

