package modelTest;

import controller.State;
import controller.stateType.SegregationState;
import controller.states.MovingState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SegregationTest {

  @Test
  void getNextGridSegregationUnsatisfiedOAgent() {
    State[][] grid = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome5 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome6 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome7 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome8 = new State[][] {
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    Grid currentGrid = new Grid(SimulationType.SEGREGATION, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid(SimulationType.SEGREGATION, possibleOutcome1);
    Grid expected2 = new Grid(SimulationType.SEGREGATION, possibleOutcome2);
    Grid expected3 = new Grid(SimulationType.SEGREGATION, possibleOutcome3);
    Grid expected4 = new Grid(SimulationType.SEGREGATION, possibleOutcome4);
    Grid expected5 = new Grid(SimulationType.SEGREGATION, possibleOutcome5);
    Grid expected6 = new Grid(SimulationType.SEGREGATION, possibleOutcome6);
    Grid expected7 = new Grid(SimulationType.SEGREGATION, possibleOutcome7);
    Grid expected8 = new Grid(SimulationType.SEGREGATION, possibleOutcome8);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4) || actualNextGrid.equals(expected5) || actualNextGrid.equals(expected6) || actualNextGrid.equals(expected7) || actualNextGrid.equals(expected8));
  }

  @Test
  void getNextGridSegregationSatisfiedOAgent() {
    State[][] grid = new State[][] {
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.OAGENT)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.OAGENT)}
    };

    State[][] possibleOutcome = new State[][] {
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.OAGENT)},
        {new MovingState(SegregationState.OAGENT), new MovingState(SegregationState.OAGENT)}
    };

    Grid currentGrid = new Grid(SimulationType.SEGREGATION, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expected1 = new Grid(SimulationType.SEGREGATION, possibleOutcome);

    assertTrue(actualNextGrid.equals(expected1));
  }

  @Test
  void getNextGridSegregationUnsatisfiedXAgent() {
    State[][] grid = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome5 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome6 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome7 = new State[][] {
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome8 = new State[][] {
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)},
        {new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY), new MovingState(SegregationState.EMPTY)}
    };

    Grid currentGrid = new Grid(SimulationType.SEGREGATION, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid(SimulationType.SEGREGATION, possibleOutcome1);
    Grid expected2 = new Grid(SimulationType.SEGREGATION, possibleOutcome2);
    Grid expected3 = new Grid(SimulationType.SEGREGATION, possibleOutcome3);
    Grid expected4 = new Grid(SimulationType.SEGREGATION, possibleOutcome4);
    Grid expected5 = new Grid(SimulationType.SEGREGATION, possibleOutcome5);
    Grid expected6 = new Grid(SimulationType.SEGREGATION, possibleOutcome6);
    Grid expected7 = new Grid(SimulationType.SEGREGATION, possibleOutcome7);
    Grid expected8 = new Grid(SimulationType.SEGREGATION, possibleOutcome8);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4) || actualNextGrid.equals(expected5) || actualNextGrid.equals(expected6) || actualNextGrid.equals(expected7) || actualNextGrid.equals(expected8));
  }

  @Test
  void getNextGridSegregationSatisfiedXAgent() {
    State[][] grid = new State[][] {
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.XAGENT)},
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.XAGENT)}
    };

    State[][] possibleOutcome = new State[][] {
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.XAGENT)},
        {new MovingState(SegregationState.XAGENT), new MovingState(SegregationState.XAGENT)}
    };

    Grid currentGrid = new Grid(SimulationType.SEGREGATION, grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expected1 = new Grid(SimulationType.SEGREGATION, possibleOutcome);

    assertTrue(actualNextGrid.equals(expected1));
  }

  //for help debugging
  private void printGrid(Grid grid) {
    for(int r = 0; r<3; r++) {
      for(int c = 0; c<4; c++) {
        System.out.print(grid.getCell(r,c).getCurrentState());
      }
      System.out.println();
    }
    System.out.println();
  }

}
