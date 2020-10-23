package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

/**
 * NeighborPolicy for 4 neighbors in corners of center cell
 *
 * @author Katherine Barbano
 */
public class CornerNeighborPolicy extends NeighborPolicy {

  public CornerNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(Math.abs(row)==Math.abs(column) && row!=0) {
          makePositionAndPutIntoMap(row, column);
        }
      }
    }
  }
}
