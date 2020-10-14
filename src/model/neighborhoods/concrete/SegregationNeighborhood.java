package model.neighborhoods.concrete;

import controller.State;
import controller.states.SegregationState;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.InfluentialNeighborhood;

public class SegregationNeighborhood extends InfluentialNeighborhood {

  public static final String THRESHOLD_TO_MOVE_PROPERTIES = "Segregation_thresholdToMove";

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
    if(nextState == SegregationState.EMPTY) {

    }
    return nextState;
  }
}
