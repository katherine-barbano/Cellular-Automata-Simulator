package modelTest;

import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {
/*
  //test used to verify no exception is thrown
  @Test
  void verifyCellsInSquareInitialGridInstantiation() {
    int[][] states = new int[][]{
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, states);
  }

  //test used to verify no exception is thrown
  @Test
  void verifyCellsInNonSquareInitialGridInstantiation() {
    int[][] states = new int[][] {
        {0, 0, 0, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, states);
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

  @Test
  void stableGrid() {
    int[][] block = new int[][] {
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, block);
    assertTrue(grid.currentGridIsStable());
  }

  @Test
  void unstableGrid() {
    int[][] unstableMatrix = new int[][] {
        {0, 0, 0, 0},
        {0, 0, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, unstableMatrix);
    assertFalse(grid.currentGridIsStable());
  }*/
}