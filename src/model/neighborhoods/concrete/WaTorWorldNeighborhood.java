package model.neighborhoods.concrete;

import controller.State;
import controller.WaTorWorldState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.neighborhoods.InfluentialNeighborhood;

public class WaTorWorldNeighborhood extends InfluentialNeighborhood {

  public static final String MIN_BREED_AGE_PROPERTIES = "WaTorWorld_MinimumBreedingAge";

  public WaTorWorldNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public State getNextState(State currentState) {
    WaTorWorldState currentWaTorWorldState = (WaTorWorldState)currentState;
    List<int[]> positionsOfEmptyNeighbors = positionsOfEmptyNeighbors();
    if(currentState == WaTorWorldState.SHARK) {
      return handleSharkState(currentWaTorWorldState, positionsOfEmptyNeighbors);
    }
    else if(currentState == WaTorWorldState.FISH) {
      return handleFishState(currentWaTorWorldState, positionsOfEmptyNeighbors);
    }
    else {
      return handleEmptyState();
    }
  }

  private State handleSharkState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    return null;
  }

  private State handleFishState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    return null;
  }

  private State handleBreeding(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    int minimumBreedingAge = Integer.parseInt(getModelResources().getString(MIN_BREED_AGE_PROPERTIES));
    if(currentState.getAge()>minimumBreedingAge) {
      int[] positionToBreedInto = currentState.getOpenPosition(positionsOfEmptyNeighbors);
      replaceEmptyWithNewlyBornSeaCreature(positionToBreedInto);
      currentState.setAge(currentState.getAge() + 1);
      currentState.setNextPositionStationary();
    }
    return currentState;
  }

  private void replaceEmptyWithNewlyBornSeaCreature(int[] positionToBreedInto) {
    State babyShark = WaTorWorldState.SHARK;
    replaceNeighborStateWithNewState(positionToBreedInto,babyShark);
  }

  private List<int[]> positionsOfEmptyNeighbors() {
    List<int[]> emptyIndices = new ArrayList<>();
    Map<int[], State> neighborPositionToState = getNeighborPositionToState();
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(thisKey) == WaTorWorldState.EMPTY) {
        emptyIndices.add(thisKey);
      }
    }
    return emptyIndices;
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
