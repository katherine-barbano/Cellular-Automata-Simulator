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

  //If index is out of bounds, this means the center cell is on the edge, and the neighbor should be taken from the opposite side of the Grid
  private int[] getPositionOfOutOfBoundsNeighbor(int[] relativePositionOfNeighbor) {
    State[][] states = getStates();
    int[] positionNeighbor = getNonRelativePositions(relativePositionOfNeighbor);
    int neighborRow = positionNeighbor[0];
    int neighborColumn = positionNeighbor[1];

    if(neighborRow >= states.length || neighborRow < 0) {
      handleRowWrapping(states, neighborRow, neighborColumn, positionNeighbor);
    }
    if(neighborColumn >= states[0].length || neighborColumn < 0) {
      handleColumnWrapping(states, neighborRow, neighborColumn, positionNeighbor);
    }
    return new int[]{positionNeighbor[0], positionNeighbor[1]};
  }

  private void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor) {
    if(neighborRow < 0) {
      positionNeighbor[0] = states.length-1;
    }
    else {
      positionNeighbor[0] = 0;
    }
  }

  private void handleColumnWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor) {
    if(neighborColumn < 0) {
      positionNeighbor[1] = states[0].length-1;
    }
    else {
      positionNeighbor[1] = 0;
    }
  }
}
