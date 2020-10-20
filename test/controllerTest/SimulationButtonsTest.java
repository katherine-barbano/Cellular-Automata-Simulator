package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.ControllerMain;
import controller.GameOfLifeSimulation;
import controller.Simulation;
import controller.StateType;
import javafx.scene.control.Button;
import controller.stateType.GameOfLifeState;
import javafx.scene.Scene;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.SimulationView;

public class SimulationButtonsTest extends DukeApplicationTest {

  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";

  private ControllerMain myController;
  private SimulationView mySimView;
  private Scene myScene;
  private Button myButton;

  @Test
  void testPlayButtonOnSimulationView() {
    //test initial set up
    javafxRun(() -> myController = new ControllerMain());
    Simulation newSim = new GameOfLifeSimulation();
    boolean pauseValueBefore = myController.getIsPaused();
    assertFalse(pauseValueBefore);

  }
}
