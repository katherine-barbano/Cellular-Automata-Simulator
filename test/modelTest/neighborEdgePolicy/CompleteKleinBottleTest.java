package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompleteKleinBottleTest {

  @Test
  void completeKleinBottlePoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)}
    };

    Grid currentGrid = new Grid("GameOfLife", "KleinBottle", "Complete", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "KleinBottle", "Complete", expectedGrid);

    assertTrue(actualNextGrid.equalsGrid(expectedNextGrid));
  }

  @Test
  void completeKleinBottlePoliciesPercolation() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.BLOCKED), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "KleinBottle", "Complete",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "KleinBottle", "Complete",  expectedGrid);

    assertTrue(actualNextGrid.equalsGrid(expectedNextGrid));
  }
}
