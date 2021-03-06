package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.RockPaperScissorsState;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

/***
 * Neighborhood for RockPaperScissors simulation
 *
 * @author Katherine Barbano
 */
public class RockPaperScissorsNeighborhood extends NonInfluentialNeighborhood {

  public static final String THRESHOLD_TO_LOSE_PROPERTIES = "RockPaperScissors_thresholdToLose";

  /***
   * Constructor overrides Neighborhood constructor
   * @param neighborPolicy NeighborPolicy object
   */
  public RockPaperScissorsNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  /***
   * Gets the next state of the center cell for this type of simulation
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center cell for the next grid
   */
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
    if(currentState.equalsState(RockPaperScissorsState.ROCK)) {
      return new State(RockPaperScissorsState.PAPER);
    }
    else if(currentState.equalsState(RockPaperScissorsState.PAPER)) {
      return new State(RockPaperScissorsState.SCISSORS);
    }
    return new State(RockPaperScissorsState.ROCK);
  }

}
