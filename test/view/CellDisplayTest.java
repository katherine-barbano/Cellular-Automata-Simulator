package view;

import static org.junit.jupiter.api.Assertions.*;

import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class CellDisplayTest extends DukeApplicationTest {
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
  private GridDisplay myGridDisplay;
  private CellDisplay myCellDisplay;
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
    myCellDisplay = (CellDisplay) myGridDisplay.getChildren().get(0);
  }

  @Test
  void testClick(){
    clickOn(myCellDisplay);
    assertEquals(myCellDisplay.getMyStateType(),GameOfLifeState.DEAD);
    assertEquals(myCellDisplay.getCurrentColor(),SimulationView.STATE_COLOR_MAP.get(GameOfLifeState.DEAD));

    clickOn(myCellDisplay);
    assertEquals(myCellDisplay.getMyStateType(),GameOfLifeState.ALIVE);
    assertEquals(myCellDisplay.getCurrentColor(),SimulationView.STATE_COLOR_MAP.get(GameOfLifeState.ALIVE));

  }

  @Test
  void testGetNextState()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method nextStateMethod = myCellDisplay.getClass().getDeclaredMethod("getNextState", CellDisplay.class);
    nextStateMethod.setAccessible(true);
    nextStateMethod.invoke(myCellDisplay,myCellDisplay.getMyStateType());
  }



}