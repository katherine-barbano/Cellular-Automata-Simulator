package view;

import static org.junit.jupiter.api.Assertions.*;

import controller.Simulation;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class GridDisplayTest extends DukeApplicationTest {
  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";

  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},{new State(DEAD),new State(ALIVE),new State(DEAD)},{new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  private GridDisplay myGridDisplay;

  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    SimulationView simView= new SimulationView(grid,LANGUAGE);
    Scene myScene = simView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
    stage.show();

    myGridDisplay = lookup("#gridDisplay").query();
  }


  @Test
  void getCellListByState() {
    List<CellDisplay> cells = myGridDisplay.getCellListByState(GameOfLifeState.ALIVE);
    for(CellDisplay cell: cells){
      assertEquals(GameOfLifeState.ALIVE,cell.getMyStateType());
    }
  }

  @Test
  void updateCellInGrid() {
    myGridDisplay.updateCellInGrid(0,0,ALIVE);
    assertEquals(ALIVE,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());

    myGridDisplay.updateCellInGrid(0,0,DEAD);
    assertEquals(DEAD,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());

    assertThrows(IndexOutOfBoundsException.class, ()->myGridDisplay.updateCellInGrid(-1,0,ALIVE));

    CellDisplay cell = (CellDisplay) myGridDisplay.getChildren().get(0);
    clickOn(cell);
    assertEquals(DEAD,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());

    clickOn(cell);
    assertEquals(ALIVE,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());

  }

}