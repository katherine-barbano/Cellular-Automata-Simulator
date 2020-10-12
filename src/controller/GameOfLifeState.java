package controller;

import java.util.List;

public enum GameOfLifeState implements State{
  DEAD("Dead"),
  ALIVE("Alive");

  private String stateName;

  GameOfLifeState(String nameOfState) {
    this.stateName = nameOfState;
  }

  public String toString() {
    return this.stateName;
  }


  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }

}
