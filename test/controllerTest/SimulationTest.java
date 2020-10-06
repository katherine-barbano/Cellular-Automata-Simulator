package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameOfLifeSimulation;
import controller.Simulation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.SimulationType;
import org.junit.jupiter.api.Test;


public class SimulationTest {

  @Test
  void testSimulationCreatesArray() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of game of life simulation
    toCompare.add(3);
    toCompare.add(4);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }


  @Test
  void testInitialGOLBlinker() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker.csv");
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLBlock() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlock.csv");
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
  }

  @Test
  void testInitialGOLTub() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLTub.csv");
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLBoat() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBoat.csv");
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLBlinker2() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker2.csv");
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge1() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge1.csv");
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(0, mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(0, mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(0, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge2() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge2.csv");
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(1, mySimulation.getCurrentGrid().getCell(row, col).getCurrentState());
      }
    }
    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge3() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge3.csv");
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(1, mySimulation.getCurrentGrid().getCell(row, col).getCurrentState());
      }
    }
    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(1, 0).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(1, mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
  }
}
