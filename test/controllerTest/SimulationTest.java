package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameOfLifeSimulation;
import controller.Simulation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;
import model.SimulationType;
import org.junit.jupiter.api.Test;


public class SimulationTest {

  @Test
  void testSimulationCreatesArray(){
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of game of life simulation
    toCompare.add(1);
    toCompare.add(2);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }

}
