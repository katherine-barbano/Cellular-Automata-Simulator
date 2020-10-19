package controllerTest.SimulationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.RockPaperScissorsSimulation;
import controller.SegregationSimulation;
import controller.Simulation;
import controller.stateType.RockPaperScissorsState;
import java.util.ArrayList;
import java.util.List;
import model.Grid;
import org.junit.jupiter.api.Test;

public class SegregationSimulationTest {
/*
  @Test
  void testSimulationCreatesArray() {
    Simulation mySimulation = new SegregationSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of segregation simulation
    toCompare.add(3);
    toCompare.add(4);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }

  @Test
  void testInitialDefaultGridConfiguration() {
    Simulation mySimulation = new RockPaperScissorsSimulation();
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.PAPER,  myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.ROCK, myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(0, 3).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.PAPER,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER, myGrid.getCell(0, 2).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.ROCK,  myGrid.getCell(2, 3).getCurrentState().getStateType());
  }

  @Test
  void testSettingNewConfiguration() {
    Simulation mySimulation = new RockPaperScissorsSimulation();
    mySimulation.setSimulationFileLocation("testingNewRPSConfiguration.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.ROCK,  myGrid.getCell(2, 1).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER, myGrid.getCell(2, 3).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(3, 3).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.SCISSORS,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(3, 3).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(2, 3).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER,  myGrid.getCell(2, 0).getCurrentState().getStateType());
  }

  @Test
  void testAnotherNewCellConfiguration() {
    Simulation mySimulation = new RockPaperScissorsSimulation();
    mySimulation.setSimulationFileLocation("testingAnotherNewRPSConfiguration.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.ROCK,  myGrid.getCell(2, 2).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER, myGrid.getCell(3, 2).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(4, 2).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(RockPaperScissorsState.PAPER,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS, myGrid.getCell(3, 2).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.PAPER, myGrid.getCell(2, 2).getCurrentState().getStateType());
    assertEquals(RockPaperScissorsState.SCISSORS,  myGrid.getCell(4, 2).getCurrentState().getStateType());
  }
   */
}
