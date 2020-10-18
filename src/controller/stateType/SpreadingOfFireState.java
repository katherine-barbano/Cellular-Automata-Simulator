package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public enum SpreadingOfFireState implements StateType {
  EMPTY(CellColors.WHITE),
  BURNING(CellColors.RED),
  TREE(CellColors.GREEN);

  private CellFill defaultColor;

  SpreadingOfFireState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}
