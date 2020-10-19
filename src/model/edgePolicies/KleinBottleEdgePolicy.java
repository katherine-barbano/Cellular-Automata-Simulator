package model.edgePolicies;

import controller.State;
import model.EdgePolicy;

public class KleinBottleEdgePolicy extends InfiniteEdgePolicy {

  public KleinBottleEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner) {
    if((neighborRow < 0 && !neighborStartsAsCorner) || (neighborRow >= states.length && neighborStartsAsCorner)) {
      positionNeighbor[0] = states.length-1;
    }
    else if (!neighborStartsAsCorner){
      positionNeighbor[0] = 0;
    }
    else {
      positionNeighbor[0] = neighborRow - positionNeighbor[0];
    }
  }

  @Override
  void handleColumnWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner) {
    if(neighborColumn < 0) {
      positionNeighbor[1] = states[0].length-1;
    }
    else {
      positionNeighbor[1] = 0;
    }

    if(!neighborStartsAsCorner) {
      positionNeighbor[0] = (states.length - 1) - positionNeighbor[0];
    }
  }
}
