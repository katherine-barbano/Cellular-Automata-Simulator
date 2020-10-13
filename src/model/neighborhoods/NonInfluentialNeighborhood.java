package model.neighborhoods;

import controller.State;
import java.util.Map;
import model.Neighborhood;

public abstract class NonInfluentialNeighborhood extends Neighborhood {

  public NonInfluentialNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  public abstract State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors);

  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    return nextState;
  }
}
