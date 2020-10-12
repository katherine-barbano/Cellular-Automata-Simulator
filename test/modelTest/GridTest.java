package modelTest;

import controller.GameOfLifeState;
import controller.State;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

  //test used to verify no exception is thrown
  @Test
  void verifyCellsInSquareInitialGridInstantiation() {
    State[][] states = new State[][]{
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, states);
  }

  //test used to verify no exception is thrown
  @Test
  void verifyCellsInNonSquareInitialGridInstantiation() {
    State[][] states = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, states);
  }

  @Test
  void gridEquals() {
    State[][] statesOne = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    State[][] statesTwo = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertTrue(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEquals() {
    State[][] statesOne = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE}
    };

    State[][] statesTwo = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEqualsDifferentLengthMatrices() {
    State[][] statesOne = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    State[][] statesTwo = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid gridOne = new Grid(SimulationType.GAME_OF_LIFE, statesOne);
    Grid gridTwo = new Grid(SimulationType.GAME_OF_LIFE, statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void stableGrid() {
    State[][] block = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, block);
    assertTrue(grid.currentGridIsStable());
  }

  @Test
  void unstableGrid() {
    State[][] unstableMatrix = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, unstableMatrix);
    assertFalse(grid.currentGridIsStable());
  }
}