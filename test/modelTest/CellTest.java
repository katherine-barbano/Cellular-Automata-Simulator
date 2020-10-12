package modelTest;

import controller.GameOfLifeState;
import model.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

  @Test
  void cellsNotEqualState() {
    Cell aliveCell = new Cell(GameOfLifeState.ALIVE);
    Cell deadCell = new Cell( GameOfLifeState.DEAD);
    assertFalse(aliveCell.equals(deadCell));
  }

  @Test
  void cellsEqualState() {
    Cell aliveCell1 = new Cell( GameOfLifeState.ALIVE);
    Cell aliveCell2 = new Cell( GameOfLifeState.ALIVE);
    assertTrue(aliveCell1.equals(aliveCell2));
  }
}
