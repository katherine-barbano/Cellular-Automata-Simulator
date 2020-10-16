package modelTest;

import controller.State;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

  private void checkStillLifeGridsEqual(SimulationType simulationType, State[][] initialMatrix) {
    Grid currentGrid = new Grid(simulationType, initialMatrix);
    Grid nextGrid = currentGrid.getNextGrid();
    assertTrue(currentGrid.equals(nextGrid));
  }

  private void checkTwoPeriodOscillator(SimulationType simulationType, State[][] gridInitialState, State[][] gridOneStepState) {
    checkExpectedAndActualNextStateGridsEqual(simulationType, gridInitialState, gridOneStepState);
    checkExpectedAndActualNextStateGridsEqual(simulationType, gridOneStepState, gridInitialState);
  }

  private void checkExpectedAndActualNextStateGridsEqual(SimulationType simulationType, State[][] initialState, State[][] expectedNextState) {
    Grid currentGrid = new Grid(simulationType, initialState);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expectedNextGrid = new Grid(simulationType, expectedNextState);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
  }

  @Test
  void getNextGridGameOfLifeStillLifeBlock() {
    State[][] block = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, block);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBeehive() {
    State[][] beehive = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, beehive);
  }

  @Test
  void getNextGridGameOfLifeStillLifeLoaf() {
    State[][] loaf = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, loaf);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBoat() {
    State[][] boat = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, boat);
  }

  @Test
  void getNextGridGameOfLifeStillLifeTub() {
    State[][] tub = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, tub);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBlinker() {
    State[][] blinkerInitialState = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    State[][] blinkerOneStepState = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, blinkerInitialState, blinkerOneStepState);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBeacon() {
    State[][] beaconInitialState = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    State[][] beaconOneStepState = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Alive"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Alive"), new State("Alive"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead"), new State("Dead")}
    };

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, beaconInitialState, beaconOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAllAliveCellsOnEdge() {
    State[][] edgesInitialState = new State[][] {
        {new State("Alive"), new State("Alive"), new State("Alive")},
        {new State("Alive"), new State("Alive"), new State("Alive")},
        {new State("Alive"), new State("Alive"), new State("Alive")},
    };

    State[][] edgesOneStepState = new State[][] {
        {new State("Alive"), new State("Dead"), new State("Alive")},
        {new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Alive"), new State("Dead"), new State("Alive")},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAliveCellsInCorners() {
    State[][] edgesInitialState = new State[][] {
        {new State("Alive"), new State("Dead"), new State("Alive")},
        {new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Alive"), new State("Dead"), new State("Alive")},
    };

    State[][] edgesOneStepState = new State[][] {
        {new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead")},
        {new State("Dead"), new State("Dead"), new State("Dead")},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeEndWithAliveCellsOnFirstTwoRows() {
    State[][] edgesInitialState = new State[][] {
        {new State("Alive"), new State("Alive"), new State("Alive")},
        {new State("Alive"), new State("Alive"), new State("Alive")},
        {new State("Dead"), new State("Dead"), new State("Dead")},
    };

    State[][] edgesOneStepState = new State[][] {
        {new State("Alive"), new State("Dead"), new State("Alive")},
        {new State("Alive"), new State("Dead"), new State("Alive")},
        {new State("Dead"), new State("Alive"), new State("Dead")},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }
}
