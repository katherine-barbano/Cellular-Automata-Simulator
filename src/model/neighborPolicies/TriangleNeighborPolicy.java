package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

/**
 * NeighborPolicy for cell immediately above center, and cells in the bottom left and bottom
 * right corners from the center.
 *
 * @author Katherine Barbano
 */
public class TriangleNeighborPolicy extends NeighborPolicy {

  public TriangleNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int column = -1; column<=1; column++) {
      if(Math.abs(column)==1) {
        makePositionAndPutIntoMap(1, column);
      }
    }
    makePositionAndPutIntoMap(-1,0);
  }
}
