package model.edgePolicies;

import controller.State;
import model.EdgePolicy;

public class ToroidalEdgePolicy extends EdgePolicy {

  public static final String COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES = "neighborPositionCoordinateSize";

  public ToroidalEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  protected State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor) {
    try {
      return getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor);
    }
    catch(IndexOutOfBoundsException e) {
      return handleOutOfBoundsIndex(relativePositionOfNeighbor);
    }
  }

  //If index is out of bounds, this means the center cell is on the edge, and the neighbor should be taken from the opposite side of the Grid
  private State handleOutOfBoundsIndex(int[] relativePositionOfNeighbor) {
    State[][] states = getStates();
    int neighborRow = getCenterCellRow() + relativePositionOfNeighbor[0];
    int neighborColumn = getCenterCellColumn() + relativePositionOfNeighbor[1];
    int positionDimensions = Integer.parseInt(getModelResources().getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
    int[] newRelativePositionNeighbor = new int[positionDimensions];
    if(neighborRow >= states.length || neighborRow < 0) {
      handleRowWrapping(states, neighborRow, neighborColumn, newRelativePositionNeighbor);
    }
    else {
      handleColumnWrapping(states, neighborRow, neighborColumn, newRelativePositionNeighbor);
    }
    return getNeighborStateFromAdjacentPosition(newRelativePositionNeighbor);
  }

  private void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] newRelativePositionNeighbor) {
    if(neighborRow < 0) {
      newRelativePositionNeighbor[0] = states.length-1;
    }
    else {
      newRelativePositionNeighbor[0] = 0;
    }
    newRelativePositionNeighbor[1] = neighborColumn;
  }

  private void handleColumnWrapping(State[][] states, int neighborRow, int neighborColumn, int[] newRelativePositionNeighbor) {
    if(neighborColumn < 0) {
      newRelativePositionNeighbor[1] = states[0].length-1;
    }
    else {
      newRelativePositionNeighbor[1] = 0;
    }
    newRelativePositionNeighbor[0] = neighborRow;
  }
}
