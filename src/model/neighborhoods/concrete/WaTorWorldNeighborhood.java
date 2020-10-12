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

  private State handleSharkState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    List<int[]> positionsOfFishNeighbors = positionsOfTargetStateNeighbors(WaTorWorldState.FISH);

    if(currentState.getAge()>minimumBreedingAge && positionsOfEmptyNeighbors.size()>0) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfFishNeighbors.size() == 0 && positionsOfEmptyNeighbors.size()>0) {
      return handleMove(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfFishNeighbors.size()>0) {
      return handleEat(currentState, positionsOfEmptyNeighbors);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleFishState(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    if(currentState.getAge()>minimumBreedingAge) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfEmptyNeighbors.size()>0) {
      return handleMove(currentState, positionsOfEmptyNeighbors);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleBreeding(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    int[] positionToBreedInto = currentState.getOpenPosition(positionsOfEmptyNeighbors);
    replaceEmptyWithNewlyBornSeaCreature(positionToBreedInto, currentState);
    handleAgingAndStationary(currentState);
    return currentState;
  }

  private void replaceEmptyWithNewlyBornSeaCreature(int[] positionToBreedInto, WaTorWorldState currentState) {
    //TODO: use reflection here
    State baby = null;
    if(currentState == WaTorWorldState.FISH) {
      baby = WaTorWorldState.FISH;
    }
    else {
      baby = WaTorWorldState.SHARK;
    }

    //check this? idk if I should put it into the neighbors or not
    //((WaTorWorldState)babyShark).setNextPositionMove();
    replaceNeighborStateWithNewState(positionToBreedInto,baby);
  }

  private State handleMove(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    ageByOne(currentState);
    currentState.setNextPositionMove(positionsOfEmptyNeighbors);
    //check this? idk if I should put it into the neighbors or not
    int[] positionToMoveInto = currentState.getNextPosition();
    replaceNeighborStateWithNewState(positionToMoveInto,currentState);
    return WaTorWorldState.EMPTY;
  }

  private State handleEat(WaTorWorldState currentState, List<int[]> positionsOfEmptyNeighbors) {
    ageByOne(currentState);
    State empty = WaTorWorldState.EMPTY;
    int[] openPosition = currentState.getOpenPosition(positionsOfEmptyNeighbors);
    replaceNeighborStateWithNewState(openPosition,empty);
    return currentState;
  }

  private State handleAgingAndStationary(WaTorWorldState currentState) {
    ageByOne(currentState);
    currentState.setNextPositionStationary();
    return currentState;
  }

  private void ageByOne(WaTorWorldState currentState) {
    currentState.setAge(currentState.getAge() + 1);
  }

  private State handleEmptyState() {
    return WaTorWorldState.EMPTY;
  }

  /***
   * If multiple fish or multiple sharks trying to move into or breed into the same cell, first give
   * priority to the oldest shark over all other sharks or fish (because they are the biggest and win in a fight),
   * and if there are multiple fish, give priority to the oldest fish (because they win in a fight against other fish).
   * The fish or shark that doesn't win the fight trying to move into the cell dies and disappears.
   * @param nextState
   * @param statesOfOverlappingNeighborsOnCell
   * @return
   */
  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    if(nextState == WaTorWorldState.EMPTY) {
      State oldestShark = returnOldestSeaCreature(WaTorWorldState.SHARK, statesOfOverlappingNeighborsOnCell);
      State oldestFish = returnOldestSeaCreature(WaTorWorldState.FISH, statesOfOverlappingNeighborsOnCell);
      if(oldestShark != null) {
        return oldestShark;
      }
      if(oldestFish != null) {
        return oldestFish;
      }
    }
    return nextState;
  }

  private State returnOldestSeaCreature(State targetState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    State oldestSeaCreature = getOldestSeaCreature(statesOfOverlappingNeighborsOnCell, WaTorWorldState.FISH);
    if(oldestSeaCreature !=null) {
      return oldestSeaCreature;
    }
    return null;
  }

  /***
   * Returns null if no shark in map
   * @param neighborPositionToState
   * @return
   */
  private State getOldestSeaCreature (Map<int[], State> neighborPositionToState, State targetState) {
    State oldestSeaCreature = null;
    for(int[] key:neighborPositionToState.keySet()) {
      State currentState = neighborPositionToState.get(key);
      int oldestSeaCreatureAge = ((WaTorWorldState) oldestSeaCreature).getAge();
      if(currentState == targetState && ((WaTorWorldState)currentState).getAge() > oldestSeaCreatureAge) {
        oldestSeaCreature = currentState;
      }
    }
    return oldestSeaCreature;
  }
}
