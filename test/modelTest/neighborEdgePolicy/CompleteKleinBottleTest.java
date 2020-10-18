package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import controller.stateType.RockPaperScissorsState;
import controller.stateType.SegregationState;
import controller.stateType.SpreadingOfFireState;
import controller.stateType.WaTorWorldState;
import controller.states.MovingState;
import controller.states.MovingStateWithAge;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompleteKleinBottleTest {

  @Test
  void completeKleinBottlePoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid currentGrid = new Grid("GameOfLife", "KleinBottle", "Complete", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "KleinBottle", "Complete", expectedGrid);

    printGrid(currentGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void completeKleinBottlePoliciesPercolation() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.WATER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "KleinBottle", "Complete",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "KleinBottle", "Complete",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void completeKleinBottlePoliciesRockPaperScissors() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER)}
    };

    Grid currentGrid = new Grid("RockPaperScissors", "KleinBottle", "Complete", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("RockPaperScissors", "KleinBottle", "Complete", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void completeKleinBottlePoliciesSegregation() {
    State[][] grid = new State[][] {
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };


    Grid currentGrid = new Grid("Segregation", "KleinBottle", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("Segregation", "KleinBottle", "Complete", possibleOutcome1);
    Grid expected2 = new Grid("Segregation", "KleinBottle", "Complete", possibleOutcome2);
    Grid expected3 = new Grid("Segregation", "KleinBottle", "Complete", possibleOutcome3);
    Grid expected4 = new Grid("Segregation", "KleinBottle", "Complete", possibleOutcome4);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4));
  }

  @Test
  void completeKleinBottlePoliciesSpreadingOfFire() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.BURNING)}
    };

    State[][] expectGrid1 = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectGrid2 = new State[][] {
        {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)}
    };

    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Complete",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid1);
    Grid expectGrid2Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid2);

    assertTrue(actualNextGrid.equals(expectGrid1Result) || actualNextGrid.equals(expectGrid2Result));
  }

  @Test
  void completeKleinBottlePoliciesWaTorWorld() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.FISH)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("WaTorWorld", "Finite", "Complete", possibleOutcome1);
    Grid expected2 = new Grid("WaTorWorld", "Finite", "Complete", possibleOutcome2);
    Grid expected3 = new Grid("WaTorWorld", "Finite", "Complete", possibleOutcome3);
    Grid expected4 = new Grid("WaTorWorld", "Finite", "Complete", possibleOutcome4);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4));
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
