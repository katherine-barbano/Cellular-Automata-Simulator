package model;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/***
 * States represented as the integers from csv file.
 */
abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model.properties";

  private Map<Integer, Integer> neighborPositionToState;
  private ResourceBundle modelResources;

  Neighborhood(int centerCellRow, int centerCellColumn, int[][] stateIntegerGrid) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);

    createCSVValueToStateMap();
    createNeighborMap(centerCellRow, centerCellColumn, stateIntegerGrid);
  }

  abstract int getNextState(int currentState);
  abstract Neighborhood getNextNeighborhood();

  /***
   * Chose to make this abstract because some types of simulations might not have a regular mapping
   * of 0:first state, 1: second state, etc. correspondence. Can add letters, additional states, etc.
   * to the CSV file.
   */
  abstract void createCSVValueToStateMap();

  /***
   * Creates a map with keys as neighbor position relative to the center cell, and values
   * as the state value as an integer. Neighbor position is numbered from 0 to 7, in the format
   * of the picture in doc/neighborMapIndices.JPG
   * @param allStatesInCSV 2D int array of all the states in the CSV file
   * @param centerCellRow Starting with index 0, row number of center cell
   * @param centerCellColumn Starting with index 0, column number of center cell
   */
  void createNeighborMap(int centerCellRow, int centerCellColumn, int[][] allStatesInCSV) {
    int maxNumberOfNeighbors = (int) modelResources.getObject("MaxNumberOfNeighbors");
    neighborPositionToState = new HashMap<>();

    for(int neighborPosition = 0; neighborPosition<maxNumberOfNeighbors; neighborPosition++) {
      int neighborState = getNeighborStateFromAdjacentPosition(neighborPosition, centerCellRow, centerCellColumn, allStatesInCSV);
      if (neighborState != -1) {
        neighborPositionToState.put(neighborPosition,neighborState);
      }
    }
  }

  /***
   * Assume there are no states in CSV with "-1". Returns -1 if given neighborPosition is out of bounds
   * for allStatesInCSV (i.e. center cell is on the outer edge of allStatesInCSV).
   */
  private int getNeighborStateFromAdjacentPosition(int neighborPosition, int centerCellRow, int centerCellColumn, int[][] allStatesInCSV) {
    try {
      int neighborState = -1;
      switch (neighborPosition) {
        case 0:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn-1];
          break;
        case 1:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn];
          break;
        case 2:
          neighborState = allStatesInCSV[centerCellRow-1][centerCellColumn+1];
          break;
        case 3:
          neighborState = allStatesInCSV[centerCellRow][centerCellColumn-1];
          break;
        case 4:
          neighborState = allStatesInCSV[centerCellRow][centerCellColumn+1];
          break;
        case 5:
          neighborState = allStatesInCSV[centerCellRow+1][centerCellColumn-1];
          break;
        case 6:
          neighborState = allStatesInCSV[centerCellRow+1][centerCellColumn];
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
      return -1;
    }
  }

  Map<Integer, Integer> getNeighborPositionToState() {
    return neighborPositionToState;
  }
}
