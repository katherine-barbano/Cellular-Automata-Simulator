package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.WaTorWorldState;
import java.util.List;
import java.util.Map;
import model.ModelException;
import model.NeighborPolicy;
import model.Neighborhood;
import model.neighborhoods.InfluentialNeighborhood;

/***
 * Neighborhood for WaTorWorld simulation
 *
 * @author Katherine Barbano
 */
public class WaTorWorldNeighborhood extends InfluentialNeighborhood {

  public static final String MIN_BREED_AGE_PROPERTIES = "WaTorWorld_MinimumBreedingAge";
  public static final String SEA_CREATURE_EXCEPTION_MESSAGE = "oldestSeaCreatureNotFoundExceptionMessage";

  /***
   * Constructor overrides Neighborhood constructor
   * @param neighborPolicy NeighborPolicy object
   */
  public WaTorWorldNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  /***
   * Gets the next state of the center cell for this type of simulation
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center cell for the next grid
   */
  @Override
  public State getNextState(State currentState) {
    List<int[]> positionsOfEmptyNeighbors = positionsOfTargetStateNeighbors(new State(WaTorWorldState.EMPTY));
    int minimumBreedingAge = Integer.parseInt(getModelResources().getString(MIN_BREED_AGE_PROPERTIES));
    if(currentState.equalsState(WaTorWorldState.SHARK)) {
      return handleSharkState(currentState, positionsOfEmptyNeighbors, minimumBreedingAge);
    }
    else if(currentState.equalsState(WaTorWorldState.FISH)) {
      return handleFishState(currentState, positionsOfEmptyNeighbors, minimumBreedingAge);
    }
    else {
      return handleEmptyState();
    }
  }

  private State handleSharkState(State currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    List<int[]> positionsOfFishNeighbors = positionsOfTargetStateNeighbors(new State(WaTorWorldState.FISH));

    if(!positionsOfFishNeighbors.isEmpty()) {
      return handleEat(currentState, positionsOfFishNeighbors);
    }
    else if(currentState.getAge()>=minimumBreedingAge && !positionsOfEmptyNeighbors.isEmpty()) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(positionsOfFishNeighbors.isEmpty() && !positionsOfEmptyNeighbors.isEmpty()) {
      return handleMove(currentState);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleFishState(State currentState, List<int[]> positionsOfEmptyNeighbors, int minimumBreedingAge) {
    //TODO: bug here preventing fish from getting eaten
    /*if(currentState.nextPositionIsStationary()) {
      return handleEaten();
    }*/
    if(currentState.getAge()>=minimumBreedingAge) {
      return handleBreeding(currentState, positionsOfEmptyNeighbors);
    }
    else if(!positionsOfEmptyNeighbors.isEmpty()) {
      return handleMove(currentState);
    }
    else {
      return handleAgingAndStationary(currentState);
    }
  }

  private State handleBreeding(State currentState, List<int[]> positionsOfEmptyNeighbors) {
    int[] positionToBreedInto = currentState.getOpenPosition(positionsOfEmptyNeighbors);
    if(positionToBreedInto != null) {
      replaceEmptyWithNewlyBornSeaCreature(positionToBreedInto, currentState);
      handleAgingAndStationary(currentState);
    }
    return currentState;
  }

  private void replaceEmptyWithNewlyBornSeaCreature(int[] positionToBreedInto, State currentState) {
    //TODO: use reflection here
    if(currentState.equalsState(WaTorWorldState.FISH)) {
      State baby = new State(WaTorWorldState.FISH);
      replaceNeighborStateWithNewState(positionToBreedInto,baby);
    }
    else {
      State baby = new State(WaTorWorldState.SHARK);
      replaceNeighborStateWithNewState(positionToBreedInto,baby);
    }
  }

  private State handleMove(State currentState) {
    ageByOne(currentState);
    return handleMoveToNeighbor(currentState, new State(WaTorWorldState.EMPTY));
  }

  private State handleEat(State currentState, List<int[]> positionsOfFishNeighbors) {
    handleAgingAndStationary(currentState);
    State empty = new State(WaTorWorldState.EMPTY);
    int[] openPosition = currentState.getOpenPosition(positionsOfFishNeighbors);
    Neighborhood neighborhoodOfEaten = findPositionInNeighborhoodOfNeighbors(openPosition);
    replaceNeighborStateWithNewState(openPosition,empty);
    ((InfluentialNeighborhood)neighborhoodOfEaten).deleteMovedStateFromNeighborhoodsOfNeighbors(new State(WaTorWorldState.EMPTY));
    return currentState;
  }

  /*
  //associated with bug listed as TODO
  private State handleEaten() {
    return handleEmptyState();
  }
  */

  private State handleAgingAndStationary(State currentState) {
    ageByOne(currentState);
    currentState.setNextPositionStationary();
    return currentState;
  }

  private void ageByOne(State currentState) {
    int currentAge = currentState.getAge();
    currentState.setAge(currentAge + 1);
  }

  private State handleEmptyState() {
    return new State(WaTorWorldState.EMPTY);
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
    try {
      if(nextState.equalsState(WaTorWorldState.EMPTY)) {
        return returnOldestSharkOrFish(statesOfOverlappingNeighborsOnCell);
      }
      else {
        return nextState;
      }
    }
    catch(ModelException e) {
      return nextState;
    }
  }

  private State returnOldestSharkOrFish(Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    try {
      return getOldestSeaCreature(statesOfOverlappingNeighborsOnCell, new State(WaTorWorldState.SHARK));
    }
    catch(ModelException e) {
      return getOldestSeaCreature(statesOfOverlappingNeighborsOnCell, new State(WaTorWorldState.FISH));
    }
  }

  private State getOldestSeaCreature (Map<int[], State> neighborPositionToState, State targetState) {
    State oldestSeaCreature = targetState;
    int greatestAge = -1;
    for(int[] key:neighborPositionToState.keySet()) {
      State currentState = neighborPositionToState.get(key);
      if(currentState.equalsState(targetState) && currentState.getAge() > greatestAge) {
        oldestSeaCreature = currentState;
        greatestAge = currentState.getAge();
      }
    }
    if(greatestAge==-1) {
      String errorMessage = getModelResources().getString(SEA_CREATURE_EXCEPTION_MESSAGE);
      throw new ModelException(errorMessage);
    }
    return oldestSeaCreature;
  }

}
