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
  public static final String POSITION_NOT_FOUND_EXCEPTION_PROPERTIES = "positionNotFoundExceptionMessage";

  private Map<int[], Neighborhood> neighborhoodsOfNeighbors;
  private ResourceBundle modelResources;
  private NeighborPolicy neighborPolicy;

  public Neighborhood(NeighborPolicy neighborPolicy) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborhoodsOfNeighbors = new HashMap<>();
    this.neighborPolicy = neighborPolicy;
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
    String positionNotFoundExceptionMessage = modelResources.getString(POSITION_NOT_FOUND_EXCEPTION_PROPERTIES);
    throw new ModelException(positionNotFoundExceptionMessage);
  }

  public Map<int[], Neighborhood> getNeighborhoodsOfNeighbors() {
    return neighborhoodsOfNeighbors;
  }

  public ResourceBundle getModelResources() {
    return modelResources;
  }

  public boolean equals(Neighborhood neighborhood) {
    return neighborPolicy.equals(neighborhood);
  }

  protected boolean neighborPositionToStateContainsState(State target) {
    return neighborPolicy.neighborPositionToStateContainsState(target);
  }

  public void replaceNeighborStateWithNewState(int[] neighborKey, State newState) {
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

  protected Set<int[]> allPossibleRelativePositions() {
    return neighborPolicy.allPossibleRelativePositions();
  }
}
