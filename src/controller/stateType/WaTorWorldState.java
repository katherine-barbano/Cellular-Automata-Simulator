package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum WaTorWorldState implements StateType {
  FISH(CellColors.RED),
  SHARK(CellColors.BLACK),
  EMPTY(CellColors.BLUE);

  private CellColors defaultColor;

  WaTorWorldState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor(){
    return defaultColor;
  }

}
