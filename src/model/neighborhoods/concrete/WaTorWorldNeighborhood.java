package model.neighborhoods.concrete;

import controller.State;
import controller.states.MovingState;
import controller.states.MovingStateWithAge;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.ModelException;
import model.Neighborhood;
import model.neighborhoods.InfluentialNeighborhood;

public class WaTorWorldNeighborhood extends InfluentialNeighborhood {

  public static final String MIN_BREED_AGE_PROPERTIES = "WaTorWorld_MinimumBreedingAge";
  public static final String EMPTY_PROPERTIES = "emptyStateName";
  public static final String SHARK_PROPERTIES = "sharkStateName";
  public static final String FISH_PROPERTIES="fishStateName";

  private String emptyStateName = getModelResources().getString(EMPTY_PROPERTIES);
  private String sharkStateName = getModelResources().getString(SHARK_PROPERTIES);
  private String fishStateName = getModelResources().getString(FISH_PROPERTIES);

  public WaTorWorldNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    List<int[]> positionsOfEmptyNeighbors = positionsOfTargetStateNeighbors(new MovingStateWithAge(emptyStateName));
    int minimumBreedingAge = Integer.parseInt(getModelResources().getString(MIN_BREED_AGE_PROPERTIES));
    if(currentState.equals(sharkStateName)) {
      return handleSharkState(currentState, positionsOfEmptyNeighbors, minimumBreedingAge, neighborhoodsOfNeighbors);
    }
    else if(currentState.equals(fishStateName)) {
      return handleFishState(currentState, positionsOfEmptyNeighbors, minimumBreedingAge, neighborhoodsOfNeighbors);
    }
    else {
      return handleEmptyState();
    }
  }

  private List<int[]> positionsOfTargetStateNeighbors(State state) {
    List<int[]> emptyIndices = new ArrayList<>();
    Map<int[], State> neighborPositionToState = getNeighborPositionToState();
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(thisKey).equals(state)) {
        emptyIndices.add(thisKey);
      }
    }
    return emptyIndices;
  }

  private State handleSharkState(State currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    List<int[]> positionsOfFishNeighbors = positionsOfTargetStateNeighbors(new MovingStateWithAge(fishStateName));

    if(positionsOfFishNeighbors.size()>0) {
      return handleEat(currentState, positionsOfEmptyNeighbors, neighborhoodsOfNeighbors);
    }
    else if(((MovingStateWithAge)currentState).getAge()>minimumBreedingAge && positionsOfEmptyNeighbors.size()>0) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfFishNeighbors.size() == 0 && positionsOfEmptyNeighbors.size()>0) {
      return handleMove(currentState, positionsOfEmptyNeighbors, neighborhoodsOfNeighbors);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleFishState(State currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    if(((MovingState)currentState).nextPositionIsStationary()) {
      return handleEaten();
    }
    else if(((MovingStateWithAge)currentState).getAge()>=minimumBreedingAge) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfEmptyNeighbors.size()>0) {
      return handleMove(currentState, positionsOfEmptyNeighbors, neighborhoodsOfNeighbors);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleBreeding(State currentState, List<int[]> positionsOfEmptyNeighbors) {
    int[] positionToBreedInto = ((MovingState)currentState).getOpenPosition(positionsOfEmptyNeighbors);
    replaceEmptyWithNewlyBornSeaCreature(positionToBreedInto, currentState);
    handleAgingAndStationary(currentState);
    return currentState;
  }

  private void replaceEmptyWithNewlyBornSeaCreature(int[] positionToBreedInto, State currentState) {
    //TODO: use reflection here
    State baby = null;
    if(currentState.equals(fishStateName)) {
      baby = new MovingStateWithAge(fishStateName);
    }
    else {
      baby = new MovingStateWithAge(sharkStateName);
    }
    replaceNeighborStateWithNewState(positionToBreedInto,baby);
  }

  private State handleMove(State currentState, List<int[]> positionsOfEmptyNeighbors, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    ageByOne(currentState);
    ((MovingState)currentState).setNextPositionMove(positionsOfEmptyNeighbors);
    int[] positionToMoveInto = ((MovingState)currentState).getNextPosition();
    replaceNeighborStateWithNewState(positionToMoveInto,currentState);
    deleteMovedStateFromNeighborhoodsOfNeighbors(neighborhoodsOfNeighbors, new State(emptyStateName));
    return new State(emptyStateName);
  }

  private State handleEat(State currentState, List<int[]> positionsOfEmptyNeighbors, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    handleAgingAndStationary(currentState);
    State empty = new State(emptyStateName);
    int[] openPosition = ((MovingState)currentState).getOpenPosition(positionsOfEmptyNeighbors);
    Neighborhood neighborhoodOfEaten = findEatenInNeighborhood(openPosition, neighborhoodsOfNeighbors);
    replaceNeighborStateWithNewState(openPosition,empty);
    ((InfluentialNeighborhood)neighborhoodOfEaten).deleteMovedStateFromNeighborhoodsOfNeighbors(neighborhoodsOfNeighbors, new State(emptyStateName));
    return currentState;
  }

  private Neighborhood findEatenInNeighborhood(int[] openPosition, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    for(int[] position:neighborhoodsOfNeighbors.keySet()) {
      if(position[0] == openPosition[0] && position[1] == openPosition[1]) {
        return neighborhoodsOfNeighbors.get(position);
      }
    }
    throw new ModelException("Eaten not found");
  }

  private State handleEaten() {
    return handleEmptyState();
  }

  private State handleAgingAndStationary(State currentState) {
    ageByOne(currentState);
    ((MovingState)currentState).setNextPositionStationary();
    return currentState;
  }

  private void ageByOne(State currentState) {
    int currentAge = ((MovingStateWithAge)currentState).getAge();
    ((MovingStateWithAge)currentState).setAge(currentAge + 1);
  }

  private State handleEmptyState() {
    return new State(emptyStateName);
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
    if(nextState.equals(emptyStateName)) {
      State oldestShark = returnOldestSeaCreature(new MovingStateWithAge(sharkStateName), statesOfOverlappingNeighborsOnCell);
      State oldestFish = returnOldestSeaCreature(new MovingStateWithAge(fishStateName), statesOfOverlappingNeighborsOnCell);
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
    State oldestSeaCreature = getOldestSeaCreature(statesOfOverlappingNeighborsOnCell, targetState);
    if(oldestSeaCreature !=null) {
      return oldestSeaCreature;
    }
    return null;
  }

  /***
   * Returns null if no targetState in map
   * @param neighborPositionToState
   * @return
   */
  private State getOldestSeaCreature (Map<int[], State> neighborPositionToState, State targetState) {
    State oldestSeaCreature = targetState;
    int greatestAge = -1;
    for(int[] key:neighborPositionToState.keySet()) {
      State currentState = neighborPositionToState.get(key);
      if(currentState.toString().equals(targetState.toString()) && ((MovingStateWithAge)currentState).getAge() > greatestAge) {
        oldestSeaCreature = currentState;
        greatestAge = ((MovingStateWithAge) currentState).getAge();
      }
    }
    if(greatestAge==-1) {
      return null;
    }
    return oldestSeaCreature;
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentNeighborsOnly(centerCellRow, centerCellColumn, allStatesInCSV);
  }
}
