package modelTest;

import controller.State;
import controller.states.PercolationState;
import controller.states.RockPaperScissorsState;
import controller.states.SpreadingOfFireState;
import java.util.Random;
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
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.EMPTY},
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.EMPTY}
    };

    State[][] expectedGrid = new State[][] {
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.EMPTY},
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.EMPTY}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTrees() {
    State[][] firstGrid = new State[][] {
        {SpreadingOfFireState.TREE, SpreadingOfFireState.TREE},
        {SpreadingOfFireState.TREE, SpreadingOfFireState.TREE}
    };

    State[][] expectedGrid = new State[][] {
        {SpreadingOfFireState.TREE, SpreadingOfFireState.TREE},
        {SpreadingOfFireState.TREE, SpreadingOfFireState.TREE}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridSpreadingOfFireTreesAndEmpty() {
    State[][] firstGrid = new State[][] {
        {SpreadingOfFireState.TREE, SpreadingOfFireState.EMPTY},
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.TREE}
    };

    State[][] expectedGrid = new State[][] {
        {SpreadingOfFireState.TREE, SpreadingOfFireState.EMPTY},
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.TREE}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.SPREADING_OF_FIRE, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextStateSpreadingOfFireWithFire() {
    State[][] firstGrid = new State[][] {
        {SpreadingOfFireState.TREE, SpreadingOfFireState.EMPTY, SpreadingOfFireState.EMPTY, SpreadingOfFireState.BURNING},
        {SpreadingOfFireState.TREE, SpreadingOfFireState.BURNING, SpreadingOfFireState.TREE, SpreadingOfFireState.TREE},
        {SpreadingOfFireState.EMPTY, SpreadingOfFireState.BURNING, SpreadingOfFireState.TREE, SpreadingOfFireState.TREE}
    };

    Grid currentGrid = new Grid(SimulationType.SPREADING_OF_FIRE, firstGrid);
    Neighborhood neighborhood = currentGrid.getCell(0,2).getNeighborhood();
    double nextDouble = ((SpreadingOfFireNeighborhood)neighborhood).getNextDoubleOfRandom();
    Grid actualNextGrid = currentGrid.getNextGrid();

    if(nextDouble>=.15) {
      assertEquals(actualNextGrid.getCell(1,0).getCurrentState(), SpreadingOfFireState.BURNING);
    }
    else {
      assertEquals(actualNextGrid.getCell(1,0).getCurrentState(), SpreadingOfFireState.TREE);
    }
  }

}
