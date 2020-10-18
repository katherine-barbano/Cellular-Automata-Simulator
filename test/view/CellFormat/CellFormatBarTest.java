package view.CellFormat;

import static org.junit.jupiter.api.Assertions.*;

import controller.State;
import controller.StateType;
import controller.stateType.GameOfLifeState;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellDisplay;
import view.GridDisplay;
import view.SimulationView;

class CellFormatBarTest extends DukeApplicationTest {

  public static final StateType ALIVE = GameOfLifeState.ALIVE;
  public static final StateType DEAD = GameOfLifeState.DEAD;
  public static final StateType[] MY_STATES = GameOfLifeState.values();
  public static final String SIMULATION_TYPE ="GameOfLife";
  public static final String EDGE_POLICY_TYPE ="Finite";
  public static final String NEIGHBOR_POLICY_TYPE ="Complete";
  public static final State[][] TEST_GRID ={{new State(ALIVE),new State(ALIVE),new State(ALIVE)},{new State(DEAD),new State(ALIVE),new State(DEAD)},{new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  public static final State[][] TEST_GRID2 ={{new State(DEAD),new State(DEAD),new State(DEAD)},{new State(DEAD),new State(ALIVE),new State(DEAD)},{new State(ALIVE),new State(DEAD),new State(ALIVE)}};
  private SimulationView myView;
  private Scene myScene;
  private GridDisplay myGridDisplay;
  private CellFormatBar myCellBar;
  private StateChooser myStateChooser;
  private CellColorChooser myColorChooser;
  private ChangeColorButton myColorButton;
  private ImageChooser myImageChooser;
  private ChangeImageButton myImageButton;

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
    myCellBar = lookup("#cell-format-bar").query();
    myColorChooser = lookup("#color-chooser").query();
    myStateChooser = lookup("#state-chooser").query();
    myColorButton = lookup("#change-color-button").query();
    myImageChooser = lookup("#image-chooser").query();
    myImageButton = lookup("#change-image-button").query();

  }

  @Test
  void testColorChoice() {
    myStateChooser.setMySelection(ALIVE);
    myColorChooser.setMyChosenColor(CellColors.BLUE);
    clickOn(myColorButton);
    CellDisplay cell = myGridDisplay.getCellListByState(myStateChooser.getMySelection()).get(0);

    assertEquals(CellColors.BLUE, cell.getMyState().getStateColor());

    //Test that cells have Blue Color after updating the grid
    javafxRun(()->myView.updateGridDisplay(new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID2)));
    CellDisplay cell2 = myGridDisplay.getCellListByState(myStateChooser.getMySelection()).get(0);
    assertEquals(CellColors.BLUE, cell2.getMyState().getStateColor());
  }

  @Test
  void testImageChoice(){
    //Test Choosing Starry Night Image for Alive cells
    myStateChooser.setMySelection(ALIVE);
    myImageChooser.setMyChosenImage(CellColors.STARRY_NIGHT);
    clickOn(myImageButton);
    CellDisplay cell = myGridDisplay.getCellListByState(myStateChooser.getMySelection()).get(0);

    assertEquals(CellColors.STARRY_NIGHT, cell.getMyState().getStateColor());

    //Test that cells have Starry Night Images after updating the grid
    javafxRun(()->myView.updateGridDisplay(new Grid(SIMULATION_TYPE, EDGE_POLICY_TYPE,NEIGHBOR_POLICY_TYPE,TEST_GRID2)));
    CellDisplay cell2 = myGridDisplay.getCellListByState(myStateChooser.getMySelection()).get(0);
    assertEquals(CellColors.STARRY_NIGHT, cell2.getMyState().getStateColor());


  }

}