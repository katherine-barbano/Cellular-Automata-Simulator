package modelTest;

import controller.State;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

  @Test
  void getNextGridPercolationNoBlocks() {
    State[][] firstGrid = new State[][] {
        {new State("Open"), new State("Open"), new State("Open")},
        {new State("Open"), new State("Water"), new State("Open")},
        {new State("Open"), new State("Open"), new State("Open")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Open"), new State("Water"), new State("Open")},
        {new State("Water"), new State("Water"), new State("Water")},
        {new State("Open"), new State("Water"), new State("Open")}
    };

    State[][] expectedGrid2 = new State[][] {
        {new State("Water"), new State("Water"), new State("Water")},
        {new State("Water"), new State("Water"), new State("Water")},
        {new State("Water"), new State("Water"), new State("Water")}
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
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")},
        {new State("Blocked"), new State("Water"), new State("Blocked"), new State("Open")},
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")},
        {new State("Blocked"), new State("Water"), new State("Blocked"), new State("Open")},
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationWaterOnEdge() {
    State[][] firstGrid = new State[][] {
        {new State("Open"), new State("Open"), new State("Open")},
        {new State("Water"), new State("Open"), new State("Open")},
        {new State("Open"), new State("Open"), new State("Open")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Water"), new State("Open"), new State("Open")},
        {new State("Water"), new State("Water"), new State("Open")},
        {new State("Water"), new State("Open"), new State("Open")}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationTwoWaterSources() {
    State[][] firstGrid = new State[][] {
        {new State("Open"), new State("Open"), new State("Open")},
        {new State("Water"), new State("Open"), new State("Water")},
        {new State("Open"), new State("Open"), new State("Open")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Water"), new State("Open"), new State("Water")},
        {new State("Water"), new State("Water"), new State("Water")},
        {new State("Water"), new State("Open"), new State("Water")}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationNoWater() {
    State[][] firstGrid = new State[][] {
        {new State("Blocked"), new State("Open"), new State("Open"), new State("Open")},
        {new State("Open"), new State("Open"), new State("Blocked"), new State("Open")},
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Blocked"), new State("Open"), new State("Open"), new State("Open")},
        {new State("Open"), new State("Open"), new State("Blocked"), new State("Open")},
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Open")}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationMixOfThreeStates() {
    State[][] firstGrid = new State[][] {
        {new State("Blocked"), new State("Open"), new State("Open"), new State("Open")},
        {new State("Open"), new State("Water"), new State("Blocked"), new State("Open")},
        {new State("Open"), new State("Blocked"), new State("Open"), new State("Water")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Blocked"), new State("Water"), new State("Open"), new State("Open")},
        {new State("Water"), new State("Water"), new State("Blocked"), new State("Water")},
        {new State("Open"), new State("Blocked"), new State("Water"), new State("Water")}
    };

    Grid currentGrid = new Grid(SimulationType.PERCOLATION, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.PERCOLATION, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
