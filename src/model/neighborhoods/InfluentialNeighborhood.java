package model.neighborhoods;

import controller.State;
import java.util.List;
import java.util.Map;
import model.Neighborhood;

public abstract class InfluentialNeighborhood extends Neighborhood {

  public InfluentialNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  public void deleteMovedStateFromNeighborhoodsOfNeighbors(State newState) {
    for(int[] neighborPosition : getNeighborhoodsOfNeighbors().keySet()) {
      int[] positionOfCenterCellInNeighbor = negateArray(neighborPosition);
      Neighborhood neighborhoodOfNeighbor = getNeighborhoodsOfNeighbors().get(neighborPosition);
      neighborhoodOfNeighbor.replaceNeighborStateWithNewState(positionOfCenterCellInNeighbor,newState);
    }
  }

  private int[] negateArray(int[] array) {
    int[] newArray = new int[array.length];
    for(int index = 0; index<array.length; index++) {
      newArray[index] = array[index]*(-1);
    }
    return newArray;
  }
}
