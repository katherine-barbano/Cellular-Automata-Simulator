package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum SpreadingOfFireState implements StateType {
  EMPTY(CellColors.WHITE),
  BURNING(CellColors.RED),
  TREE(CellColors.GREEN);

  private CellColors defaultColor;

  SpreadingOfFireState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor() {
    return defaultColor;
  }
}
