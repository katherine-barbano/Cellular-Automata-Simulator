package controllerTest;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameOfLifeSimulation;
import controller.State;
import controller.stateType.GameOfLifeState;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class SimulationTest {

  @Test
  void testSimulationCreatesArray() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of game of life simulation
    toCompare.add(5);
    toCompare.add(5);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }


  @Test
  void testInitialGOLBlinker() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker.csv");
    assertEquals(GameOfLifeState.ALIVE,
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals(GameOfLifeState.ALIVE,
        mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState().getStateType());
    assertEquals(GameOfLifeState.ALIVE,
        mySimulation.getCurrentGrid().getCell(1, 3).getCurrentState().getStateType());
    //based on this initial file, and edge and neighbor policy default for game of life
    mySimulation.updateSimulation();
    assertEquals(GameOfLifeState.DEAD,
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals(GameOfLifeState.DEAD,
        mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState().getStateType());
    assertEquals(GameOfLifeState.DEAD,
        mySimulation.getCurrentGrid().getCell(1, 3).getCurrentState().getStateType());
  }


  @Test
  void testInitialGOLBlock() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlock.csv");
    assertEquals(GameOfLifeState.DEAD,
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState().getStateType());
    assertEquals(GameOfLifeState.DEAD,
        mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState().getStateType());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation();
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState().getStateType());
  }


  @Test
  void testInitialGOLTub() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLTub.csv");
    assertEquals((GameOfLifeState.ALIVE),
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.ALIVE),
        mySimulation.getCurrentGrid().getCell(1, 3).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.ALIVE),
        mySimulation.getCurrentGrid().getCell(3, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.ALIVE),
        mySimulation.getCurrentGrid().getCell(3, 3).getCurrentState().getStateType());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation();
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(1, 3).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(3, 1).getCurrentState().getStateType());
    assertEquals((GameOfLifeState.DEAD),
        mySimulation.getCurrentGrid().getCell(3, 3).getCurrentState().getStateType());
  }
}

/*
  @Test
  void testInitialGOLBoat() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBoat.csv");
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation();
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(1, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(1, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(3, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLBlinker2() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLBlinker2.csv");
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 3).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge1() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge1.csv");
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());

    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.DEAD), mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge2() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge2.csv");
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(row, col).getCurrentState());
      }
    }
    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 2).getCurrentState());
  }

  @Test
  void testInitialGOLAliveEdge3() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    mySimulation.setSimulationFileLocation("testInitialGOLAliveEdge3.csv");
    for (int row = 0; row < 2; row++) {
      for (int col = 0; col < 3; col++) {
        assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(row, col).getCurrentState());
      }
    }
    //based on this initial file, we know that the next grid should have 1 in (2,1), (2,2,) and (2,3)
    mySimulation.updateSimulation(true);
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(1, 0).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(0, 2).getCurrentState());
    assertEquals(new State(GameOfLifeState.ALIVE), mySimulation.getCurrentGrid().getCell(2, 1).getCurrentState());
  }

}

 */
