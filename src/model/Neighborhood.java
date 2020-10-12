package model;

import controller.State;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/***
 * States represented as the integers from csv file.
 */
public abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model";

  private Map<int[], State> neighborPositionToState;
  private ResourceBundle modelResources;

  public Neighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborPositionToState = createNeighborMap(centerCellRow, centerCellColumn, stateGrid);
  }

  public abstract State getNextState(State currentState);

  public abstract State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell);

  /***
   * Creates a map with keys as neighbor position relative to the center cell, and values
   * as the state value as an integer. Neighbor position is numbered from 0 to 7, in the format
   * of the picture in doc/relativePositionOfNeighbors.JPG. If a neighbor does not exist (i.e. center cell is on
   * outer border of grid), Map simply does not contain a key with that neighbor position number.
   * @param allStatesInCSV 2D int array of all the states in the CSV file
   * @param centerCellRow Starting with index 0, row number of center cell
   * @param centerCellColumn Starting with index 0, column number of center cell
   */
  public abstract Map<int[], State> createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV);

  /***
   * Assume there are no states in CSV with "-1". Returns -1 if given neighborPosition is out of bounds
   * for allStatesInCSV (i.e. center cell is on the outer edge of allStatesInCSV).
   */
  public State getNeighborStateFromAdjacentPosition(int[] neighborPosition, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) throws IndexOutOfBoundsException{
    int neighborRow = centerCellRow + neighborPosition[0];
    int neighborColumn = centerCellColumn + neighborPosition[1];

    return allStatesInCSV[neighborRow][neighborColumn];
  }

  public Map<int[], State> getNeighborPositionToState() {
    return neighborPositionToState;
  }

  public ResourceBundle getModelResources() {
    return modelResources;
  }

  public boolean equals(Neighborhood neighborhood) {
    return neighborPositionToState.equals(neighborhood.getNeighborPositionToState());
  }
}
