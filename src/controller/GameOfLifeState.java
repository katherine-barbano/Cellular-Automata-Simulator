package controller;

import java.util.List;
import model.Cell;
import view.CellFormat.CellColors;

public enum GameOfLifeState implements State{
  DEAD("Dead", CellColors.WHITE),
  ALIVE("Alive", CellColors.BLACK);

  private String stateName;
  private CellColors stateColor;

  GameOfLifeState(String nameOfState, CellColors color) {
    this.stateName = nameOfState;
    this.stateColor= color;
  }

  public String toString() {
    return this.stateName;
  }

  public void setStateColor(CellColors color){
    this.stateColor = color;
  }

  public CellColors getStateColor(){
    return stateColor;
  }


  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }

}
