package modelTest.neighborEdgePolicy;

import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.RockPaperScissorsState;
import controller.stateType.SegregationState;
import controller.states.MovingState;
import model.Grid;
import modelTest.neighborhood.PercolationTest;
import modelTest.neighborhood.SpreadingOfFireTest;
import modelTest.neighborhood.WaTorWorldTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleFiniteTest {

  @Test
  void rectangleFinitePoliciesGameOfLife() {
    State[][] firstGrid = new State[][] {
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
        {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
    };

    Grid currentGrid = new Grid("GameOfLife", "Finite", "Rectangle", firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid("GameOfLife", "Finite", "Rectangle", expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void rectangleFinitePoliciesPercolation() {
    PercolationTest test = new PercolationTest();
    test.getNextGridPercolationMixOfThreeStates();
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

}
