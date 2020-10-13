package model.neighborhoods.concrete;

import controller.states.GameOfLifeState;
import controller.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.neighborhoods.NonInfluentialNeighborhood;

public class GameOfLifeNeighborhood extends NonInfluentialNeighborhood {

  public static final String NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForLiveCellToSurvive";
  public static final String NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForDeadCellToSurvive";
  public static final String COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES = "neighborPositionCoordinateSize";

  public GameOfLifeNeighborhood(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    Map<int[], State> neighborPositionToState = new HashMap<>();

    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(!(row==0 && column==0)) {
          int coordinateDimensions = Integer
              .parseInt(getModelResources().getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
          int[] relativePositionOfNeighbor = new int[coordinateDimensions];
          relativePositionOfNeighbor[0] = row;
          relativePositionOfNeighbor[1] = column;
          putNeighborPositionIntoMap(relativePositionOfNeighbor, neighborPositionToState,
              centerCellRow, centerCellColumn, allStatesInCSV);
        }
      }
    }
    return neighborPositionToState;
  }

  private void putNeighborPositionIntoMap(int[] relativePositionOfNeighbor, Map<int[], State> neighborPositionToState, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    try {
      State neighborState = getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor, centerCellRow, centerCellColumn, allStatesInCSV);
      neighborPositionToState.put(relativePositionOfNeighbor,neighborState);
    }
    catch(IndexOutOfBoundsException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
  }

  @Override
  public State getNextState(State currentState) {
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
