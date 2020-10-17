package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.RockPaperScissorsState;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class RockPaperScissorsNeighborhood extends NonInfluentialNeighborhood {

  public static final String THRESHOLD_TO_LOSE_PROPERTIES = "RockPaperScissors_thresholdToLose";

  public RockPaperScissorsNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentAndDiagonal(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState) {
    State stateThatBeatsCurrentState = stateThatBeatsCurrentState(currentState);
    int numberOfNeighborsThatBeatCurrentState = getNumberOfNeighborsWithGivenState(stateThatBeatsCurrentState);
    int thresholdValue = Integer.parseInt(getModelResources().getString(THRESHOLD_TO_LOSE_PROPERTIES));
    if(numberOfNeighborsThatBeatCurrentState >= thresholdValue) {
      return stateThatBeatsCurrentState;
    }
    return currentState;
  }

  private State stateThatBeatsCurrentState(State currentState) {
    if(currentState.equals(RockPaperScissorsState.ROCK)) {
      return new State(RockPaperScissorsState.PAPER);
    }
    else if(currentState.equals(RockPaperScissorsState.PAPER)) {
      return new State(RockPaperScissorsState.SCISSORS);
    }
    return new State(RockPaperScissorsState.ROCK);
  }

}
