package modelTest;

import controller.states.GameOfLifeState;
import controller.State;
import model.Grid;
import model.SimulationType;
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
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, block);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBeehive() {
    State[][] beehive = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, beehive);
  }

  @Test
  void getNextGridGameOfLifeStillLifeLoaf() {
    State[][] loaf = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, loaf);
  }

  @Test
  void getNextGridGameOfLifeStillLifeBoat() {
    State[][] boat = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, boat);
  }

  @Test
  void getNextGridGameOfLifeStillLifeTub() {
    State[][] tub = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkStillLifeGridsEqual(SimulationType.GAME_OF_LIFE, tub);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBlinker() {
    State[][] blinkerInitialState = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    State[][] blinkerOneStepState = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, blinkerInitialState, blinkerOneStepState);
  }

  @Test
  void getNextGridGameOfLifeOscillatorBeacon() {
    State[][] beaconInitialState = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    State[][] beaconOneStepState = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD}
    };

    checkTwoPeriodOscillator(SimulationType.GAME_OF_LIFE, beaconInitialState, beaconOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAllAliveCellsOnEdge() {
    State[][] edgesInitialState = new State[][] {
        {GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE},
        {GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE},
        {GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE},
    };

    State[][] edgesOneStepState = new State[][] {
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeAliveCellsInCorners() {
    State[][] edgesInitialState = new State[][] {
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
    };

    State[][] edgesOneStepState = new State[][] {
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }

  @Test
  void getNextGridGameOfLifeEndWithAliveCellsOnFirstTwoRows() {
    State[][] edgesInitialState = new State[][] {
        {GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE},
        {GameOfLifeState.ALIVE, GameOfLifeState.ALIVE, GameOfLifeState.ALIVE},
        {GameOfLifeState.DEAD, GameOfLifeState.DEAD, GameOfLifeState.DEAD},
    };

    State[][] edgesOneStepState = new State[][] {
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
        {GameOfLifeState.ALIVE, GameOfLifeState.DEAD, GameOfLifeState.ALIVE},
        {GameOfLifeState.DEAD, GameOfLifeState.ALIVE, GameOfLifeState.DEAD},
    };

    checkExpectedAndActualNextStateGridsEqual(SimulationType.GAME_OF_LIFE, edgesInitialState, edgesOneStepState);
  }
}
