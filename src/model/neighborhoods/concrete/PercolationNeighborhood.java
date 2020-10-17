package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.PercolationState;
import model.neighborhoods.NonInfluentialNeighborhood;

public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  public PercolationNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentNeighborsOnly(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState) {
    if(currentState.equals(PercolationState.OPEN)) {
      return handleOpenState();
    }
    return currentState;
  }

  private State handleOpenState() {
    boolean adjacentToWater = neighborPositionToStateContainsState(new State(PercolationState.WATER));
    if(adjacentToWater) {
      return new State(PercolationState.WATER);
    }
    return new State(PercolationState.OPEN);
  }
}
