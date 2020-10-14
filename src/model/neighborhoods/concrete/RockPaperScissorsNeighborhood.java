package model.neighborhoods.concrete;

import controller.State;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class RockPaperScissorsNeighborhood extends NonInfluentialNeighborhood {

  public static final String THRESHOLD_TO_LOSE_PROPERTIES = "RockPaperScissors_thresholdToLose";
  public static final String ROCK_PROPERTIES="rockStateName";
  public static final String PAPER_PROPERTIES="paperStateName";
  public static final String SCISSORS_PROPERTIES="scissorsStateName";

  private String rockStateName = getModelResources().getString(ROCK_PROPERTIES);
  private String paperStateName = getModelResources().getString(PAPER_PROPERTIES);
  private String scissorsStateName = getModelResources().getString(SCISSORS_PROPERTIES);

  public RockPaperScissorsNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentAndDiagonal(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    State stateThatBeatsCurrentState = stateThatBeatsCurrentState(currentState);
    int numberOfNeighborsThatBeatCurrentState = getNumberOfNeighborsWithGivenState(stateThatBeatsCurrentState);
    int thresholdValue = Integer.parseInt(getModelResources().getString(THRESHOLD_TO_LOSE_PROPERTIES));
    if(numberOfNeighborsThatBeatCurrentState >= thresholdValue) {
      return stateThatBeatsCurrentState;
    }
    return currentState;
  }

  private State stateThatBeatsCurrentState(State currentState) {
    if(currentState.equals(rockStateName)) {
      return new State(paperStateName);
    }
    else if(currentState.equals(paperStateName)) {
      return new State(scissorsStateName);
    }
    return new State(rockStateName);
  }

}
