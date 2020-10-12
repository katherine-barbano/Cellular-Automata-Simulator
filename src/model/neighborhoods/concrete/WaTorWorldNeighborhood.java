package model.neighborhoods.concrete;

import controller.State;
import controller.WaTorWorldState;
import java.util.Map;
import model.neighborhoods.InfluentialNeighborhood;

public class WaTorWorldNeighborhood extends InfluentialNeighborhood {

  public static final String MIN_BREED_AGE_PROPERTIES = "WaTorWorld_MinimumBreedingAge";

  public WaTorWorldNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public State getNextState(State currentState) {
    if(currentState == WaTorWorldState.SHARK) {
      return handleSharkState(currentState);
    }
    else if(currentState == WaTorWorldState.FISH) {
      return handleFishState(currentState);
    }
    else {
      return handleEmptyState();
    }
  }

  private State handleSharkState(State currentState) {
    return null;
  }

  private State handleFishState(State currentState) {
    return null;
  }

  private State handleBreeding(State currentState) {
    int minimumBreedingAge = Integer.parseInt(getModelResources().getString(MIN_BREED_AGE_PROPERTIES));
    //if(currentState.get)
    return null;
  }

  private State handleEmptyState() {
    return WaTorWorldState.EMPTY;
  }

  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    return null;
  }

  @Override
  public Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    return null;
  }
}
