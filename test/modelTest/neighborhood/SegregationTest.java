package modelTest.neighborhood;

import controller.State;
import controller.stateType.SegregationState;
import model.Grid;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SegregationTest {

  @Test
  void getNextGridSegregationUnsatisfiedOAgent() {
    State[][] grid = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome5 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome6 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome7 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome8 = new State[][] {
        {new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    Grid currentGrid = new Grid("Segregation", "Finite", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("Segregation", "Finite", "Complete", possibleOutcome1);
    Grid expected2 = new Grid("Segregation", "Finite", "Complete", possibleOutcome2);
    Grid expected3 = new Grid("Segregation", "Finite", "Complete", possibleOutcome3);
    Grid expected4 = new Grid("Segregation", "Finite", "Complete", possibleOutcome4);
    Grid expected5 = new Grid("Segregation", "Finite", "Complete", possibleOutcome5);
    Grid expected6 = new Grid("Segregation", "Finite", "Complete", possibleOutcome6);
    Grid expected7 = new Grid("Segregation", "Finite", "Complete", possibleOutcome7);
    Grid expected8 = new Grid("Segregation", "Finite", "Complete", possibleOutcome8);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4) || actualNextGrid.equals(expected5) || actualNextGrid.equals(expected6) || actualNextGrid.equals(expected7) || actualNextGrid.equals(expected8));
  }

  @Test
  void getNextGridSegregationSatisfiedOAgent() {
    State[][] grid = new State[][] {
        {new State(SegregationState.OAGENT), new State(SegregationState.OAGENT)},
        {new State(SegregationState.OAGENT), new State(SegregationState.OAGENT)}
    };

    State[][] possibleOutcome = new State[][] {
        {new State(SegregationState.OAGENT), new State(SegregationState.OAGENT)},
        {new State(SegregationState.OAGENT), new State(SegregationState.OAGENT)}
    };

    Grid currentGrid = new Grid("Segregation", "Finite", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expected1 = new Grid("Segregation", "Finite", "Complete", possibleOutcome);

    assertTrue(actualNextGrid.equals(expected1));
  }

  @Test
  public void getNextGridSegregationUnsatisfiedXAgent() {
    State[][] grid = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome1 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome2 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome3 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome4 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome5 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome6 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.XAGENT), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome7 = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    State[][] possibleOutcome8 = new State[][] {
        {new State(SegregationState.XAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
        {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
    };

    Grid currentGrid = new Grid("Segregation", "Finite", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();

    Grid expected1 = new Grid("Segregation", "Finite", "Complete", possibleOutcome1);
    Grid expected2 = new Grid("Segregation", "Finite", "Complete", possibleOutcome2);
    Grid expected3 = new Grid("Segregation", "Finite", "Complete", possibleOutcome3);
    Grid expected4 = new Grid("Segregation", "Finite", "Complete", possibleOutcome4);
    Grid expected5 = new Grid("Segregation", "Finite", "Complete", possibleOutcome5);
    Grid expected6 = new Grid("Segregation", "Finite", "Complete", possibleOutcome6);
    Grid expected7 = new Grid("Segregation", "Finite", "Complete", possibleOutcome7);
    Grid expected8 = new Grid("Segregation", "Finite", "Complete", possibleOutcome8);

    assertTrue(actualNextGrid.equals(expected1) || actualNextGrid.equals(expected2) || actualNextGrid.equals(expected3) || actualNextGrid.equals(expected4) || actualNextGrid.equals(expected5) || actualNextGrid.equals(expected6) || actualNextGrid.equals(expected7) || actualNextGrid.equals(expected8));
  }

  @Test
  void getNextGridSegregationSatisfiedXAgent() {
    State[][] grid = new State[][] {
        {new State(SegregationState.XAGENT), new State(SegregationState.XAGENT)},
        {new State(SegregationState.XAGENT), new State(SegregationState.XAGENT)}
    };

    State[][] possibleOutcome = new State[][] {
        {new State(SegregationState.XAGENT), new State(SegregationState.XAGENT)},
        {new State(SegregationState.XAGENT), new State(SegregationState.XAGENT)}
    };

    Grid currentGrid = new Grid("Segregation", "Finite", "Complete", grid);
    Grid actualNextGrid = currentGrid.getNextGrid();
    Grid expected1 = new Grid("Segregation", "Finite", "Complete", possibleOutcome);

    assertTrue(actualNextGrid.equals(expected1));
  }

  @Test
  void segregationOptionalProbability() {
    State[][] grid = new State[][] {
        {new State(SegregationState.XAGENT), new State(SegregationState.EMPTY)}
    };

    Grid currentGrid = new Grid("Segregation", "Finite", "Complete", grid, 1.0);
    double actualProbability = currentGrid.getOptionalProbability();

    State[][] expectedGrid = new State[][] {
        {new State(SegregationState.EMPTY), new State(SegregationState.XAGENT)}
    };

    Grid expectedNextGrid = new Grid("Segregation", "Finite", "Complete", expectedGrid, 1.0);
    Grid actualNextGrid = currentGrid.getNextGrid();

    assertEquals(actualProbability, 1.0);
    assertTrue(expectedNextGrid.equals(actualNextGrid));
  }
}
