package modelTest.neighborhood;

import controller.State;
import controller.stateType.SpreadingOfFireState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpreadingOfFireTest {

  @Test
  void getNextGridSpreadingOfFireEmpty() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTrees() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.TREE)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.TREE)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.TREE)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.TREE)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTreesAndEmpty() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.TREE)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.TREE)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  public void getNextStateSpreadingOfFireWithFire() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.BURNING)}
    };

    State[][] expectGrid1 = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectGrid2 = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid("SpreadingOfFire", "Finite", "Rectangle",  expectGrid1);
    Grid expectGrid2Result = new Grid("SpreadingOfFire", "Finite", "Rectangle",  expectGrid2);

    assertTrue(actualNextGrid.equals(expectGrid1Result) || actualNextGrid.equals(expectGrid2Result));
  }

  @Test
  void spreadingOfFireOptionalProbability() {
    State[][] grid = new State[][] {
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.TREE)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Complete", grid, 1.0);
    double actualProbability = currentGrid.getOptionalProbability();

    State[][] expectedGrid = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.TREE)}
    };

    Grid expectedNextGrid = new Grid("SpreadingOfFire", "Finite", "Complete", expectedGrid, 1.0);
    Grid actualNextGrid = currentGrid.getNextGrid();

    assertEquals(actualProbability, 1.0);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
  }

}
