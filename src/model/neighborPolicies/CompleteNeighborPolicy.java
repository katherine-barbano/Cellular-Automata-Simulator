package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

/**
 * NeighborPolicy for all 8 adjacent neighbors
 *
 * @author Katherine Barbano
 */
public class CompleteNeighborPolicy extends NeighborPolicy {

  public CompleteNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        if(!(row==0 && column==0)) {
          makePositionAndPutIntoMap(row, column);
        }
      }
    }
  }
}
