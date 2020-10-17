package model;

import controller.State;
import java.util.HashMap;
import java.util.List;
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
  private NeighborPolicy neighborPolicy;

  public Neighborhood(NeighborPolicy neighborPolicy) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborhoodsOfNeighbors = new HashMap<>();
    this.neighborPolicy = neighborPolicy;
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

  public ResourceBundle getModelResources() {
    return modelResources;
  }

  public boolean equals(Neighborhood neighborhood) {
    Map<int[],State> otherNeighborPositionToState = neighborhood.getNeighborPositionToState();
    boolean direction1 = compareNeighborhoodInOneDirection(otherNeighborPositionToState,neighborPositionToState);
    boolean direction2 = compareNeighborhoodInOneDirection(neighborPositionToState,otherNeighborPositionToState);
    return direction1 && direction2;
  }

  protected boolean neighborPositionToStateContainsState(State target) {
    return neighborPolicy.neighborPositionToStateContainsState(target);
  }

  protected void replaceNeighborStateWithNewState(int[] neighborKey, State newState) {
    neighborPolicy.replaceNeighborStateWithNewState(neighborKey, newState);
  }

  protected State getStateFromNeighborPosition(int[] position) {
    return neighborPolicy.getStateFromNeighborPosition(position);
  }

  protected int getNumberOfNeighborsWithGivenState(State targetState) {
    return neighborPolicy.getNumberOfNeighborsWithGivenState(targetState);
  }

  protected int getNumberOfNeighbors() {
    return neighborPolicy.getNumberOfNeighbors();
  }

  protected List<int[]> positionsOfTargetStateNeighbors(State state) {
    return neighborPolicy.positionsOfTargetStateNeighbors(state);
  }
}
