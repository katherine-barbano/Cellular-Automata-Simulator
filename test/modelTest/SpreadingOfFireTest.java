package modelTest;

import controller.State;
import controller.states.MovingStateWithAge;
import model.Grid;
import model.Neighborhood;
import model.SimulationType;
import model.neighborhoods.concrete.SpreadingOfFireNeighborhood;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpreadingOfFireTest {

  @Test
  void getNextGridSpreadingOfFireEmpty() {
    State[][] firstGrid = new State[][] {
        {new State("Empty"), new State("Empty")},
        {new State("Empty"), new State("Empty")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Empty"), new State("Empty")},
        {new State("Empty"), new State("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTrees() {
    State[][] firstGrid = new State[][] {
        {new State("Tree"), new State("Tree")},
        {new State("Tree"), new State("Tree")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Tree"), new State("Tree")},
        {new State("Tree"), new State("Tree")}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTreesAndEmpty() {
    State[][] firstGrid = new State[][] {
        {new State("Tree"), new State("Empty")},
        {new State("Empty"), new State("Tree")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Tree"), new State("Empty")},
        {new State("Empty"), new State("Tree")}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextStateSpreadingOfFireWithFire() {
    State[][] firstGrid = new State[][] {
        {new State("Empty"), new State("Empty")},
        {new State("Tree"), new State("Burning")}
    };

    State[][] expectGrid1 = new State[][] {
        {new State("Empty"), new State("Empty")},
        {new State("Tree"), new State("Empty")}
    };

    State[][] expectGrid2 = new State[][] {
        {new State("Empty"), new State("Empty")},
        {new State("Burning"), new State("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid(SimulationType.SPREADING_OF_FIRE, expectGrid1);
    Grid expectGrid2Result = new Grid(SimulationType.SPREADING_OF_FIRE, expectGrid2);

    assertTrue(actualNextGrid.equals(expectGrid1Result) || actualNextGrid.equals(expectGrid2Result));
  }

}
