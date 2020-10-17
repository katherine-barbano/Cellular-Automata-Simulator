package modelTest;

import controller.State;
import controller.stateType.SpreadingOfFireState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpreadingOfFireTest {

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

    Grid currentGrid = new Grid("SpreadingOfFire", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", expectedGrid);

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

    Grid currentGrid = new Grid("SpreadingOfFire", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", expectedGrid);

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

    Grid currentGrid = new Grid("SpreadingOfFire", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("SpreadingOfFire", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextStateSpreadingOfFireWithFire() {
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

    Grid currentGrid = new Grid("SpreadingOfFire", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid("SpreadingOfFire", expectGrid1);
    Grid expectGrid2Result = new Grid("SpreadingOfFire", expectGrid2);

    assertTrue(actualNextGrid.equals(expectGrid1Result) || actualNextGrid.equals(expectGrid2Result));
  }

}
