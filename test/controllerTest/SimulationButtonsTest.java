package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.ControllerMain;
import controller.GameOfLifeSimulation;
import controller.Simulation;
import controller.State;
import controller.StateType;
import view.*;
import controller.stateType.GameOfLifeState;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellFormat.CellColorChooser;
import view.CellFormat.ChangeColorButton;
import view.CellFormat.ChangeImageButton;
import view.CellFormat.ImageChooser;
import view.CellFormat.StateChooser;
import view.ControlButtons.PlayPauseButton;
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

/*  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},
      {new State(DEAD),new State(ALIVE),new State(DEAD)},
      {new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  private Simulation mySimulation;
  private SimulationView myView;
  private ControllerMain myController;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private TitleBar myTitleBar;
  private Stage currentStage;
  private Group root;
  private PlayPauseButton myButton;*/
  private final ControllerMain myGame = new ControllerMain();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  private PlayPauseButton myButton;


  @Override
  public void start(Stage stage) {
    //Grid grid = new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID);
 /*   mySimulation = new GameOfLifeSimulation();
    myView = new SimulationView(mySimulation.getCurrentGrid(),LANGUAGE);
    myScene = myController.setupScene(myController.FRAME_SIZE, myController.FRAME_SIZE, mySimulation, "GameOfLife");
    stage.setScene(myScene);
   // stage.setTitle("Game of Life");
    stage.show();*/
    //stage.setScene(myScene);
    //stage.show();

    //getUIComponentsInScene();
/*    myController = new ControllerMain();
    mySimulation = new GameOfLifeSimulation();
    root = new Group();
    myView = new SimulationView(mySimulation.getCurrentGrid(),"English");
    myScene = myView.setupScene("GameOfLife", mySimulation.getPossibleStateTypes(),
        500, 500);
    myButton = lookup("play-pause").query();*/
    //currentStage = new Stage();
    //root = new Group();
    //mySimulation = new GameOfLifeSimulation();
    //myView = new SimulationView(mySimulation.getCurrentGrid(),"English");
    //myScene = myView.setupScene("GameOfLife", mySimulation.getPossibleStateTypes(),
    //    ControllerMain.SCREEN_WIDTH, ControllerMain.SCREEN_HEIGHT);
   // return myScene;

    myScene = myGame.setupScene(ControllerMain.FRAME_SIZE, ControllerMain.FRAME_SIZE,
        myGame.getCurrentSimulation(), "GameOfLife");
    stage.setScene(myScene);
    stage.show();
    myButton = lookup("play-pause").query();
  }

  //private void getUIComponentsInScene(){
   // =lookup("#gridDisplay").query();
  //}



  @Test
  void testPlayButtonOnSimulationView() {
    boolean pauseValueBefore = myGame.getIsPaused();
    assertTrue(pauseValueBefore);
    clickOn(myButton);
    assertFalse(myGame.getIsPaused());
  }
}
