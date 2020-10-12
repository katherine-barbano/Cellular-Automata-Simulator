package model.neighborhoods.concrete;

import controller.State;
import java.util.Map;
import model.neighborhoods.InfluentialNeighborhood;

public class WatorWorldNeighborhood extends InfluentialNeighborhood {

  public WatorWorldNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public State getNextState(State currentState) {

  }

  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {

  }

  @Override
  public Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {

  }
}
