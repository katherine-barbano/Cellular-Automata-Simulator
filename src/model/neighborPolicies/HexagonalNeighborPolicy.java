package model.neighborPolicies;

import model.EdgePolicy;
import model.NeighborPolicy;

/**
 * NeighborPolicy for all adjacent neighbors except the northeast and southwest neighbors
 *
 * @author Katherine Barbano
 */
public class HexagonalNeighborPolicy extends NeighborPolicy {

  public HexagonalNeighborPolicy(EdgePolicy edgePolicy) {
    super(edgePolicy);
  }

  @Override
  public void createNeighborPositionToState() {
    for(int row = -1; row<=1; row++) {
      for(int column = -1; column<=1; column++) {
        boolean isCorner = row==column;
        boolean isRectangle = Math.abs(row)!=Math.abs(column);
        boolean isNotZero = !(row==0 && column==0);
        if((isCorner||isRectangle) && isNotZero) {
          makePositionAndPutIntoMap(row, column);
        }
      }
    }
  }
}
