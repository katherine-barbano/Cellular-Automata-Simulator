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
    List<int[]> positionsOfEmptyNeighbors = positionsOfTargetStateNeighbors(WaTorWorldState.EMPTY);
    int minimumBreedingAge = Integer.parseInt(getModelResources().getString(MIN_BREED_AGE_PROPERTIES));
    if(currentState == WaTorWorldState.SHARK) {
      return handleSharkState(currentWaTorWorldState, positionsOfEmptyNeighbors, minimumBreedingAge);
    }
    else if(currentState == WaTorWorldState.FISH) {
      return handleFishState(currentWaTorWorldState, positionsOfEmptyNeighbors, minimumBreedingAge);
    }
    else {
      return handleEmptyState();
    }
  }

  private State handleSharkState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    List<int[]> positionsOfFishNeighbors = positionsOfTargetStateNeighbors(WaTorWorldState.FISH);

    if(currentState.getAge()>minimumBreedingAge && positionsOfEmptyNeighbors.size()>0) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfFishNeighbors.size() == 0 && positionsOfEmptyNeighbors.size()>0) {
      return handleMove();
    }
    else if(positionsOfFishNeighbors.size()>0) {
      return handleEat();
    }
    else {
      return handleAging();
    }
  }

  private State handleFishState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    if(currentState.getAge()>minimumBreedingAge) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfEmptyNeighbors.size()>0) {
      return handleMove();
    }
    else {
      return handleAging();
    }
  }

  private State handleBreeding(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    int[] positionToBreedInto = currentState.getOpenPosition(positionsOfEmptyNeighbors);
    replaceEmptyWithNewlyBornSeaCreature(positionToBreedInto);
    currentState.setAge(currentState.getAge() + 1);
    currentState.setNextPositionStationary();
    return currentState;
  }

  private void replaceEmptyWithNewlyBornSeaCreature(int[] positionToBreedInto) {
    State babyShark = WaTorWorldState.SHARK;
    replaceNeighborStateWithNewState(positionToBreedInto,babyShark);
  }

  private List<int[]> positionsOfTargetStateNeighbors(WaTorWorldState state) {
    List<int[]> emptyIndices = new ArrayList<>();
    Map<int[], State> neighborPositionToState = getNeighborPositionToState();
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(thisKey) == state) {
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
