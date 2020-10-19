package controllerTest.SimulationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.PercolationSimulation;
import controller.RockPaperScissorsSimulation;
import controller.Simulation;
import controller.stateType.PercolationState;
import controller.stateType.RockPaperScissorsState;
import java.util.ArrayList;
import java.util.List;
import model.Grid;
import org.junit.jupiter.api.Test;

public class RockPaperScissorsSimulationTest {

  @Test
  void testSimulationCreatesArray() {
    Simulation mySimulation = new RockPaperScissorsSimulation();
    List<Integer> toCompare = new ArrayList<>();
    //CHECK these change depending on starting state of percolation simulation
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
/*
  @Test
  void testSettingNewConfiguration() {
    Simulation mySimulation = new PercolationSimulation();
    mySimulation.setSimulationFileLocation("testingPercolationConfigurationNew.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.OPEN,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED, myGrid.getCell(2, 2).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.WATER,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED, myGrid.getCell(2, 2).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED,  myGrid.getCell(2, 0).getCurrentState().getStateType());
  }

  @Test
  void testAnotherNewCellConfiguration() {
    Simulation mySimulation = new PercolationSimulation();
    mySimulation.setSimulationFileLocation("testPercolationConfiguration.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.OPEN,  myGrid.getCell(4, 3).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(4, 2).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED, myGrid.getCell(5, 1).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.WATER,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(4, 3).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(4, 2).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER,  myGrid.getCell(5, 0).getCurrentState().getStateType());
  }
  */
 
}
