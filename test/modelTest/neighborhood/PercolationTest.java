package modelTest.neighborhood;

import controller.State;
import controller.stateType.PercolationState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PercolationTest {

  @Test
  void getNextGridPercolationNoBlocks() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid2 = new State[][] {
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));

    Grid actualNextNextGrid = actualNextGrid.getNextGrid();
    Grid expectedNextNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid2);

    assertTrue(actualNextNextGrid.equals(expectedNextNextGrid));
  }

  @Test
  void getNextGridPercolationWaterIsTrapped() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationWaterOnEdge() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationTwoWaterSources() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.WATER)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridPercolationNoWater() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  public void getNextGridPercolationMixOfThreeStates() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.WATER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.WATER)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
