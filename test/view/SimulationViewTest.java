package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GameOfLifeSimulation;
import controller.GameOfLifeState;
import controller.Simulation;
import controller.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SimulationViewTest extends DukeApplicationTest {
  public static final State ALIVE = GameOfLifeState.ALIVE;
  public static final State DEAD = GameOfLifeState.DEAD;

  public static final State[][] TEST_GRID ={{ALIVE,ALIVE,ALIVE},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, TEST_GRID);
    myView = new SimulationView(grid);
    myScene = myView.setupScene(SimulationType.GAME_OF_LIFE,400,400);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
    stage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene(){
    myGridDisplay=lookup("#gridDisplay").query();
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
        int cellState= getStateNumber(cells.get(cellIndex).getId());
        assertEquals(cellState, TEST_GRID[row][col]);
      }
    }
  }


  @Test
  void TestUpdateGridDisplay() throws FileNotFoundException {
    //int[][] gridMatrix = getIntMatrixFromInputFile("data/gameOfLifeSample/testingGOL.csv");
    State[][] gridMatrix = {{ALIVE,ALIVE,ALIVE,ALIVE},{ALIVE,ALIVE,DEAD,ALIVE},{DEAD,ALIVE,ALIVE,ALIVE}};
    Grid newGrid = new Grid (SimulationType.GAME_OF_LIFE,gridMatrix);
    javafxRun(()->myView.updateGridDisplay(newGrid));

    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      int cellState=getStateNumber(cell.getId());
      assertEquals(cellState,gridMatrix[row][col]);
    }
  }


  @Test
  void testInitialGOLBlinker() throws FileNotFoundException {
    //setup initial simulation grid
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker.csv");
    Grid grid1 =mySimulation.getCurrentGrid();
    int[][] gridMatrix = getIntMatrixFromInputFile("data/GameOfLifeSample/testInitialGOLBlinker.csv");
    javafxRun(()->myView.updateGridDisplay(grid1));

    //test gridDisplay = inputFile
    List<Node> cells = myGridDisplay.getChildren();
    for(Node cell: cells){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      int cellState=getStateNumber(cell.getId());
      assertEquals(cellState,gridMatrix[row][col]);
    }

    //set up screen 2
    mySimulation.updateSimulation(true);
    Grid grid2 =mySimulation.getCurrentGrid();
    int[][] gridMatrix2 = getIntMatrixFromInputFile("data/GameOfLifeSample/testInitialGOLBlinker2.csv");
    javafxRun(()->myView.updateGridDisplay(grid2));

    //test GridDipslay = input file 2
    List<Node> cells2 = myGridDisplay.getChildren();
    for(Node cell: cells2){
      int row = myGridDisplay.getRowIndex(cell);
      int col = myGridDisplay.getColumnIndex(cell);

      int cellState=getStateNumber(cell.getId());
      assertEquals(cellState,gridMatrix2[row][col]);
    }
  }

  private int getStateNumber(String id){
    switch(id){
      case "dead": return 0;
      case "alive": return 1;
      default: return -1;
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

}