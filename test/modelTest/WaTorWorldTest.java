package modelTest;

import controller.State;
import controller.states.WaTorWorldState;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaTorWorldTest {

  @Test
  void getNextGridWaTorWorldFishMovement() {
    State[][] grid = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.FISH, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome1 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.FISH, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome2 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.FISH, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome3 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.FISH, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome4 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.FISH, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome1);
    Grid expected2 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome2);
    Grid expected3 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome3);
    Grid expected4 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome4);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4));
  }

  @Test
  void getNextGridWaTorWorldSharkMovement() {
    State[][] grid = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.SHARK, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome1 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.SHARK, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome2 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.SHARK, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome3 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.SHARK, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    State[][] possibleOutcome4 = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.SHARK, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome1);
    Grid expected2 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome2);
    Grid expected3 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome3);
    Grid expected4 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome4);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4));
  }

  @Test
  void getNextGridWaTorWorldFishBreed() {
    State[][] grid = new State[][] {
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.FISH, WaTorWorldState.FISH, WaTorWorldState.EMPTY},
        {WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY, WaTorWorldState.EMPTY}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    printGrid(gridTurn1);
    Grid gridTurn2 = gridTurn1.getNextGrid();
    printGrid(gridTurn2);
    Grid gridTurn3 = gridTurn2.getNextGrid();
    printGrid(gridTurn3);
    Grid gridTurn4 = gridTurn3.getNextGrid();
    printGrid(gridTurn4);
    Grid gridTurn5 = gridTurn4.getNextGrid();
    printGrid(gridTurn5);

    int fishCount = 0;
    for(int row=0;row<3;row++) {
      for(int column=0;column<4;column++) {
        if(gridTurn4.getCell(row,column).getCurrentState() == WaTorWorldState.FISH) {
          fishCount++;
        }
      }
    }

    assertEquals(fishCount,3);
  }


  //for help debugging
  private void printGrid(Grid grid) {
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(((WaTorWorldState)grid.getCell(r,c).getCurrentState()).getAge());
      }
      System.out.println();
    }
    System.out.println();
  }
}
