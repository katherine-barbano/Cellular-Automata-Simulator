package modelTest;

import controller.State;
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
        {new State("Tree"), new State("Empty"), new State("Empty"), new State("Burning")},
        {new State("Tree"), new State("Burning"), new State("Tree"), new State("Tree")},
        {new State("Empty"), new State("Burning"), new State("Tree"), new State("Tree")}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Neighborhood neighborhood = currentGrid.getCell(0,2).getNeighborhood();
    double nextDouble = ((SpreadingOfFireNeighborhood)neighborhood).getNextDoubleOfRandom();
    Grid actualNextGrid = currentGrid.getNextGrid();

    if(nextDouble>=.15) {
      assertEquals(actualNextGrid.getCell(1,0).getCurrentState(), new State("Burning"));
    }
    else {
      assertEquals(actualNextGrid.getCell(1,0).getCurrentState(), new State("Tree"));
    }
  }

}
