package modelTest.neighborEdgePolicy;

import static org.junit.jupiter.api.Assertions.assertTrue;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import model.Grid;
import org.junit.jupiter.api.Test;

class TriangleFiniteTest {

  @Test
  void triangleFinitePoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid currentGrid = new Grid("GameOfLife", "Finite", "Triangle", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "Finite", "Triangle", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void triangleFinitePoliciesPercolation() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Triangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Triangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
