package model.edgePolicies;

import controller.State;
import model.EdgePolicy;
import model.ModelException;

public class FiniteEdgePolicy extends EdgePolicy {

  public FiniteEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  protected State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor) {
    try {
      return getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor);
    }
    catch(IndexOutOfBoundsException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist
      String edgePolicyAddExceptionMessage = getModelResources().getString(EdgePolicy.EDGE_POLICY_EXCEPTION_PROPERTIES);
      throw new ModelException(edgePolicyAddExceptionMessage);
    }
  }
}
