package modelTest;

import controller.PercolationState;
import controller.State;
import controller.WaTorWorldState;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

  @Test
  void getNextGridPercolation() {
    State[][] grid = new State[][] {
        {PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.WATER, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.WATER}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(actualNextGrid.getCell(r,c).getCurrentState());
      }
      System.out.println();
    }
  }
}
