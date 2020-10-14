package model.neighborhoods.concrete;

import controller.State;
import controller.states.MovingState;
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

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    return null;
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
      else {
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
