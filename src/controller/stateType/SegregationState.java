package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum SegregationState implements StateType {
  XAGENT(CellColors.BLUE),
  OAGENT(CellColors.BLACK),
  EMPTY(CellColors.WHITE);

  private CellColors defaultColor;

  SegregationState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor() {
    return defaultColor;
  }
}
