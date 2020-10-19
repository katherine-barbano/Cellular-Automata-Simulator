package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

public class TriangleNeighborPolicy extends NeighborPolicy {

  public TriangleNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int column = -1; column<=1; column++) {
        makePositionAndPutIntoMap(1, column);
    }
    makePositionAndPutIntoMap(-1,0);
  }
}
