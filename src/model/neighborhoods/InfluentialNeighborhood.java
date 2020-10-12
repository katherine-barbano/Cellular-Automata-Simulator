package model.neighborhoods;

import controller.State;
import java.util.Map;
import model.Neighborhood;

public abstract class InfluentialNeighborhood extends Neighborhood {

  public InfluentialNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  public abstract State getNextState(State currentState);

  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    return null;
  }

  public abstract Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV);
}
