package modelTest;

import controller.states.PercolationState;
import controller.State;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

  @Test
  void getNextGridPercolationNoBlocks() {
    State[][] firstGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.WATER, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.WATER, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.WATER},
        {PercolationState.OPEN, PercolationState.WATER, PercolationState.OPEN}
    };

    State[][] expectedGrid2 = new State[][] {
        {PercolationState.WATER, PercolationState.WATER, PercolationState.WATER},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.WATER},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.WATER}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));

    Grid actualNextNextGrid = actualNextGrid.getNextGrid();
    Grid expectedNextNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid2);

    assertTrue(actualNextNextGrid.equals(expectedNextNextGrid));
  }

  @Test
  void getNextGridPercolationWaterIsTrapped() {
    State[][] firstGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.BLOCKED, PercolationState.WATER, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.BLOCKED, PercolationState.WATER, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationWaterOnEdge() {
    State[][] firstGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.OPEN}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationTwoWaterSources() {
    State[][] firstGrid = new State[][] {
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.WATER},
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.WATER},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.WATER},
        {PercolationState.WATER, PercolationState.OPEN, PercolationState.WATER}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationNoWater() {
    State[][] firstGrid = new State[][] {
        {PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationMixOfThreeStates() {
    State[][] firstGrid = new State[][] {
        {PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.WATER, PercolationState.BLOCKED, PercolationState.OPEN},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.OPEN, PercolationState.WATER}
    };

    State[][] expectedGrid = new State[][] {
        {PercolationState.BLOCKED, PercolationState.WATER, PercolationState.OPEN, PercolationState.OPEN},
        {PercolationState.WATER, PercolationState.WATER, PercolationState.BLOCKED, PercolationState.WATER},
        {PercolationState.OPEN, PercolationState.BLOCKED, PercolationState.WATER, PercolationState.WATER}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
