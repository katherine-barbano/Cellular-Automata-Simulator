package model.edgePolicies;

import controller.State;
import model.EdgePolicy;

public abstract class InfiniteEdgePolicy extends EdgePolicy {

  public InfiniteEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  abstract void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner);

  abstract void handleColumnWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner);

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
    boolean neighborStartsAsCorner = neighborIsCorner(relativePositionOfNeighbor, states);

    if(neighborColumn >= states[0].length || neighborColumn < 0) {
      handleColumnWrapping(states, neighborRow, neighborColumn, positionNeighbor, neighborStartsAsCorner);
    }
    if(neighborRow >= states.length || neighborRow < 0) {
      handleRowWrapping(states, neighborRow, neighborColumn, positionNeighbor, neighborStartsAsCorner);
    }
    return new int[]{positionNeighbor[0], positionNeighbor[1]};
  }

  private boolean neighborIsCorner(int[] relativePositionOfNeighbor, State[][] states) {
    int centerCellRow = getCenterCellRow();
    int centerCellColumn = getCenterCellColumn();
    if(centerCellRow == 0 && centerCellColumn==0) {
      return relativePositionOfNeighbor[0]<0 && relativePositionOfNeighbor[1]<0 && centerCellRow == centerCellColumn;
    }
    else if(centerCellRow == 0 && centerCellColumn==states[0].length-1) {
      return relativePositionOfNeighbor[0]<0 && relativePositionOfNeighbor[1]>0 && centerCellColumn == states[0].length-1;
    }
    else if(centerCellRow == states.length-1 && centerCellColumn==0) {
      return relativePositionOfNeighbor[0]>0 && relativePositionOfNeighbor[1]<0 && centerCellColumn == 0;
    }
    else if(centerCellRow == states.length-1 && centerCellColumn==states[0].length-1){
      return relativePositionOfNeighbor[0]>0 && relativePositionOfNeighbor[1]>0 && centerCellColumn == states[0].length-1;
    }
    return false;
  }
}
