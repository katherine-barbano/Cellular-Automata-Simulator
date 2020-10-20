package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GameOfLifeSimulation;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SimulationViewTest extends DukeApplicationTest {
  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";

  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},{new State(DEAD),new State(ALIVE),new State(DEAD)},{new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  State[][] blinkerInitialState = new State[][] {
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}
  };

  State[][] blinkerOneStepState = new State[][] {
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.ALIVE), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)},
      {new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD), new State(GameOfLifeState.DEAD)}};
  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView = new SimulationView(grid,LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
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
    assertEquals(DEAD,cell.getMyStateType());

    assertEquals(DEAD,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState().getStateType());
    assertEquals(DEAD,myView.getCurrentGridInDisplay().getCell(0,0).getCurrentState().getStateType());

  }

  @Test
  void UISetup(){
    sleep(10000);
  }


  @Test
  void TestGridCreated(){
    sleep(500);
    List<Node> cells = myGridDisplay.getChildren();
    for(int row=0;row< TEST_GRID.length;row++){
      for(int col=0; col<TEST_GRID[row].length;col++){
        int cellIndex = TEST_GRID[row].length *row +col;
        CellDisplay cellDisplay= (CellDisplay) cells.get(cellIndex);
        StateType cellState = cellDisplay.getMyStateType();
        assertEquals(cellState, TEST_GRID[row][col].getStateType());
      }
    }
  }


  @Test
  void TestUpdateGridDisplay() throws FileNotFoundException {
    //int[][] gridMatrix = getIntMatrixFromInputFile("data/gameOfLifeSample/testingGOL.csv");
    State[][] gridMatrix = {{new State(ALIVE),new State(ALIVE),new State(ALIVE),new State(ALIVE)},{new State(ALIVE),new State(ALIVE),new State(DEAD),new State(ALIVE)},{new State(DEAD),new State(ALIVE),new State(ALIVE),new State(ALIVE)}};
    Grid newGrid = new Grid (SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,gridMatrix);
    javafxRun(()->myView.updateGridDisplay(newGrid));

    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      StateType cellState=cellDisplay.getMyStateType();
      assertEquals(cellState,gridMatrix[row][col].getStateType());
    }
  }

  @Test
  void testInitialGOLBlinker() throws FileNotFoundException {
    //setup initial simulation grid
    Grid blinkerfirst = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE, blinkerInitialState);
    javafxRun(()->myView.updateGridDisplay(blinkerfirst));

    //test gridDisplay = inputFile
    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      StateType cellState=cellDisplay.getMyStateType();
      assertEquals(cellState,blinkerInitialState[row][col].getStateType());
    }

    //set up screen 2
    Grid grid2 =new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE, blinkerOneStepState);
    javafxRun(()->myView.updateGridDisplay(grid2));

    //test GridDipslay = input file 2
    List<Node> cells2 = myGridDisplay.getChildren();
    for(Node cell: cells2){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      StateType cellState=cellDisplay.getMyStateType();
      assertEquals(cellState,blinkerOneStepState[row][col].getStateType());
    }
  }


}