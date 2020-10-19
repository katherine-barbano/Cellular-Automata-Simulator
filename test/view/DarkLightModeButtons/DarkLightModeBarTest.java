package view.DarkLightModeButtons;

import static org.junit.jupiter.api.Assertions.*;

import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.SimulationView;

class DarkLightModeBarTest extends DukeApplicationTest {

  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final String LANGUAGE="English";
  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},{new State(DEAD),new State(ALIVE),new State(DEAD)},{new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  private SimulationView myView;
  private Scene myScene;
  private Button myDarkButton;
  private Button myLightButton;

  @Override
  public void start(Stage stage) throws Exception {

    Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
    myView = new SimulationView(grid, LANGUAGE);
    myScene = myView.setupScene(SIMULATION_TYPE,MY_STATES,500,500);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
    stage.show();

    getUIComponentsInScene();
  }

  private void getUIComponentsInScene() {
    myDarkButton = lookup("#dark-button").query();
    myLightButton = lookup("#light-button").query();
  }

  @Test
  void checkSwitchMode(){
    clickOn(myDarkButton);
    assertTrue(myScene.getStylesheets().contains(SimulationView.RESOURCES+SimulationView.DARK_MODE_CSS));

    clickOn(myLightButton);
    assertFalse(myScene.getStylesheets().contains(SimulationView.RESOURCES+SimulationView.DARK_MODE_CSS));

  }

}