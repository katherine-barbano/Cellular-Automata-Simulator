package model;

import controller.State;

public abstract class EdgePolicy {

  private int centerCellRow;
  private int centerCellColumn;
  private State[][] states;

  protected EdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    this.centerCellRow = centerCellRow;
    this.centerCellColumn = centerCellColumn;
    this.states = allStatesInCSV;
  }

  protected abstract State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor);

  protected int getCenterCellRow() {
    return centerCellRow;
  }

  protected int getCenterCellColumn() {
    return centerCellColumn;
  }

  protected State[][] getStates() {
    return states;
  }

  protected State getNeighborStateFromAdjacentPosition(int[] neighborPosition) throws IndexOutOfBoundsException{
    int neighborRow = centerCellRow + neighborPosition[0];
    int neighborColumn = centerCellColumn + neighborPosition[1];

    return states[neighborRow][neighborColumn];
  }
}
