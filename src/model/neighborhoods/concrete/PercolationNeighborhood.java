package model.neighborhoods.concrete;

import controller.State;
import java.util.HashMap;
import java.util.Map;
import model.neighborhoods.NonInfluentialNeighborhood;

public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  public PercolationNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    Map<int[], State> neighborPositionToState = new HashMap<>();

    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(!(row==0 && column==0)) {
          int coordinateDimensions = Integer
              .parseInt(getModelResources().getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
          int[] relativePositionOfNeighbor = new int[coordinateDimensions];
          relativePositionOfNeighbor[0] = row;
          relativePositionOfNeighbor[1] = column;
          putNeighborPositionIntoMap(relativePositionOfNeighbor, neighborPositionToState,
              centerCellRow, centerCellColumn, allStatesInCSV);
        }
      }
    }
    return neighborPositionToState;
  }

  private void putNeighborPositionIntoMap(int[] relativePositionOfNeighbor, Map<int[], State> neighborPositionToState, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    try {
      State neighborState = getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor, centerCellRow, centerCellColumn, allStatesInCSV);
      neighborPositionToState.put(relativePositionOfNeighbor,neighborState);
    }
    catch(IndexOutOfBoundsException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
  }

  @Override

}
