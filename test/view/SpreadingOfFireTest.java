package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.State;
import controller.StateType;
import controller.stateType.PercolationState;
import controller.stateType.SpreadingOfFireState;
import java.io.FileNotFoundException;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class SpreadingOfFireTest extends DukeApplicationTest {

  public static final StateType[] MY_STATES = SpreadingOfFireState.values();
  public static final String SIMULATION_TYPE ="SpreadingOfFire";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";
  public static final State[][] firstGrid = new State[][] {
      {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
      {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.BURNING)}
  };

  public static final State[][] expectedGrid = new State[][] {
      {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
      {new State(SpreadingOfFireState.TREE), new State(SpreadingOfFireState.EMPTY)}
  };

  public static final State[][] expectGrid2 = new State[][] {
      {new State(SpreadingOfFireState.EMPTY), new State(SpreadingOfFireState.EMPTY)},
      {new State(SpreadingOfFireState.BURNING), new State(SpreadingOfFireState.EMPTY)}
  };

  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;


  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,firstGrid);
    myView = new SimulationView(grid,LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    stage.setScene(myScene);
    stage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene(){
    myGridDisplay=lookup("#gridDisplay").query();
  }

  @Test
  void clickOnCell(){
    CellDisplay cell = (CellDisplay) myGridDisplay.getChildren().get(0);
    clickOn(cell);
    assertEquals(SpreadingOfFireState.BURNING,cell.getMyStateType());

    assertEquals(SpreadingOfFireState.BURNING,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.BURNING,myView.getCurrentGridInDisplay().getCell(0,0).getCurrentState().getStateType());

    clickOn(cell);
    assertEquals(SpreadingOfFireState.TREE,cell.getMyStateType());

    assertEquals(SpreadingOfFireState.TREE,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.TREE,myView.getCurrentGridInDisplay().getCell(0,0).getCurrentState().getStateType());

    clickOn(cell);
    assertEquals(SpreadingOfFireState.EMPTY,cell.getMyStateType());

    assertEquals(SpreadingOfFireState.EMPTY,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY,myView.getCurrentGridInDisplay().getCell(0,0).getCurrentState().getStateType());
  }

  @Test
  void UISetup(){
    sleep(10000);
  }


  @Test
  void TestGridCreated(){
    sleep(500);
    List<Node> cells = myGridDisplay.getChildren();
    for(int row=0;row< firstGrid.length;row++){
      for(int col=0; col<firstGrid[row].length;col++){
        int cellIndex = firstGrid[row].length *row +col;
        CellDisplay cellDisplay= (CellDisplay) cells.get(cellIndex);
        StateType cellState = cellDisplay.getMyStateType();
        assertEquals(cellState, firstGrid[row][col].getStateType());
      }
    }
  }


  @Test
  void TestUpdateGridDisplay() throws FileNotFoundException {

    Grid newGrid = new Grid (SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,expectedGrid);
    javafxRun(()->myView.updateGridDisplay(newGrid));

    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      StateType cellState=cellDisplay.getMyStateType();
      assertEquals(cellState,expectedGrid[row][col].getStateType());
    }

    Grid newGrid2 = new Grid (SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,expectGrid2);
    javafxRun(()->myView.updateGridDisplay(newGrid2));

    List<Node> cells2 = myGridDisplay.getChildren();
    for(Node cell: cells2){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      StateType cellState=cellDisplay.getMyStateType();
      assertEquals(cellState,expectGrid2[row][col].getStateType());
    }
  }

}
