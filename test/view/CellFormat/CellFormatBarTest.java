package view.CellFormat;

import static org.junit.jupiter.api.Assertions.*;

import controller.State;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import view.CellDisplay;
import view.GridDisplay;
import view.SimulationView;

class CellFormatBarTest extends DukeApplicationTest {

  public static final State ALIVE = new State("Alive");
  public static final State DEAD = new State("Dead");
  public static final State[][] TEST_GRID ={{ALIVE,ALIVE,ALIVE},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
  public static final State[][] TEST_GRID2 ={{DEAD,DEAD,DEAD},{DEAD,ALIVE,DEAD},{ALIVE,DEAD,ALIVE}};
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
    javafxRun(()->myView.updateGridDisplay(new Grid(SimulationType.GAME_OF_LIFE,TEST_GRID2)));
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
    javafxRun(()->myView.updateGridDisplay(new Grid(SimulationType.GAME_OF_LIFE,TEST_GRID2)));
    CellDisplay cell2 = myGridDisplay.getCellListByState(myStateChooser.getMySelection()).get(0);
    assertEquals(CellColors.STARRY_NIGHT, cell2.getMyState().getStateColor());


  }

}