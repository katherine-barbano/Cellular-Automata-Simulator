package controllerTest;

import controller.GameOfLifeSimulation;
import controller.Simulation;
import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellFormat.CellColorChooser;
import view.CellFormat.CellFormatBar;
import view.CellFormat.ChangeColorButton;
import view.CellFormat.ChangeImageButton;
import view.CellFormat.ImageChooser;
import view.CellFormat.StateChooser;
import view.GridDisplay;
import view.SimulationView;
import view.TitleBar;

public class SimulationButtonsTest extends DukeApplicationTest {

  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";

  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},
      {new State(DEAD),new State(ALIVE),new State(DEAD)},
      {new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;


  @Override
  public void start(Stage stage) throws Exception {
    //Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    Simulation mySimulation = new GameOfLifeSimulation();
    myView = new SimulationView(mySimulation.getCurrentGrid(),LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE,MY_STATES,400,400);
    stage.setScene(myScene);
   // stage.setTitle("Game of Life");
    stage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene(){
    myGridDisplay=lookup("#gridDisplay").query();
  }



  @Test
  void testButtonOnSimulationView() {
    myView.getMySimulationButtons().getMySimulationButton();
    //javafxRun(()->myView.getMySimulationButtons().);
    //System.out.println("test");
  }
}
