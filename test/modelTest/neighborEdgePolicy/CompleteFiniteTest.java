package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.PercolationState;
import controller.stateType.SpreadingOfFireState;
import controller.stateType.WaTorWorldState;
import controller.states.MovingStateWithAge;
import model.Grid;
import modelTest.neighborhood.GameOfLifeTest;
import modelTest.neighborhood.RockPaperScissorsTest;
import modelTest.neighborhood.SegregationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompleteFiniteTest {

  @Test
  void completeFinitePoliciesGameOfLife() {
    GameOfLifeTest test = new GameOfLifeTest();
    test.getNextGridGameOfLifeAliveCellsInCorners();
  }

  @Test
  void completeFinitePoliciesPercolation() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.BLOCKED), new State(PercolationState.OPEN), new State(PercolationState.WATER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER), new State(PercolationState.WATER)},
        {new State(PercolationState.WATER), new State(PercolationState.BLOCKED), new State(PercolationState.WATER), new State(PercolationState.WATER)}
    };

    Grid currentGrid = new Grid("Percolation", "Finite", "Complete",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("Percolation", "Finite", "Complete",  expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void completeFinitePoliciesRockPaperScissors() {
    RockPaperScissorsTest test = new RockPaperScissorsTest();
    test.getNextGridRockPaperScissorsMixThreeStates();
  }

  @Test
  void completeFinitePoliciesSegregation() {
    SegregationTest test = new SegregationTest();
    test.getNextGridSegregationUnsatisfiedXAgent();
  }

  @Test
  void completeFinitePoliciesSpreadingOfFire() {
    State[][] firstGrid = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.BURNING)}
    };

    State[][] expectGrid1 = new State[][] {
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectGrid2 = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectGrid3 = new State[][] {
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)}
    };

    State[][] expectGrid4 = new State[][] {
        {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)},
        {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)}
    };



    Grid currentGrid = new Grid("SpreadingOfFire", "Finite", "Complete",  firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectGrid1Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid1);
    Grid expectGrid2Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid2);
    Grid expectGrid3Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid3);
    Grid expectGrid4Result = new Grid("SpreadingOfFire", "Finite", "Complete",  expectGrid4);

    assertTrue(actualNextGrid.equals(expectGrid1Result) || actualNextGrid.equals(expectGrid2Result) || actualNextGrid.equals(expectGrid3Result) || actualNextGrid
        .equals(expectGrid4Result));
  }

  @Test
  void completeFinitePoliciesWaTorWorld() {
    State[][] grid = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.FISH), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)},
        {new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY), new MovingStateWithAge(WaTorWorldState.EMPTY)}
    };

    Grid currentGrid = new Grid("WaTorWorld", "Finite", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome2);
    Grid expected3 = new Grid("WaTorWorld", "Finite", "Rectangle", possibleOutcome3);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3));
  }
}
