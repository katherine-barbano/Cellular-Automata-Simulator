package view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.ControllerMain;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import controller.stateType.SegregationState;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.GraphElements.GraphView;
import view.GraphElements.SimulationGraph;

public class GraphViewTestSegregation extends DukeApplicationTest {

  public static final StateType[] MY_STATES = SegregationState.values();
  public static final String SIMULATION_TYPE ="Segregation";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";

  public static final State[][] TEST_GRID =new State[][] {
      {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
      {new State(SegregationState.EMPTY), new State(SegregationState.OAGENT), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)},
      {new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY), new State(SegregationState.EMPTY)}
  };
  private Stage myStage;
  private GraphView myView;
  private Scene myScene;
  private SimulationGraph myGraph;
  private XYChart.Data myData;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
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
  void updateGridSegregation(){
    Grid newGrid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView.updateCurrentGrid(newGrid,3);

    myData = (Data) myGraph.getMyStateSeriesMap().get(SegregationState.EMPTY).getData().get(0);
    assertEquals(11,myData.getYValue());

    XYChart.Data data2 = (Data) myGraph.getMyStateSeriesMap().get(SegregationState.EMPTY).getData().get(1);
    assertEquals(11,data2.getYValue());
    assertEquals(3.0,data2.getXValue());

  }

}
