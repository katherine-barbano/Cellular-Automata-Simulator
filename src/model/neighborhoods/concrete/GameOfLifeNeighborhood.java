package model.neighborhoods.concrete;

import controller.GameOfLifeState;
import controller.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class GameOfLifeNeighborhood extends NonInfluentialNeighborhood {

  public static final String NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForLiveCellToSurvive";
  public static final String NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForDeadCellToSurvive";

  public GameOfLifeNeighborhood(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    GameOfLifeState nextState = GameOfLifeState.DEAD;
    int numberOfLivingNeighbors = getNumberOfLivingNeighbors();
    List<Integer> numberLiveNeighborsForLiveCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES);
    List<Integer> numberLiveNeighborsForDeadCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES);

    boolean liveCellSurvives = currentState == GameOfLifeState.ALIVE && numberLiveNeighborsForLiveCellToSurvive.contains(numberOfLivingNeighbors);
    boolean deadCellSurvives = currentState == GameOfLifeState.DEAD && numberLiveNeighborsForDeadCellToSurvive.contains(numberOfLivingNeighbors);

    if(liveCellSurvives || deadCellSurvives) {
      nextState = GameOfLifeState.ALIVE;
    }

    return nextState;
  }

  private int getNumberOfLivingNeighbors() {
    Map<int[], State> adjacentNeighborsToState = getNeighborPositionToState();
    int numberLivingNeighbors=0;
    for(int[] neighborPosition:adjacentNeighborsToState.keySet()) {
      State state = adjacentNeighborsToState.get(neighborPosition);
      if(state == GameOfLifeState.ALIVE) {
        numberLivingNeighbors++;
      }
    }
    return numberLivingNeighbors;
  }

  private List<Integer> getNumberOfNeighborsFromResources(String numberListName) {
    String numberOfNeighborsString = getModelResources().getString(numberListName);
    String[] numberOfNeighborsStringArray = numberOfNeighborsString.split(",");
    List<Integer> numberOfNeighborsIntList = new ArrayList<>();
    for(String numberOfNeighbors:numberOfNeighborsStringArray) {
      numberOfNeighborsIntList.add(Integer.parseInt(numberOfNeighbors));
    }
    return numberOfNeighborsIntList;
  }
}
