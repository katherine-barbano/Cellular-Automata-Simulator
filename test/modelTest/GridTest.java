package modelTest;

import model.Cell;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;

class GridTest {

  @Test
  void getNextGridGameOfLife() {
    SimulationType simulationType = SimulationType.GAME_OF_LIFE;
    int[][] initialStateMatrix = new int[][] {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    Grid currentGrid = new Grid(simulationType, initialStateMatrix);
    System.out.println();
    Grid nextGrid = currentGrid.getNextGrid();
    printGrid(currentGrid);
    printGrid(nextGrid);
  }

  void printGrid(Grid grid) {
    Cell[][] initialCellGrid = grid.getCellGrid();
    for(int row = 0; row< initialCellGrid.length; row++) {
      for(int col = 0; col< initialCellGrid[0].length; col++) {
        Cell cell = initialCellGrid[row][col];
        int state = cell.getCurrentState();
        System.out.print(state+" ");
      }
      System.out.println();
    }
    System.out.println();
  }
}