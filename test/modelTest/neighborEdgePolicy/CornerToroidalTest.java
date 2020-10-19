package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CornerToroidalTest {

  @Test
  void cornerToroidalPoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid currentGrid = new Grid("GameOfLife", "Toroidal", "Corner", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "Toroidal", "Corner", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void cornerToroidalPoliciesPercolation() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.OPEN)}
    };

    Grid currentGrid = new Grid("Percolation", "Toroidal", "Corner",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Toroidal", "Corner",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  //for help debugging
  private void printGrid(Grid grid) {
    for(int r = 0; r<grid.getGridNumberOfRows(); r++) {
      for(int c = 0; c<grid.getGridNumberOfColumns(); c++) {
        //System.out.print(grid.getCell(r,c).getCurrentState().getStateType());
        grid.getCell(r,c).getNeighborhood().printNeighborPositionToState();
      }
      System.out.println();
    }
    System.out.println();
  }

}
