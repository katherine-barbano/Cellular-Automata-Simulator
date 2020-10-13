package model.neighborhoods.concrete;

import controller.PercolationState;
import controller.State;
import java.util.HashMap;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  public PercolationNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(!(row==0 && column==0)) {
          int coordinateDimensions = Integer
              .parseInt(getModelResources().getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
          int[] relativePositionOfNeighbor = new int[coordinateDimensions];
          relativePositionOfNeighbor[0] = row;
          relativePositionOfNeighbor[1] = column;
          putNeighborPositionIntoMap(relativePositionOfNeighbor,
              centerCellRow, centerCellColumn, allStatesInCSV);
        }
      }
    }
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    if(currentState == PercolationState.OPEN) {
      handleOpenState();
    }
    return currentState;
  }

  private State handleOpenState() {
    boolean adjacentToWater = neighborPositionToStateContainsState(PercolationState.WATER);
    if(adjacentToWater) {
      return PercolationState.WATER;
    }
    return PercolationState.OPEN;
  }
}
