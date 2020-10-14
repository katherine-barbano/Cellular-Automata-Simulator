package model;

import controller.State;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/***
 * States represented as the integers from csv file.
 */
public abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model";
  public static final String COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES = "neighborPositionCoordinateSize";
  public static final String KEY_NOT_FOUND_PROPERTIES = "neighborPositionNotFound";

  private Map<int[], State> neighborPositionToState;
  private Map<int[], Neighborhood> neighborhoodsOfNeighbors;
  private ResourceBundle modelResources;

  public Neighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborPositionToState = new HashMap<>();
    neighborhoodsOfNeighbors = new HashMap<>();
    createNeighborMap(centerCellRow, centerCellColumn, stateGrid);
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
  public abstract void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV);

  /***
   * Assume there are no states in CSV with "-1". Returns -1 if given neighborPosition is out of bounds
   * for allStatesInCSV (i.e. center cell is on the outer edge of allStatesInCSV).
   */
  public State getNeighborStateFromAdjacentPosition(int[] neighborPosition, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) throws IndexOutOfBoundsException{
    int neighborRow = centerCellRow + neighborPosition[0];
    int neighborColumn = centerCellColumn + neighborPosition[1];

    return allStatesInCSV[neighborRow][neighborColumn];
  }

  public boolean neighborPositionToStateContainsState(State target) {
    for(int[] position:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(position).equals(target)) {
        return true;
      }
    }
    return false;
  }

  public Map<int[], State> getNeighborPositionToState() {
    return neighborPositionToState;
  }

  public void setNeighborhoodsOfNeighbors(Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    this.neighborhoodsOfNeighbors = neighborhoodsOfNeighbors;
  }

  public Neighborhood findPositionInNeighborhoodOfNeighbors(int[] openPosition) {
    for(int[] position:neighborhoodsOfNeighbors.keySet()) {
      if(position[0] == openPosition[0] && position[1] == openPosition[1]) {
        return neighborhoodsOfNeighbors.get(position);
      }
    }
    throw new ModelException("Eaten not found");
  }

  public Map<int[], Neighborhood> getNeighborhoodsOfNeighbors() {
    return neighborhoodsOfNeighbors;
  }

  public void replaceNeighborStateWithNewState(int[] neighborKey, State newState) {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(keysAreEqual(thisKey,neighborKey)) {
        neighborPositionToState.put(thisKey,newState);
      }
    }
  }

  public boolean keysAreEqual(int[] thisKey, int[] otherKey) {
    return thisKey[0] == otherKey[0] && thisKey[1] == otherKey[1];
  }

  public State getStateFromNeighborPosition(int[] position) {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(keysAreEqual(thisKey,position)) {
        return neighborPositionToState.get(thisKey);
      }
    }
    String errorMessage = getModelResources().getString(KEY_NOT_FOUND_PROPERTIES);
    throw new ModelException(errorMessage);
  }

  public void putNeighborPositionIntoMap(int[] relativePositionOfNeighbor, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    try {
      State neighborState = getNeighborStateFromAdjacentPosition(relativePositionOfNeighbor, centerCellRow, centerCellColumn, allStatesInCSV);
      neighborPositionToState.put(relativePositionOfNeighbor,neighborState);
    }
    catch(IndexOutOfBoundsException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
  }

  public ResourceBundle getModelResources() {
    return modelResources;
  }

  public boolean equals(Neighborhood neighborhood) {
    Map<int[],State> otherNeighborPositionToState = neighborhood.getNeighborPositionToState();
    boolean direction1 = compareNeighborhoodInOneDirection(otherNeighborPositionToState,neighborPositionToState);
    boolean direction2 = compareNeighborhoodInOneDirection(neighborPositionToState,otherNeighborPositionToState);
    return direction1 && direction2;
  }

  private boolean compareNeighborhoodInOneDirection(Map<int[],State> otherNeighborPositionToState, Map<int[],State> thisNeighborPositionToState) {
    for (int[] otherKey: otherNeighborPositionToState.keySet()) {
      Set<int[]> thisNeighborKeySet = thisNeighborPositionToState.keySet();

      boolean thisNeighborContainsKey = false;
      int[] thisNeighborKey = new int[2];
      for(int[] thisKey:thisNeighborKeySet) {
        if(keysAreEqual(thisKey,otherKey)) {
          thisNeighborContainsKey=true;
          thisNeighborKey = thisKey;
        }
      }

      if(!thisNeighborContainsKey || !otherNeighborPositionToState.get(otherKey).equals(thisNeighborPositionToState.get(thisNeighborKey))) {
        return false;
      }
    }
    return true;
  }

  //for help debugging
  public void printNeighborPositionToState() {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      System.out.print(thisKey[0]+","+thisKey[1]+"=");
      System.out.print(neighborPositionToState.get(thisKey)+", ");
    }
    System.out.println();
  }

  public void createNeighborMapForAdjacentNeighborsOnly(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(Math.abs(row)!=Math.abs(column)) {
          makePositionAndPutIntoMap(row, column, centerCellRow, centerCellColumn, allStatesInCSV);
        }
      }
    }
  }

  public void createNeighborMapForAdjacentAndDiagonal(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(!(row==0 && column==0)) {
          makePositionAndPutIntoMap(row, column, centerCellRow, centerCellColumn, allStatesInCSV);
        }
      }
    }
  }

  private void makePositionAndPutIntoMap(int row, int column, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    int coordinateDimensions = Integer
        .parseInt(getModelResources().getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
    int[] relativePositionOfNeighbor = new int[coordinateDimensions];
    relativePositionOfNeighbor[0] = row;
    relativePositionOfNeighbor[1] = column;
    putNeighborPositionIntoMap(relativePositionOfNeighbor,
        centerCellRow, centerCellColumn, allStatesInCSV);
  }

  public int getNumberOfNeighborsWithGivenState(State targetState) {
    Map<int[], State> adjacentNeighborsToState = getNeighborPositionToState();
    int numberNeighbors=0;
    for(int[] neighborPosition:adjacentNeighborsToState.keySet()) {
      State state = adjacentNeighborsToState.get(neighborPosition);
      if(state.equals(targetState)) {
        numberNeighbors++;
      }
    }
    return numberNeighbors;
  }
}
