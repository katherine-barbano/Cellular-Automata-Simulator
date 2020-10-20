package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.ControllerMain;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.GraphElements.GraphView;
import view.GraphElements.SimulationGraph;

class GraphViewTest extends DukeApplicationTest {

  public static final State ALIVE = new State(GameOfLifeState.ALIVE);
  public static final State DEAD = new State(GameOfLifeState.DEAD);
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";

  public static final State[][] TEST_GRID ={{ALIVE,ALIVE,ALIVE},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
  private Stage myStage;
  private Stage myStage2;
  private SimulationView mySimulationView;
  private Scene mySimScene;
  private GraphView myView;
  private Scene myScene;
  private SimulationGraph myGraph;
  private XYChart.Data myData;
  private GridDisplay myGridDisplay;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    mySimulationView = new SimulationView(grid, ControllerMain.ENGLISH_LANGUAGE);
    mySimScene = mySimulationView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    myGridDisplay = mySimulationView.getMyGridDisplay();
    myView = new GraphView(grid, ControllerMain.ENGLISH_LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE, 0,MY_STATES,400,400);
    myStage = stage;
    myStage.setScene(myScene);
    myStage.setTitle("Game of Life");
    myStage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene(){
    myGraph=lookup("#simulationGraph").query();
  }


  @Test
  void GraphSetup(){
    sleep(1000);
    XYChart.Data data = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.DEAD).getData().get(0);
    assertEquals(3,data.getYValue());

    XYChart.Data data3 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.ALIVE).getData().get(0);
    assertEquals(6,data3.getYValue());
    assertEquals(0.0,data3.getXValue());
  }

  @Test
  void updateGraph(){
    Grid newGrid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView.updateCurrentGrid(newGrid,10);
    XYChart.Data data = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.DEAD).getData().get(0);
    assertEquals(3,data.getYValue());

    XYChart.Data data2 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.DEAD).getData().get(1);
    assertEquals(3,data2.getYValue());
    assertEquals(10.0,data2.getXValue());

    XYChart.Data data3 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.ALIVE).getData().get(0);
    assertEquals(6,data3.getYValue());
    assertEquals(0.0,data3.getXValue());

    XYChart.Data data4 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.ALIVE).getData().get(1);
    assertEquals(6,data4.getYValue());
    assertEquals(10.0,data4.getXValue());
  }


  @Test
  void testGraphAndSimulationViewMatch(){
    List<CellDisplay> simViewData = myGridDisplay.getCellListByState(GameOfLifeState.DEAD);
    List<CellDisplay> simViewData3 = myGridDisplay.getCellListByState(GameOfLifeState.ALIVE);

    Grid newGrid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView.updateCurrentGrid(newGrid,10);
    mySimulationView.updateGridDisplay(newGrid);

    XYChart.Data data = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.DEAD).getData().get(0);
    XYChart.Data data2 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.DEAD).getData().get(1);
    XYChart.Data data3 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.ALIVE).getData().get(0);
    XYChart.Data data4 = (Data) myGraph.getMyStateSeriesMap().get(GameOfLifeState.ALIVE).getData().get(1);

    List<CellDisplay> simViewData2 = myGridDisplay.getCellListByState(GameOfLifeState.DEAD);
    List<CellDisplay> simViewData4 = myGridDisplay.getCellListByState(GameOfLifeState.ALIVE);

    assertEquals(data.getYValue(),simViewData.size());
    assertEquals(data2.getYValue(),simViewData2.size());
    assertEquals(data3.getYValue(),simViewData3.size());
    assertEquals(data4.getYValue(),simViewData4.size());
  }



}