package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;

public enum GameOfLifeState implements StateType {
  ALIVE(CellColors.BLACK),
  DEAD(CellColors.WHITE);

  private CellColors defaultColor;

  GameOfLifeState(CellColors color){
    this.defaultColor = color;
  }

  @Override
  public CellColors getDefaultColor(){
    return defaultColor;
  }
}

