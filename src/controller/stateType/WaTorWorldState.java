package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;
import view.CellFormat.CellImages;

public enum WaTorWorldState implements StateType {
  FISH(CellImages.FISH),
  SHARK(CellImages.SHARK),
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
