package model.neighborhoods;

import controller.State;
import java.util.Map;
import model.NeighborPolicy;
import model.Neighborhood;

public abstract class NonInfluentialNeighborhood extends Neighborhood {

  public NonInfluentialNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    return nextState;
  }
}
