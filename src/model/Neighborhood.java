package model;

import controller.State;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import org.assertj.core.internal.bytebuddy.matcher.StringMatcher.Mode;

/***
 * States represented as the integers from csv file.
 */
public abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model";

  private Map<int[], Neighborhood> neighborhoodsOfNeighbors;
  private ResourceBundle modelResources;

  public Neighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborhoodsOfNeighbors = new HashMap<>();
    createNeighborMap(centerCellRow, centerCellColumn, stateGrid);
  }

  public abstract State getNextState(State currentState);

  public abstract State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell);

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

  //for help debugging
  public void printNeighborPositionToState() {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      System.out.print(thisKey[0]+","+thisKey[1]+"=");
      System.out.print(neighborPositionToState.get(thisKey).getStateType()+", ");
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

  private void putIntoNeighborPositionToState(int[] relativePositionOfNeighbor, int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    try {
      putNeighborPositionIntoMap(relativePositionOfNeighbor, centerCellRow, centerCellColumn,
          allStatesInCSV);
    }
    catch(ModelException e) {
      //this ModelException means that according to edge policy, there should not be a neighbor at this relative position. So don't add anything to the map in this case.
    }
  }
}
