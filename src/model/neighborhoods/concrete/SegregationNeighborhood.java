package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.SegregationState;
import java.util.List;
import java.util.Map;
import model.NeighborPolicy;
import model.Neighborhood;
import model.neighborhoods.InfluentialNeighborhood;

public class SegregationNeighborhood extends InfluentialNeighborhood {

  public static final String DEFAULT_THRESHOLD_TO_MOVE_PROPERTIES = "Segregation_thresholdToMoveDefault";

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
    double percentSameNeighbors = getNumberOfNeighborsWithGivenState(currentState)/getNumberOfNeighbors();
    boolean isSatisfied = thresholdToMove <= percentSameNeighbors;
    if(currentState.equals(SegregationState.EMPTY)) {
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
    if(nextState.equals(SegregationState.EMPTY)) {
      State agentO = getAgentFromOverlappingNeighbors(new State(SegregationState.OAGENT), statesOfOverlappingNeighborsOnCell);
      State agentX = getAgentFromOverlappingNeighbors(new State(SegregationState.XAGENT), statesOfOverlappingNeighborsOnCell);
      if(agentX!=null) {
        return agentX;
      }
      else if(agentO!=null){
        return agentO;
      }
    }
    return nextState;
  }

  private State getAgentFromOverlappingNeighbors(State targetState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    for(int[] position:statesOfOverlappingNeighborsOnCell.keySet()) {
      State currentState = statesOfOverlappingNeighborsOnCell.get(position);
      if(currentState.equals(targetState)) {
        return currentState;
      }
    }
    return null;
  }
}
