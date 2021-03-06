package modelTest.neighborhood;

import controller.State;
import controller.stateType.WaTorWorldState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WaTorWorldTest {

  @Test
  public void getNextGridWaTorWorldFishMovement() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome2);
    Grid expected3 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome3);
    Grid expected4 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome4);

    assertTrue(actualNextGrid.equalsGrid(expected1) || actualNextGrid.equalsGrid(expected2) || actualNextGrid.equalsGrid(expected3) || actualNextGrid.equalsGrid(expected4));
  }

  @Test
  void getNextGridWaTorWorldSharkMovement() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome2);
    Grid expected3 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome3);
    Grid expected4 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome4);

    assertTrue(actualNextGrid.equalsGrid(expected1) || actualNextGrid.equalsGrid(expected2) || actualNextGrid.equalsGrid(expected3) || actualNextGrid.equalsGrid(expected4));
  }

  @Test
  void getNextGridWaTorWorldFishBreed() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid gridTurn2 = gridTurn1.getNextGrid();
    Grid gridTurn3 = gridTurn2.getNextGrid();
    Grid gridTurn4 = gridTurn3.getNextGrid();

    int fishCount = 0;
    for(int row=0;row<3;row++) {
      for(int column=0;column<4;column++) {
        if(gridTurn4.getCell(row,column).getCurrentState().equalsState(WaTorWorldState.FISH)) {
          fishCount++;
        }
      }
    }

    assertEquals(fishCount,4);
  }

  @Test
  void getNextGridWaTorWorldSharkBreed() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid gridTurn2 = gridTurn1.getNextGrid();
    Grid gridTurn3 = gridTurn2.getNextGrid();
    Grid gridTurn4 = gridTurn3.getNextGrid();
    Grid gridTurn5 = gridTurn4.getNextGrid();

    int sharkCount = 0;
    for(int row=0;row<3;row++) {
      for(int column=0;column<4;column++) {
        if(gridTurn5.getCell(row,column).getCurrentState().equalsState(WaTorWorldState.SHARK)) {
          sharkCount++;
        }
      }
    }

    assertEquals(sharkCount,3);
  }

  @Test
  void getNextGridWaTorWorldSharkEatFish() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] outcome = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid outcomeGrid = new Grid("WaTorWorld","Finite", "Rectangle", outcome);

    assertTrue(outcomeGrid.equalsGrid(gridTurn1));
  }

  @Test
  void getNextGridWaTorWorldSharkSurroundedFish() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] outcome1 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] outcome2 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] outcome3 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] outcome4 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.SHARK), new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };


    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid outcomeGrid1 = new Grid("WaTorWorld","Finite", "Rectangle", outcome1);
    Grid outcomeGrid2 = new Grid("WaTorWorld","Finite", "Rectangle", outcome2);
    Grid outcomeGrid3 = new Grid("WaTorWorld","Finite", "Rectangle", outcome3);
    Grid outcomeGrid4 = new Grid("WaTorWorld","Finite", "Rectangle", outcome4);


    assertTrue(outcomeGrid1.equalsGrid(gridTurn1) || outcomeGrid2.equalsGrid(gridTurn1) || outcomeGrid3.equalsGrid(gridTurn1) || outcomeGrid4.equalsGrid(gridTurn1));
  }


  @Test
  void getNextGridWaTorWorldAllFish() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.FISH)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.FISH)}
    };

    State[][] outcome = new State[][] {
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.FISH)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.FISH)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid gridTurn1 = currentGrid.getNextGrid();
    Grid outcomeGrid = new Grid("WaTorWorld","Finite", "Rectangle", outcome);

    assertTrue(outcomeGrid.equalsGrid(gridTurn1));
  }
}
