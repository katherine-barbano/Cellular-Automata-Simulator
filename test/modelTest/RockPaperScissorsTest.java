package modelTest;

import controller.State;
import controller.stateType.RockPaperScissorsState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

  @Test
  void getNextGridRockPaperScissorsMixThreeStates() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsRockBeatsScissors() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)},
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.ROCK)}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsScissorsBeatsPaper() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.SCISSORS)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.SCISSORS)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.SCISSORS)},
        {new State(RockPaperScissorsState.SCISSORS), new State(RockPaperScissorsState.SCISSORS)}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsPaperBeatsRock() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)},
        {new State(RockPaperScissorsState.PAPER), new State(RockPaperScissorsState.PAPER)}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsThresholdTooSmallForChange() {
    State[][] firstGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS)},
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)}
    };

    State[][] expectedGrid = new State[][] {
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.SCISSORS)},
        {new State(RockPaperScissorsState.ROCK), new State(RockPaperScissorsState.PAPER)}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
