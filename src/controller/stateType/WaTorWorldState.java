package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

public enum WaTorWorldState implements StateType {
  FISH(CellColors.RED),
  SHARK(CellColors.BLACK),
  EMPTY(CellColors.BLUE);

  private CellFill defaultColor;

  WaTorWorldState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }

}
