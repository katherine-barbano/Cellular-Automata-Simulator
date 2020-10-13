package modelTest;

import controller.State;
import controller.WaTorWorldState;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaTorWorldTest {

  private void checkExpectedAndActualNextStateGridsEqual(SimulationType simulationType, State[][] initialState, State[][] expectedNextState) {
    Grid currentGrid = new Grid(simulationType, initialState);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(simulationType, expectedNextState);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
  }

  @Test
  void getNextGridWaTorWorldAllFish() {
    State[][] grid = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.FISH, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(actualNextGrid.getCell(r,c).getCurrentState());
      }
      System.out.println();
    }
  }
}
