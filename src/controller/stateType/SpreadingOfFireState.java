package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;
import view.CellFormat.CellImages;

public enum SpreadingOfFireState implements StateType {
  EMPTY(CellColors.WHITE),
  BURNING(CellImages.FIRE),
  TREE(CellImages.TREE);

  private CellFill defaultColor;

  SpreadingOfFireState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}
