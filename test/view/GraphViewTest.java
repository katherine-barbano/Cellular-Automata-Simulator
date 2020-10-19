package view;

import static org.junit.jupiter.api.Assertions.*;

import controller.ControllerMain;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.GraphElements.SimulationGraph;

class GraphViewTest extends DukeApplicationTest {

  public static final State ALIVE = new State(GameOfLifeState.ALIVE);
  public static final State DEAD = new State(GameOfLifeState.DEAD);
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";

  public static final State[][] TEST_GRID ={{ALIVE,ALIVE,ALIVE},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
  private GraphView myView;
  private Scene myScene;
  private SimulationGraph myGraph;
  @Override
  public void start(Stage stage) throws Exception {
    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView = new GraphView(grid, ControllerMain.ENGLISH_LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
    stage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene(){
    myGraph=lookup("#simulationGraph").query();
  }


  @Test
  void GraphSetup(){
    sleep(1000);
  }

  @Test
  void updateGraph(){
    Grid newGrid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView.updateCurrentGrid(newGrid,10);
    sleep(10000);
  }

}