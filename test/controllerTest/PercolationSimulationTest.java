package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameOfLifeSimulation;
import controller.PercolationSimulation;
import controller.Simulation;
import controller.State;
import controller.stateType.GameOfLifeState;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class PercolationSimulationTest {

  @Test
  void testSimulationCreatesArray() {
    Simulation mySimulation = new PercolationSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of percolation simulation
    toCompare.add(3);
    toCompare.add(4);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }
}
