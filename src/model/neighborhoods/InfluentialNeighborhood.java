package model.neighborhoods;

import controller.State;
import java.util.List;
import java.util.Map;
import model.Neighborhood;

public abstract class InfluentialNeighborhood extends Neighborhood {

  public InfluentialNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  public abstract State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors);

  public abstract State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell);

  public void deleteMovedStateFromNeighborhoodsOfNeighbors(Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    System.out.println("hi");
  }
}
