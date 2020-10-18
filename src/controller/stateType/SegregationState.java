package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public enum SegregationState implements StateType {
  XAGENT(CellColors.BLUE),
  OAGENT(CellColors.BLACK),
  EMPTY(CellColors.WHITE);

  private CellFill defaultColor;

  SegregationState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}
