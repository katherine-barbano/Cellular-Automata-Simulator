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
  public static final State ALIVE = new State(GameOfLifeState.ALIVE);
  public static final State DEAD = new State(GameOfLifeState.DEAD);
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";

  public static final State[][] TEST_GRID ={{ALIVE,ALIVE,ALIVE},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView = new SimulationView(grid);
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
    assertEquals(DEAD,cell.getMyState());

    assertEquals(DEAD,myGridDisplay.getMyGrid().getCell(0,0).getCurrentState());
    assertEquals(DEAD,myView.getCurrentGridInDisplay().getCell(0,0).getCurrentState());

  }

  @Test
  void UISetup(){
    sleep(1000);
  }


  @Test
  void TestGridCreated(){
    sleep(500);
    List<Node> cells = myGridDisplay.getChildren();
    for(int row=0;row< TEST_GRID.length;row++){
      for(int col=0; col<TEST_GRID[row].length;col++){
        int cellIndex = TEST_GRID[row].length *row +col;
        CellDisplay cellDisplay= (CellDisplay) cells.get(cellIndex);
        State cellState = cellDisplay.getMyState();
        assertEquals(cellState, TEST_GRID[row][col]);
      }
    }
  }


  @Test
  void TestUpdateGridDisplay() throws FileNotFoundException {
    //int[][] gridMatrix = getIntMatrixFromInputFile("data/gameOfLifeSample/testingGOL.csv");
    State[][] gridMatrix = {{ALIVE,ALIVE,ALIVE,ALIVE},{ALIVE,ALIVE,DEAD,ALIVE},{DEAD,ALIVE,ALIVE,ALIVE}};
    Grid newGrid = new Grid (SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,gridMatrix);
    javafxRun(()->myView.updateGridDisplay(newGrid));

    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      State cellState=cellDisplay.getMyState();
      assertEquals(cellState,gridMatrix[row][col]);
    }
  }

/*
  @Test
  void testInitialGOLBlinker() throws FileNotFoundException {
    //setup initial simulation grid
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker.csv");
    Grid grid1 =mySimulation.getCurrentGrid();
    State[][] gridMatrix = mySimulation.createStatesFromInteger(getIntMatrixFromInputFile("data/GameOfLifeSample/testInitialGOLBlinker.csv"));
    javafxRun(()->myView.updateGridDisplay(grid1));

    //test gridDisplay = inputFile
    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      State cellState=cellDisplay.getMyState();
      assertEquals(cellState,gridMatrix[row][col]);
    }

    //set up screen 2
    mySimulation.updateSimulation(true);
    Grid grid2 =mySimulation.getCurrentGrid();
    State[][] gridMatrix2 =mySimulation.createStatesFromInteger(getIntMatrixFromInputFile("data/GameOfLifeSample/testInitialGOLBlinker2.csv"));
    javafxRun(()->myView.updateGridDisplay(grid2));

    //test GridDipslay = input file 2
    List<Node> cells2 = myGridDisplay.getChildren();
    for(Node cell: cells2){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      CellDisplay cellDisplay = (CellDisplay) cell;
      State cellState=cellDisplay.getMyState();
      assertEquals(cellState,gridMatrix2[row][col]);
    }
  }


  private int[][] getIntMatrixFromInputFile(String inputFile) throws FileNotFoundException {
    File file = new File(inputFile);
    Scanner s = new Scanner(file);

    String dimensions = s.nextLine();
    String[] sizeArray = dimensions.split(",");
    int rows = Integer.parseInt(sizeArray[0]);
    int cols = Integer.parseInt(sizeArray[1]);

    int[][] intMatrix = new int[rows][cols];
    for(int i=0;i<rows;i++){
      String states = s.nextLine();
      String[] stateArray=states.split(",");

      for(int j=0;j<cols;j++){
        intMatrix[i][j]=Integer.parseInt(stateArray[j]);
      }
    }
    return intMatrix;
  }
*/
}