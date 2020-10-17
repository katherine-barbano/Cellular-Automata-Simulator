package model.neighborhoods;

import controller.State;
import controller.states.MovingState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.NeighborPolicy;
import model.Neighborhood;

public abstract class InfluentialNeighborhood extends Neighborhood {

  public InfluentialNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  protected State handleMoveToNeighbor(State currentState, State neighborState) {
    List<int[]> positionsOfEmptyNeighbors = positionsOfTargetStateNeighbors(neighborState);
    ((MovingState)currentState).setNextPositionMove(positionsOfEmptyNeighbors);
    int[] positionToMoveInto = ((MovingState)currentState).getNextPosition();
    replaceNeighborStateWithNewState(positionToMoveInto,currentState);
    deleteMovedStateFromNeighborhoodsOfNeighbors(neighborState);
    return new State(neighborState.getStateType());
  }

  public void deleteMovedStateFromNeighborhoodsOfNeighbors(State newState) {
    for(int[] neighborPosition : getNeighborhoodsOfNeighbors().keySet()) {
      int[] positionOfCenterCellInNeighbor = negateArray(neighborPosition);
      Neighborhood neighborhoodOfNeighbor = getNeighborhoodsOfNeighbors().get(neighborPosition);
      neighborhoodOfNeighbor.replaceNeighborStateWithNewState(positionOfCenterCellInNeighbor,newState);
    }
  }

  private int[] negateArray(int[] array) {
    int[] newArray = new int[array.length];
    for(int index = 0; index<array.length; index++) {
      newArray[index] = array[index]*(-1);
    }
    return newArray;
  }
}
