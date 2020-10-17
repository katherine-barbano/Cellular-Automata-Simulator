package modelTest;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import java.util.ArrayList;
import java.util.List;
import model.Cell;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridTest {

  //test used to verify no exception is thrown
  @Test
  void verifyCellsInSquareInitialGridInstantiation() {
    State[][] states = new State[][]{
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid grid = new Grid("GameOfLife", states);
  }

  //test used to verify no exception is thrown
  @Test
  void verifyCellsInNonSquareInitialGridInstantiation() {
    State[][] states = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid grid = new Grid("GameOfLife", states);
  }

  @Test
  void gridEquals() {
    State[][] statesOne = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] statesTwo = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid gridOne = new Grid("GameOfLife", statesOne);
    Grid gridTwo = new Grid("GameOfLife", statesTwo);
    assertTrue(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEquals() {
    State[][] statesOne = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)}
    };

    State[][] statesTwo = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid gridOne = new Grid("GameOfLife", statesOne);
    Grid gridTwo = new Grid("GameOfLife", statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void gridNotEqualsDifferentLengthMatrices() {
    State[][] statesOne = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] statesTwo = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid gridOne = new Grid("GameOfLife", statesOne);
    Grid gridTwo = new Grid("GameOfLife", statesTwo);
    assertFalse(gridOne.equals(gridTwo));
  }

  @Test
  void stableGrid() {
    State[][] block = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid grid = new Grid("GameOfLife", block);
    assertTrue(grid.currentGridIsStable());
  }

  @Test
  void unstableGrid() {
    State[][] unstableMatrix = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid grid = new Grid("GameOfLife", unstableMatrix);
    assertFalse(grid.currentGridIsStable());
  }

  @Test
  void getAllCellsWithSameStateTypeAsTargetTwoStates() {
    State[][] matrix = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid grid = new Grid("GameOfLife", matrix);
    List<Cell> actualCells = grid.getAllCellsWithSameStateTypeAsTarget(GameOfLifeState.ALIVE);

    assertEquals(actualCells.size(), 3);
  }

  @Test
  void getAllCellsWithSameStateTypeAsTargetThreeStates() {
    State[][] matrix = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.WATER)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.BLOCKED)}
    };

    Grid grid = new Grid("Percolation", matrix);
    List<Cell> actualCells = grid.getAllCellsWithSameStateTypeAsTarget(PercolationState.BLOCKED);

    assertEquals(actualCells.size(), 2);
  }
}