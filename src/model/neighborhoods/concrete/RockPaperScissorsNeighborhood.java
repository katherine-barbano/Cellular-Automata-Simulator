package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.RockPaperScissorsState;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

public class RockPaperScissorsNeighborhood extends NonInfluentialNeighborhood {

  public static final String THRESHOLD_TO_LOSE_PROPERTIES = "RockPaperScissors_thresholdToLose";

  public RockPaperScissorsNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
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
