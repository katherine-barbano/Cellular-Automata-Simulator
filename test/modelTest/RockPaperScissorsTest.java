package modelTest;

import controller.State;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RockPaperScissorsTest {

  @Test
  void getNextGridRockPaperScissorsMixThreeStates() {
    State[][] firstGrid = new State[][] {
        {new State("Rock"), new State("Scissors"), new State("Rock"), new State("Paper")},
        {new State("Scissors"), new State("Rock"), new State("Scissors"), new State("Rock")},
        {new State("Paper"), new State("Scissors"), new State("Paper"), new State("Paper")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Rock"), new State("Rock"), new State("Rock"), new State("Paper")},
        {new State("Scissors"), new State("Rock"), new State("Rock"), new State("Paper")},
        {new State("Paper"), new State("Scissors"), new State("Paper"), new State("Paper")}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsRockBeatsScissors() {
    State[][] firstGrid = new State[][] {
        {new State("Scissors"), new State("Rock")},
        {new State("Rock"), new State("Rock")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Rock"), new State("Rock")},
        {new State("Rock"), new State("Rock")}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsScissorsBeatsPaper() {
    State[][] firstGrid = new State[][] {
        {new State("Scissors"), new State("Scissors")},
        {new State("Paper"), new State("Scissors")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Scissors"), new State("Scissors")},
        {new State("Scissors"), new State("Scissors")}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsPaperBeatsRock() {
    State[][] firstGrid = new State[][] {
        {new State("Rock"), new State("Paper")},
        {new State("Paper"), new State("Paper")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Paper"), new State("Paper")},
        {new State("Paper"), new State("Paper")}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }

  @Test
  void getNextGridRockPaperScissorsThresholdTooSmallForChange() {
    State[][] firstGrid = new State[][] {
        {new State("Rock"), new State("Scissors")},
        {new State("Rock"), new State("Paper")}
    };

    State[][] expectedGrid = new State[][] {
        {new State("Rock"), new State("Scissors")},
        {new State("Rock"), new State("Paper")}
    };

    Grid currentGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, firstGrid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(SimulationType.ROCK_PAPER_SCISSORS, expectedGrid);

    assertTrue(actualNextGrid.equals(expectedNextGrid));
  }
}
