package model;

import controller.State;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/***
 * States represented as the integers from csv file.
 */
abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model";

  private Map<Integer, State> neighborPositionToState;
  private ResourceBundle modelResources;

  Neighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);

    createNeighborMap(centerCellRow, centerCellColumn, stateGrid);
  }

  abstract State getNextState(State currentState);

  /***
   * Creates a map with keys as neighbor position relative to the center cell, and values
   * as the state value as an integer. Neighbor position is numbered from 0 to 7, in the format
   * of the picture in doc/neighborMapIndices.JPG. If a neighbor does not exist (i.e. center cell is on
   * outer border of grid), Map simply does not contain a key with that neighbor position number.
   * @param allStatesInCSV 2D int array of all the states in the CSV file
   * @param centerCellRow Starting with index 0, row number of center cell
   * @param centerCellColumn Starting with index 0, column number of center cell
   */
  void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    int maxNumberOfNeighbors = Integer.parseInt((String)modelResources.getObject("MaxNumberOfNeighbors"));
    neighborPositionToState = new HashMap<>();

    for(int neighborPosition = 0; neighborPosition<maxNumberOfNeighbors; neighborPosition++) {
      State neighborState = getNeighborStateFromAdjacentPosition(neighborPosition, centerCellRow, centerCellColumn, allStatesInCSV);
      if (neighborState != null) {
        neighborPositionToState.put(neighborPosition,neighborState);
      }
    }
  }

  /***
   * Assume there are no states in CSV with "-1". Returns -1 if given neighborPosition is out of bounds
   * for allStatesInCSV (i.e. center cell is on the outer edge of allStatesInCSV).
   */
  private State getNeighborStateFromAdjacentPosition(int neighborPosition, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    try {
      State neighborState = null;
      switch (neighborPosition) {
        case 0:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn];
          break;
        case 1:
          neighborState = allStatesInCSV[centerCellRow][centerCellColumn-1];
          break;
        case 2:
          neighborState = allStatesInCSV[centerCellRow][centerCellColumn+1];
          break;
        case 3:
          neighborState = allStatesInCSV[centerCellRow+1][centerCellColumn];
          break;
        case 4:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn-1];
          break;
        case 5:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn+1];
          break;
        case 6:
          neighborState = allStatesInCSV[centerCellRow+1][centerCellColumn-1];
          break;
        case 7:
          neighborState = allStatesInCSV[centerCellRow+1][centerCellColumn+1];
          break;
        default:
          //should never be reached, because maxNumberOfNeighbors = 8
          break;
      }
      return neighborState;
    }
    catch(IndexOutOfBoundsException e) {
      //TODO: throw custom runtime exception here
      return null;
    }
  }

  Map<Integer, State> getNeighborPositionToState() {
    return neighborPositionToState;
  }

  ResourceBundle getModelResources() {
    return modelResources;
  }

  public boolean equals(Neighborhood neighborhood) {
    return neighborPositionToState.equals(neighborhood.getNeighborPositionToState());
  }
}
