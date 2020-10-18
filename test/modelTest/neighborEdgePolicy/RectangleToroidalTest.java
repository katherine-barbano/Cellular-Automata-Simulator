package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import controller.stateType.RockPaperScissorsState;
import controller.stateType.SegregationState;
import controller.states.MovingState;
import model.Grid;
import modelTest.neighborhood.PercolationTest;
import modelTest.neighborhood.SpreadingOfFireTest;
import modelTest.neighborhood.WaTorWorldTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RectangleToroidalTest {

  @Test
  void rectangleFinitePoliciesGameOfLife() {
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

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void rectangleFinitePoliciesPercolation() {
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

    printGrid(actualNextGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void rectangleFinitePoliciesRockPaperScissors() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    Grid currentGrid = new Grid("RockPaperScissors", "Finite", "Rectangle", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("RockPaperScissors", "Finite", "Rectangle", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void rectangleFinitePoliciesSegregation() {
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


    Grid currentGrid = new Grid("Segregation", "Finite", "Rectangle", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("Segregation", "Finite", "Rectangle", possibleOutcome1);
    Grid expected2 = new Grid("Segregation", "Finite", "Rectangle", possibleOutcome2);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2));
  }

  @Test
  void rectangleFinitePoliciesSpreadingOfFire() {
    SpreadingOfFireTest test = new SpreadingOfFireTest();
    test.getNextStateSpreadingOfFireWithFire();
  }

  @Test
  void rectangleFinitePoliciesWaTorWorld() {
    WaTorWorldTest test = new WaTorWorldTest();
    test.getNextGridWaTorWorldFishMovement();
  }

  //for help debugging
  private void printGrid(Grid grid) {
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(grid.getCell(r,c).getCurrentState().getStateType());
      }
      System.out.println();
    }
    System.out.println();
  }
}
