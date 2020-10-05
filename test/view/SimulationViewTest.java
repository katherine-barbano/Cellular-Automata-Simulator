package view;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class SimulationViewTest extends DukeApplicationTest {

  private Scene myScene;
  @Override
  public void start(Stage stage) throws Exception {
    int[][] testGrid={{1,1,1},{0,1,0},{1,0,1}};
    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, testGrid);
    SimulationView view = new SimulationView(grid);
    myScene = view.setupScene("GameOfLife",400,400);
    stage.setScene(myScene);
    stage.setTitle("Game of Life");
    stage.show();
  }


  @Test
  void SimulationViewTest(){

    sleep(10000);
  }

}