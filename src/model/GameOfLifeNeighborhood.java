package model;

import controller.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameOfLifeNeighborhood extends Neighborhood {

  public static final String NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForLiveCellToSurvive";
  public static final String NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForDeadCellToSurvive";

  private Map<Integer, GameOfLifeState> gameOfLifeStateMap;

  GameOfLifeNeighborhood(int centerCellRow, int centerCellColumn, int[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  /***
   * Assumes the number of possible state values given in CSV file are equal to the corresponding
   * number of constants in GameOfLifeState enum.
   */
  @Override
  void createCSVValueToStateMap() {
    gameOfLifeStateMap = new HashMap<>();
    GameOfLifeState possibleStatesInGameOfLife[] = GameOfLifeState.values();
    int stateNumber = 0;
    for(GameOfLifeState state : possibleStatesInGameOfLife) {
      gameOfLifeStateMap.put(stateNumber,state);
      stateNumber++;
    }
  }

  @Override
  int getNextState(State currentState) {
    GameOfLifeState nextState = GameOfLifeState.DEAD;
    int numberOfLivingNeighbors = getNumberOfLivingNeighbors();
    List<Integer> numberLiveNeighborsForLiveCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES);
    List<Integer> numberLiveNeighborsForDeadCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES);

    boolean liveCellSurvives = currentState == GameOfLifeState.ALIVE && numberLiveNeighborsForLiveCellToSurvive.contains(numberOfLivingNeighbors);
    boolean deadCellSurvives = currentState == GameOfLifeState.DEAD && numberLiveNeighborsForDeadCellToSurvive.contains(numberOfLivingNeighbors);

    if(liveCellSurvives || deadCellSurvives) {
      nextState = GameOfLifeState.ALIVE;
    }

    return getIntegerFromState(nextState);
  }

  private int getNumberOfLivingNeighbors() {
    Map<Integer, Integer> adjacentNeighborsToIntegerState = getNeighborPositionToState();
    int numberLivingNeighbors=0;
    for(int neighborPosition:adjacentNeighborsToIntegerState.keySet()) {
      int stateInteger = adjacentNeighborsToIntegerState.get(neighborPosition);
      if(getStateFromInteger(stateInteger) == GameOfLifeState.ALIVE) {
        numberLivingNeighbors++;
      }
    }
    return numberLivingNeighbors;
  }

  /***
   * Returns -1 if current state does not exist in gameOfLifeStateMap.
   * Assumes there are no duplicate values between different keys in gameOfLifeStateMap.
   */
  private int getIntegerFromState(GameOfLifeState currentState) {
    for(int stateInteger:gameOfLifeStateMap.keySet()) {
      if(gameOfLifeStateMap.get(stateInteger) == currentState) {
        return stateInteger;
      }
    }
    return -1;
  }

  private List<Integer> getNumberOfNeighborsFromResources(String numberListName) {
    String numberOfNeighborsString = (String) getModelResources().getObject(numberListName);
    String[] numberOfNeighborsStringArray = numberOfNeighborsString.split(",");
    List<Integer> numberOfNeighborsIntList = new ArrayList<>();
    for(String numberOfNeighbors:numberOfNeighborsStringArray) {
      numberOfNeighborsIntList.add(Integer.parseInt(numberOfNeighbors));
    }
    return numberOfNeighborsIntList;
  }
}
