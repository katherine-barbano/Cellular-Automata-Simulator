package model;

import controller.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class NeighborPolicy {

  public static final String KEY_NOT_FOUND_PROPERTIES = "neighborPositionNotFound";
  public static final String MODEL_RESOURCE_PATH = "resources/Model";
  public static final String COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES = "neighborPositionCoordinateSize";

  private Map<int[], State> neighborPositionToState;
  private EdgePolicy edgePolicy;
  private ResourceBundle modelResources;

  protected NeighborPolicy(EdgePolicy edgePolicy) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    this.edgePolicy = edgePolicy;
    createNeighborPositionToState();
  }

  protected abstract void createNeighborPositionToState();

  protected EdgePolicy getEdgePolicy() {
    return edgePolicy;
  }

  protected void makePositionAndPutIntoMap(int row, int column) {
    int coordinateDimensions = Integer
        .parseInt(modelResources.getString(COORDINATE_DIMENSIONS_IN_MODEL_PROPERTIES));
    int[] relativePositionOfNeighbor = new int[coordinateDimensions];
    relativePositionOfNeighbor[0] = row;
    relativePositionOfNeighbor[1] = column;
    State neighborState = edgePolicy.getNeighborStateFromPositionForInitialization(relativePositionOfNeighbor);
    neighborPositionToState.put(relativePositionOfNeighbor, neighborState);
  }

  protected boolean neighborPositionToStateContainsState(State target) {
    for(int[] position:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(position).equals(target)) {
        return true;
      }
    }
    return false;
  }

  protected void replaceNeighborStateWithNewState(int[] neighborKey, State newState) {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(keysAreEqual(thisKey,neighborKey)) {
        neighborPositionToState.put(thisKey,newState);
      }
    }
  }

  protected boolean keysAreEqual(int[] thisKey, int[] otherKey) {
    return thisKey[0] == otherKey[0] && thisKey[1] == otherKey[1];
  }

  protected State getStateFromNeighborPosition(int[] position) {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(keysAreEqual(thisKey,position)) {
        return neighborPositionToState.get(thisKey);
      }
    }
    String errorMessage = modelResources.getString(KEY_NOT_FOUND_PROPERTIES);
    throw new ModelException(errorMessage);
  }


  protected boolean equals(Neighborhood neighborhood) {
    boolean valueEquality = compareNeighborhoods(neighborhood);
    boolean sizeEquality = getNumberOfNeighbors() == neighborhood.getNumberOfNeighbors();
    return valueEquality && sizeEquality;
  }

  private boolean compareNeighborhoods(Neighborhood otherNeighbor) {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      boolean attemptToRetrieveKey = tryToRetrieveThisKeyFromNeighbor(thisKey, otherNeighbor);
      if(attemptToRetrieveKey == false) {
        return false;
      }
    }
    return true;
  }

  private boolean tryToRetrieveThisKeyFromNeighbor(int[] thisKey, Neighborhood otherNeighbor) {
    try
    {
      otherNeighbor.getStateFromNeighborPosition(thisKey);
      return true;
    }
    catch(ModelException e) {
      return false;
    }
  }

  protected int getNumberOfNeighborsWithGivenState(State targetState) {
    Map<int[], State> adjacentNeighborsToState = neighborPositionToState;
    int numberNeighbors=0;
    for(int[] neighborPosition:adjacentNeighborsToState.keySet()) {
      State state = adjacentNeighborsToState.get(neighborPosition);
      if(state.equals(targetState)) {
        numberNeighbors++;
      }
    }
    return numberNeighbors;
  }

  protected int getNumberOfNeighbors() {
    return neighborPositionToState.size();
  }

  protected List<int[]> positionsOfTargetStateNeighbors(State state) {
    List<int[]> emptyIndices = new ArrayList<>();
    for(int[] thisKey:neighborPositionToState.keySet()) {
      if(neighborPositionToState.get(thisKey).equals(state)) {
        emptyIndices.add(thisKey);
      }
    }
    return emptyIndices;
  }

  protected Set<int[]> allPossibleRelativePositions() {
    return neighborPositionToState.keySet();
  }

  //for help debugging
  public void printNeighborPositionToState() {
    for(int[] thisKey:neighborPositionToState.keySet()) {
      System.out.print(thisKey[0]+","+thisKey[1]+"=");
      System.out.print(neighborPositionToState.get(thisKey).getStateType()+", ");
    }
    System.out.println();
  }

}
