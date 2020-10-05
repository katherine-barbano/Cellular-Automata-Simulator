package modelTest;

import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;

class GridTest {

  @Test
  void getNextGridGameOfLife() {
    SimulationType simulationType = SimulationType.GAME_OF_LIFE;
    int[][] initialStateMatrix = new int[][] {
        {0, 0, 0},
        {0, 1, 0},
        {0, 1, 0}
    };

    Grid grid = new Grid(simulationType, initialStateMatrix);

    //int[][] initialCellGrid = grid.getCellGrid();
  }
}