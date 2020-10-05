package modelTest;

import model.Cell;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeGridTest {

  private void checkStillLifeGridsEqual(SimulationType simulationType, int[][] initialMatrix) {
    Grid currentGrid = new Grid(simulationType, initialMatrix);
    Grid nextGrid = currentGrid.getNextGrid();
    assertTrue(currentGrid.equals(nextGrid));
  }

  private void checkTwoPeriodOscillator(SimulationType simulationType, int[][] gridInitialState, int[][] gridOneStepState) {
    checkExpectedAndActualNextStateGridsEqual(simulationType, gridInitialState, gridOneStepState);
    checkExpectedAndActualNextStateGridsEqual(simulationType, gridOneStepState, gridInitialState);
  }

  private void checkExpectedAndActualNextStateGridsEqual(SimulationType simulationType, int[][] initialState, int[][] expectedNextState) {
    Grid currentGrid = new Grid(simulationType, initialState);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(simulationType, expectedNextState);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
  }

  @Test
  void getNextGridGameOfLifeStillLifeBlock() {
    int[][] block = new int[][] {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, block);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBeehive() {
    int[][] beehive = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 1, 0, 0, 1, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, beehive);
  }

  @Test
  void getNextGridGameOfLifeStillLifeLoaf() {
    int[][] loaf = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 0, 1, 1, 0, 0},
        {0, 1, 0, 0, 1, 0},
        {0, 0, 1, 0, 1, 0},
        {0, 0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, loaf);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBoat() {
    int[][] boat = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, boat);
  }

  @Test
  void getNextGridGameOfLifeStillLifeTub() {
    int[][] tub = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 1, 0, 1, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, tub);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBlinker() {
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

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, blinkerInitialState, blinkerOneStepState);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBeacon() {
    int[][] beaconInitialState = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 0, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 0}
    };

    int[][] beaconOneStepState = new int[][] {
        {0, 0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0, 0},
        {0, 1, 0, 0, 0, 0},
        {0, 0, 0, 0, 1, 0},
        {0, 0, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 0}
    };

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, beaconInitialState, beaconOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAllAliveCellsOnEdge() {
    int[][] edgesInitialState = new int[][] {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1},
    };

    int[][] edgesOneStepState = new int[][] {
        {1, 0, 1},
        {0, 0, 0},
        {1, 0, 1},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAliveCellsInCorners() {
    int[][] edgesInitialState = new int[][] {
        {1, 0, 1},
        {0, 0, 0},
        {1, 0, 1},
    };

    int[][] edgesOneStepState = new int[][] {
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeEndWithAliveCellsOnFirstTwoRows() {
    int[][] edgesInitialState = new int[][] {
        {1, 1, 1},
        {1, 1, 1},
        {0, 0, 0},
    };

    int[][] edgesOneStepState = new int[][] {
        {1, 0, 1},
        {1, 0, 1},
        {0, 1, 0},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
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
