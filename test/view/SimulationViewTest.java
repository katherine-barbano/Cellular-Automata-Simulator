package view;

import model.Grid;
import model.SimulationType;
import org.junit.jupiter.api.Test;

class SimulationViewTest {

  @Test
  void SimulationViewTest(){
    int[][] testGrid={{1,1,1},{0,1,0},{1,0,1}};
    Grid grid = new Grid(SimulationType.GAME_OF_LIFE, testGrid);

    SimulationView simView = new SimulationView(grid);

  }

}