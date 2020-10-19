package model;

import controller.State;
import java.util.ResourceBundle;

public abstract class EdgePolicy {

  public static final String EDGE_POLICY_EXCEPTION_PROPERTIES = "edgePolicyAddExceptionMessage";
  public static final String MODEL_RESOURCE_PATH = "resources/Model";

  private final int centerCellRow;
  private final int centerCellColumn;
  private final State[][] states;
  private final ResourceBundle modelResources;

  protected EdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    this.centerCellRow = centerCellRow;
    this.centerCellColumn = centerCellColumn;
    this.states = allStatesInCSV;
  }

  protected abstract State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor);

  public abstract int[] getPositionOfNeighbor(int[] relativePositionOfNeighbor);

  protected int getCenterCellRow() {
    return centerCellRow;
  }

  protected int getCenterCellColumn() {
    return centerCellColumn;
  }

  protected State[][] getStates() {
    return states;
  }

  protected ResourceBundle getModelResources() {
    return modelResources;
  }

  protected State getNeighborStateFromAdjacentPosition(int[] neighborPosition) throws IndexOutOfBoundsException{
    int[] nonRelativePositions = getNonRelativePositions(neighborPosition);
    return states[nonRelativePositions[0]][nonRelativePositions[1]];
  }

  protected int[] getNonRelativePositions(int[] relativePosition) {
    int neighborRow = centerCellRow + relativePosition[0];
    int neighborColumn = centerCellColumn + relativePosition[1];
    return new int[]{neighborRow, neighborColumn};
  }
}
