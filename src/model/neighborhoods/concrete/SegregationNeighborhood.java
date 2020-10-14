package model.neighborhoods.concrete;

import controller.State;
import controller.states.MovingState;
import java.util.List;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.InfluentialNeighborhood;

public class SegregationNeighborhood extends InfluentialNeighborhood {

  public static final String THRESHOLD_TO_MOVE_PROPERTIES = "Segregation_thresholdToMove";
  private final String EMPTY_PROPERTIES="emptyStateName";
  public static final String XAGENT_PROPERTIES="xAgentStateName";
  public static final String OAGENT_PROPERTIES="oAgentStateName";

  private String xAgentStateName = getModelResources().getString(XAGENT_PROPERTIES);
  private String oAgentStateName = getModelResources().getString(OAGENT_PROPERTIES);
  private String emptyStateName = getModelResources().getString(EMPTY_PROPERTIES);

  private double thresholdToMove;

  public SegregationNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
    thresholdToMove = Double.parseDouble(getModelResources().getString(THRESHOLD_TO_MOVE_PROPERTIES));
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentAndDiagonal(centerCellRow, centerCellColumn, allStatesInCSV);
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
    if(currentState.equals(emptyStateName)) {
      return currentState;
    }
    else if(isSatisfied){
      return currentState;
    }
    else {
      return handleMoveToNeighbor(currentState, new State(emptyStateName));
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
    if(nextState.equals(emptyStateName)) {
      State agentO = getAgentFromOverlappingNeighbors(new MovingState(oAgentStateName), statesOfOverlappingNeighborsOnCell);
      State agentX = getAgentFromOverlappingNeighbors(new MovingState(xAgentStateName), statesOfOverlappingNeighborsOnCell);
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
