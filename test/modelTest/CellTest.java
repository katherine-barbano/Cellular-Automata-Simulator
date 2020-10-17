package modelTest;

import controller.State;
import controller.stateType.GameOfLifeState;
import model.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

  @Test
  void cellsNotEqualState() {
    Cell aliveCell = new Cell(new State(GameOfLifeState.ALIVE));
    Cell deadCell = new Cell( new State(GameOfLifeState.DEAD));
    assertFalse(aliveCell.equals(deadCell));
  }

  @Test
  void cellsEqualState() {
    Cell aliveCell1 = new Cell( new State(GameOfLifeState.ALIVE));
    Cell aliveCell2 = new Cell( new State(GameOfLifeState.ALIVE));
    assertTrue(aliveCell1.equals(aliveCell2));
  }
}
