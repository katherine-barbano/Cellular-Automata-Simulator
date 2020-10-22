package model;

import controller.State;
import java.util.ResourceBundle;

/***
 * Class that defines how the edges of the Grid are dealt with.
 *
 * Contains abstractions for how a neighbor should put a cell into NeighborPolicy's
 * neighborPositionToState map in order to deal with the Grid's edges, and another abstraction
 * to get the actual position of a neighbor in the Grid, since different EdgePolicies might
 * implement edge wrapping differently or not at all.
 *
 * @author Katherine Barbano
 */
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

  /***
   * Called by NeighborPolicy during the initialization of neighborPositionToState. Defines
   * which State should be put into neighborPositionToState based on the relative position
   * of the neighbor. This could differ for example, if the relative position of the neighbor is
   * actually off the edge of the Grid. Some EdgePolicies might chose to wrap the Grid to make
   * this relative position correspond to a cell on the opposite side of the Grid. Some might choose
   * to not add this case to the map at all. Because of this, this method is abstract. In future implementations,
   * there might even be different ways to deal with the edges of cells on the interior of the Grid as well.
   * @param relativePositionOfNeighbor int[] row, column of relative position of neighbor cell
   * @return State object that should go in that cell
   */
  protected abstract State getNeighborStateFromPositionForInitialization(int[] relativePositionOfNeighbor);

  /***
   * Returns the actual position of the neighbor cell in the Grid from a relative position.
   * This could differ for example, if the relative position of the neighbor is
   * actually off the edge of the Grid. Some EdgePolicies might chose to wrap the Grid to make
   * this relative position correspond to a cell on the opposite side of the Grid. Some might choose
   * to not add this case to the map at all. Because of this, this method is abstract. In future implementations,
   * there might even be different ways to deal with the edges of cells on the interior of the Grid as well.
   * @param relativePositionOfNeighbor int[] row, column of relative position of neighbor cell
   * @return int[] actual position of cell in Grid
   */
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
