package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import controller.stateType.RockPaperScissorsState;
import controller.stateType.SegregationState;
import controller.stateType.SpreadingOfFireState;
import controller.stateType.WaTorWorldState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleToroidalTest {

  @Test
  void rectangleToroidalPoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)}
    };

    Grid currentGrid = new Grid("GameOfLife", "Toroidal", "Rectangle", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "Toroidal", "Rectangle", expectedGrid);

    assertTrue(actualNextGrid.equalsGrid(expectedNextGrid));
  }

  @Test
  void rectangleToroidalPoliciesPercolation() {
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

    Grid currentGrid = new Grid("Percolation", "Toroidal", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Toroidal", "Rectangle",  expectedGrid);

    assertTrue(actualNextGrid.equalsGrid(expectedNextGrid));
  }

  @Test
  void rectangleToroidalPoliciesRockPaperScissors() {
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

    Grid currentGrid = new Grid("RockPaperScissors", "Toroidal", "Rectangle", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("RockPaperScissors", "Toroidal", "Rectangle", expectedGrid);

    assertTrue(actualNextGrid.equalsGrid(expectedNextGrid));
  }

  @Test
  void rectangleToroidalPoliciesSegregation() {
    State[][] grid = new State[][] {
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.OAGENT)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };


    Grid currentGrid = new Grid("Segregation", "Toroidal", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("Segregation", "Toroidal", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("Segregation", "Toroidal", "Rectangle", possibleOutcome2);
    Grid expected3 = new Grid("Segregation", "Toroidal", "Rectangle", possibleOutcome3);
    Grid expected4 = new Grid("Segregation", "Toroidal", "Rectangle", possibleOutcome4);

    assertTrue(actualNextGrid.equalsGrid(expected1) || actualNextGrid.equalsGrid(expected2) || actualNextGrid.equalsGrid(expected3) || actualNextGrid.equalsGrid(expected4));
  }

  @Test
  void rectangleToroidalPoliciesSpreadingOfFire() {
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

    Grid currentGrid = new Grid("SpreadingOfFire", "Toroidal", "Rectangle",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid("SpreadingOfFire", "Toroidal", "Rectangle",  expectGrid1);
    Grid expectGrid2Result = new Grid("SpreadingOfFire", "Toroidal", "Rectangle",  expectGrid2);

    assertTrue(actualNextGrid.equalsGrid(expectGrid1Result) || actualNextGrid.equalsGrid(expectGrid2Result));
  }

  @Test
  void rectangleToroidalPoliciesWaTorWorld() {
    State[][] grid = new State[][] {
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
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
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.FISH), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.FISH)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)},
        {new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY), new State(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Toroidal", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("WaTorWorld", "Toroidal", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("WaTorWorld", "Toroidal", "Rectangle", possibleOutcome2);
    Grid expected3 = new Grid("WaTorWorld", "Toroidal", "Rectangle", possibleOutcome3);
    Grid expected4 = new Grid("WaTorWorld", "Toroidal", "Rectangle", possibleOutcome4);

    assertTrue(actualNextGrid.equalsGrid(expected1) || actualNextGrid.equalsGrid(expected2) || actualNextGrid.equalsGrid(expected3) || actualNextGrid.equalsGrid(expected4));
  }
}
