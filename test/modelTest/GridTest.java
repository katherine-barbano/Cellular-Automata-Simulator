package modelTest;

import model.Cell;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

  @Test
  void verifyCellsInInitialGridInstantiation() {

  }

  @Test
  void verifyNeighborsInInitialGridInstantiation() {

  }

  @Test
  void gridEquals() {

  }

  @Test
  void getNextGridGameOfLifeStillLifes() {
    int[][] block = new int[][] {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    int[][] beehive = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 1, 0, 0, 1, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    int[][] loaf = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    int[][] boat = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int[][] tub = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, block);
    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, beehive);
    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, loaf);
    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, boat);
    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, tub);

  }

  private void checkStillLifeGridsEqual(SimulationType simulationType, int[][] initialMatrix) {
    Grid currentGrid = new Grid(simulationType, initialMatrix);
    Grid nextGrid = currentGrid.getNextGrid();
    assertTrue(currentGrid.equals(nextGrid));
  }

  @Test
  void getNextGridGameOfLifePeriodTwoOscillators() {
    int[][] blinkerInitialState = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int[][] blinkerOneStepState = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, blinkerInitialState, blinkerOneStepState);
  }

  private void checkExpectedAndActualNextStateGridsEqual(SimulationType simulationType, int[][] initialState, int[][] expectedNextState) {
    Grid currentGrid = new Grid(simulationType, initialState);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(simulationType, expectedNextState);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
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