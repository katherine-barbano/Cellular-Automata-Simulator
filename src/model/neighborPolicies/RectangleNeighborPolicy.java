package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

/**
 * NeighborPolicy for all adjacent neighbors that share an edge (ie not a corner neighbor)
 *
 * @author Katherine Barbano
 */
public class RectangleNeighborPolicy extends NeighborPolicy {

  public RectangleNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(Math.abs(row)!=Math.abs(column)) {
          makePositionAndPutIntoMap(row, column);
        }
      }
    }
  }
}
