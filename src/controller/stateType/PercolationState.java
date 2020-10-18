package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum PercolationState implements StateType {
  OPEN(CellColors.WHITE),
  WATER(CellColors.BLUE),
  BLOCKED(CellColors.BLACK);

  private CellColors defaultColor;

  PercolationState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor() {
    return defaultColor;
  }
}

