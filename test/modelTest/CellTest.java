package modelTest;

import model.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
  @Test
  void cellsNotEqualState() {
    Cell aliveCell = new Cell( 1);
    Cell deadCell = new Cell( 0);
    assertFalse(aliveCell.equals(deadCell));
  }

  @Test
  void cellsEqualState() {
    Cell aliveCell1 = new Cell( 1);
    Cell aliveCell2 = new Cell( 1);
    assertTrue(aliveCell1.equals(aliveCell2));
  }
}
