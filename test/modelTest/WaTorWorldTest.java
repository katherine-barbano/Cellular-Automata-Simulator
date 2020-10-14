package modelTest;

import controller.State;
import controller.states.MovingStateWithAge;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaTorWorldTest {

  @Test
  void getNextGridWaTorWorldFishMovement() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
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
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    printGrid(currentGrid);

    Grid expected1 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome1);
    printGrid(expected1);
    Grid expected2 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome2);
    printGrid(expected2);
    Grid expected3 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome3);
    printGrid(expected3);
    Grid expected4 = new Grid(SimulationType.WATOR_WORLD, possibleOutcome4);
    printGrid(expected4);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4));
  }

  @Test
  void getNextGridWaTorWorldFishBreed() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid gridTurn2 = gridTurn1.getNextGrid();
    Grid gridTurn3 = gridTurn2.getNextGrid();
    Grid gridTurn4 = gridTurn3.getNextGrid();

    int fishCount = 0;
    for(int row=0;row<3;row++) {
      for(int column=0;column<4;column++) {
        if(gridTurn4.getCell(row,column).getCurrentState().equals("Fish")) {
          fishCount++;
        }
      }
    }

    assertEquals(fishCount,4);
  }

  @Test
  void getNextGridWaTorWorldSharkBreed() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid gridTurn2 = gridTurn1.getNextGrid();
    Grid gridTurn3 = gridTurn2.getNextGrid();
    Grid gridTurn4 = gridTurn3.getNextGrid();

    int sharkCount = 0;
    for(int row=0;row<3;row++) {
      for(int column=0;column<4;column++) {
        if(gridTurn4.getCell(row,column).getCurrentState().equals("Shark")) {
          sharkCount++;
        }
      }
    }

    assertEquals(sharkCount,2);
  }

  @Test
  void getNextGridWaTorWorldSharkEatFish() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Fish"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    State[][] outcome = new State[][] {
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")},
        {new MovingStateWithAge("Empty"), new MovingStateWithAge("Shark"), new MovingStateWithAge("Empty"), new MovingStateWithAge("Empty")}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid outcomeGrid = new Grid(SimulationType.WATOR_WORLD,outcome);

    printGrid(gridTurn1);

    assertTrue(outcomeGrid.equals(gridTurn1));
  }

  @Test
  void getNextGridWaTorWorldAllFish() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge("Fish"), new MovingStateWithAge("Fish")},
        {new MovingStateWithAge("Fish"), new MovingStateWithAge("Fish")}
    };

    State[][] outcome = new State[][] {
        {new MovingStateWithAge("Fish"), new MovingStateWithAge("Fish")},
        {new MovingStateWithAge("Fish"), new MovingStateWithAge("Fish")}
    };

    Grid currentGrid = new Grid(SimulationType.WATOR_WORLD, grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid outcomeGrid = new Grid(SimulationType.WATOR_WORLD,outcome);

    assertTrue(outcomeGrid.equals(gridTurn1));
  }

  //for help debugging
  private void printGrid(Grid grid) {
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(grid.getCell(r,c).getCurrentState());
      }
      System.out.println();
    }
    System.out.println();
  }
}
