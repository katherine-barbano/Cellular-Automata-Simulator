package model.edgePolicies;

import controller.State;
import model.EdgePolicy;

public abstract class InfiniteEdgePolicy extends EdgePolicy {

  public InfiniteEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  abstract void handleRowWrapping(State[][] states, int neighborRow, int[] positionNeighbor);

  abstract void handleColumnWrapping(State[][] states, int neighborColumn, int[] positionNeighbor);

  @Override
  protected State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor) {
    try {
      return getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor);
    }
    catch(IndexOutOfBoundsException e) {
      int[] position = getPositionOfOutOfBoundsNeighbor(relativePositionOfNeighbor);
      return getStates()[position[0]][position[1]];
    }
  }

  @Override
  public int[] getPositionOfNeighbor(int[] relativePositionOfNeighbor) {
    try {
      getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor);
      return getNonRelativePositions(relativePositionOfNeighbor);
    }
    catch(IndexOutOfBoundsException e) {
      return getPositionOfOutOfBoundsNeighbor(relativePositionOfNeighbor);
    }
  }

  private int[] getPositionOfOutOfBoundsNeighbor(int[] relativePositionOfNeighbor) {
    State[][] states = getStates();
    int[] positionNeighbor = getNonRelativePositions(relativePositionOfNeighbor);
    int neighborRow = positionNeighbor[0];
    int neighborColumn = positionNeighbor[1];

    if(neighborRow >= states.length || neighborRow < 0) {
      handleRowWrapping(states, neighborRow, positionNeighbor);
    }
    if(neighborColumn >= states[0].length || neighborColumn < 0) {
      handleColumnWrapping(states, neighborColumn, positionNeighbor);
    }
    return new int[]{positionNeighbor[0], positionNeighbor[1]};
  }
}
