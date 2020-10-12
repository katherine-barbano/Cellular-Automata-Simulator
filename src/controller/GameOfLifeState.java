package controller;

import java.util.List;

public enum GameOfLifeState implements State{
  DEAD,
  ALIVE;

  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }

  @Override
  public String toString() {
    return "GameOfLife";
  }

}
