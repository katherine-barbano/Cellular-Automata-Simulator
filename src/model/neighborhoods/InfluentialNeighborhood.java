package model.neighborhoods;

import controller.State;
import controller.states.MovingState;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Neighborhood;

public abstract class InfluentialNeighborhood extends Neighborhood {

  public InfluentialNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  public State handleMoveToNeighbor(State currentState, State neighborState) {
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

   public List<int[]> positionsOfTargetStateNeighbors(State state) {
    List<int[]> emptyIndices = new ArrayList<>();
    Map<int[], State> neighborPositionToState = getNeighborPositionToState();
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(thisKey).equals(state)) {
        emptyIndices.add(thisKey);
      }
    }
    return emptyIndices;
  }
}
