package model.edgePolicies;

import controller.State;
import model.EdgePolicy;

/***
 * Abstraction for an edge policy that uses some type of wrapping, meaning there are no
 * ends to the Grid (used by KleinBottleEdgePolicy and ToroidalEdgePolicy). Contains abstractions
 * for how to wrap rows and wrap columns.
 *
 * @author Katherine Barbano
 */
public abstract class InfiniteEdgePolicy extends EdgePolicy {

  public InfiniteEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  /***
   * Modifies positionNeighbor to the correctly wrapped position if it is off the edge of the row.
   * @param states 2D state array
   * @param neighborRow actual position of row in Grid
   * @param neighborColumn actual position of column in Grid
   * @param positionNeighbor relative position of neighbor in neighborhood
   * @param neighborStartsAsCorner true if the neighbor is in the corner of the Grid
   */
  abstract void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner);

  /***
   * Modifies positionNeighbor to the correctly wrapped position if it is off the edge of the column.
   * @param states 2D state array
   * @param neighborRow actual position of row in Grid
   * @param neighborColumn actual position of column in Grid
   * @param positionNeighbor relative position of neighbor in neighborhood
   * @param neighborStartsAsCorner true if the neighbor is in the corner of the Grid
   */
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

  /***
   * Returns the actual position of the neighbor cell in the Grid from a relative position. Uses
   * wrapping to return the correct position.
   * @param relativePositionOfNeighbor int[] row, column of relative position of neighbor cell
   * @return
   */
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
      return relativePositionOfNeighbor[0]<0 && relativePositionOfNeighbor[1]<0;
    }
    else if(centerCellRow == 0 && centerCellColumn==states[0].length-1) {
      return relativePositionOfNeighbor[0]<0 && relativePositionOfNeighbor[1]>0 && centerCellColumn == states[0].length-1;
    }
    else if(centerCellRow == states.length-1 && centerCellColumn==0) {
      return relativePositionOfNeighbor[0]>0 && relativePositionOfNeighbor[1]<0;
    }
    else if(centerCellRow == states.length-1 && centerCellColumn==states[0].length-1){
      return relativePositionOfNeighbor[0]>0 && relativePositionOfNeighbor[1]>0 && centerCellColumn == states[0].length-1;
    }
    return false;
  }
}
