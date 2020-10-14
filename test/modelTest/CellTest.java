package modelTest;

import controller.State;
import model.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

  @Test
  void cellsNotEqualState() {
    Cell aliveCell = new Cell(new State("Alive"));
    Cell deadCell = new Cell( new State("Dead"));
    assertFalse(aliveCell.equals(deadCell));
  }

  @Test
  void cellsEqualState() {
    Cell aliveCell1 = new Cell( new State("Alive"));
    Cell aliveCell2 = new Cell( new State("Alive"));
    assertTrue(aliveCell1.equals(aliveCell2));
  }
}
