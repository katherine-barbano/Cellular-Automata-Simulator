package modelTest;

import controller.State;
import controller.states.RockPaperScissorsState;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

  @Test
  void getNextGridRockPaperScissorsMixThreeStates() {
    State[][] firstGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.SCISSORS, RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER},
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.ROCK, RockPaperScissorsState.SCISSORS, RockPaperScissorsState.ROCK},
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.SCISSORS, RockPaperScissorsState.PAPER, RockPaperScissorsState.PAPER}
    };

    State[][] expectedGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER},
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER},
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.SCISSORS, RockPaperScissorsState.PAPER, RockPaperScissorsState.PAPER}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsRockBeatsScissors() {
    State[][] firstGrid = new State[][] {
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.ROCK},
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK}
    };

    State[][] expectedGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK},
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.ROCK}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsScissorsBeatsPaper() {
    State[][] firstGrid = new State[][] {
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.SCISSORS},
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.SCISSORS}
    };

    State[][] expectedGrid = new State[][] {
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.SCISSORS},
        {RockPaperScissorsState.SCISSORS, RockPaperScissorsState.SCISSORS}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsPaperBeatsRock() {
    State[][] firstGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER},
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.PAPER}
    };

    State[][] expectedGrid = new State[][] {
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.PAPER},
        {RockPaperScissorsState.PAPER, RockPaperScissorsState.PAPER}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsThresholdTooSmallForChange() {
    State[][] firstGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.SCISSORS},
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER}
    };

    State[][] expectedGrid = new State[][] {
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.SCISSORS},
        {RockPaperScissorsState.ROCK, RockPaperScissorsState.PAPER}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
