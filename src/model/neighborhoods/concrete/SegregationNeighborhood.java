package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.SegregationState;
import java.util.Map;
import model.ModelException;
import model.NeighborPolicy;
import model.neighborhoods.InfluentialNeighborhood;

public class SegregationNeighborhood extends InfluentialNeighborhood {

  public static final String DEFAULT_THRESHOLD_TO_MOVE_PROPERTIES = "Segregation_thresholdToMoveDefault";
  public static final String AGENT_NOT_FOUND_EXCEPTION_MESSAGE = "agentNotFoundExceptionMessage";

  private double thresholdToMove;

  public SegregationNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
    thresholdToMove = Double.parseDouble(getModelResources().getString(DEFAULT_THRESHOLD_TO_MOVE_PROPERTIES));
  }

  public SegregationNeighborhood(NeighborPolicy neighborPolicy, double optionalProbability) {
    this(neighborPolicy);
    if(optionalProbability != 0.0) {
      thresholdToMove = optionalProbability;
    }
  }

  /***
   * An unsatisfied agent will move into one of its empty neighbors.
   * Agents were modeled after people, so if an agent is completely surrounded, it cannot move
   * regardless of whether it is satisfied or not.
   * @param currentState
   * @return
   */
  @Override
  public State getNextState(State currentState) {
    double percentSameNeighbors = getNumberOfNeighborsWithGivenState(currentState)/(double)getNumberOfNeighbors();
    boolean isSatisfied = thresholdToMove <= percentSameNeighbors;
    if(currentState.equalsState(SegregationState.EMPTY)) {
      return currentState;
    }
    else if(isSatisfied){
      return currentState;
    }
    else {
      return handleMoveToNeighbor(currentState, new State(SegregationState.EMPTY));
    }
  }

  /***
   * If multiple X's or O's try to move into the same spot, they will fight to try to take the
   * spot and kill the other. An O will always win against an X (since it is alphabetically first).
   * @param nextState
   * @param statesOfOverlappingNeighborsOnCell
   * @return
   */
  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    try {
      if(nextState.equalsState(SegregationState.EMPTY)) {
        return returnAgentFromOverlappingNeighbors(statesOfOverlappingNeighborsOnCell);
      }
      else {
        return nextState;
      }
    }
    catch(ModelException e) {
      return nextState;
    }
  }

  private State returnAgentFromOverlappingNeighbors(Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    try {
      return getAgentFromOverlappingNeighbors(new State(SegregationState.OAGENT), statesOfOverlappingNeighborsOnCell);
    }
    catch(ModelException e) {
      return getAgentFromOverlappingNeighbors(new State(SegregationState.XAGENT), statesOfOverlappingNeighborsOnCell);
    }
  }

  private State getAgentFromOverlappingNeighbors(State targetState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    for(int[] position:statesOfOverlappingNeighborsOnCell.keySet()) {
      State currentState = statesOfOverlappingNeighborsOnCell.get(position);
      if(currentState.equalsState(targetState)) {
        return currentState;
      }
    }
    String errorMessage = getModelResources().getString(AGENT_NOT_FOUND_EXCEPTION_MESSAGE);
    throw new ModelException(errorMessage);
  }
}
