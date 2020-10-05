package modelTest;

import model.Cell;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

  @Test
  void verifyCellsInSquareInitialGridInstantiation() {
    int[][] states = new int[][]{
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    verifyStatesInGrid(states);
  }

  @Test
  void verifyCellsInNonSquareInitialGridInstantiation() {
    int[][] states = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    verifyStatesInGrid(states);
  }

  private void verifyStatesInGrid(int[][] states) {
    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, states);
    Cell[][] initialCellGrid = grid.getCellGrid();
    for (int row = 0; row < initialCellGrid.length; row++) {
      for (int col = 0; col < initialCellGrid[0].length; col++) {
        Cell cell = initialCellGrid[row][col];
        int state = cell.getCurrentState();
        assertEquals(state,states[row][col]);
      }
    }
  }

  @Test
  void verifyNeighborsInInitialGridInstantiation() {

  }

  @Test
  void gridEquals() {
    int[][] statesOne = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int[][] statesTwo = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertTrue(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEquals() {
    int[][] statesOne = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 1}
    };

    int[][] statesTwo = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void gridEqualsWithEmptyCell() {
    int[][] statesOne = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, -1, 0}
    };

    int[][] statesTwo = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, -1, 0}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertTrue(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEqualsWithEmptyCell() {
    int[][] statesOne = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, -1, 0}
    };

    int[][] statesTwo = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEqualsDifferentLengthMatrices() {
    int[][] statesOne = new int[][] {
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    int[][] statesTwo = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertFalse(gridOne.equals(gridTwo));
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