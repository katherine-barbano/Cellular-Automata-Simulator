package model;

import controller.State;
import java.util.ResourceBundle;

public abstract class EdgePolicy {

  public static final String EDGE_POLICY_EXCEPTION_PROPERTIES = "edgePolicyAddExceptionMessage";
  public static final String MODEL_RESOURCE_PATH = "resources/Model";

  private int centerCellRow;
  private int centerCellColumn;
  private State[][] states;
  private ResourceBundle modelResources;

  protected EdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    this.centerCellRow = centerCellRow;
    this.centerCellColumn = centerCellColumn;
    this.states = allStatesInCSV;
  }

  protected abstract State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor);

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
    int neighborRow = centerCellRow + neighborPosition[0];
    int neighborColumn = centerCellColumn + neighborPosition[1];

    return states[neighborRow][neighborColumn];
  }
}
