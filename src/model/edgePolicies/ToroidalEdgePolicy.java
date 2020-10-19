package model.edgePolicies;

import controller.State;

public class ToroidalEdgePolicy extends InfiniteEdgePolicy {

  public ToroidalEdgePolicy(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  void handleRowWrapping(State[][] states, int neighborRow, int neighborColumn, int[] positionNeighbor, boolean neighborStartsAsCorner) {
    if(neighborRow < 0) {
      positionNeighbor[0] = states.length-1;
    }
    else {
      positionNeighbor[0] = 0;
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
  }
}
