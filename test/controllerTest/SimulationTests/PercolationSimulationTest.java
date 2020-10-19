package controllerTest.SimulationTests;

import static org.junit.jupiter.api.Assertions.*;

import controller.GameOfLifeSimulation;
import controller.PercolationSimulation;
import controller.Simulation;
import controller.State;
import controller.stateType.GameOfLifeState;
import controller.stateType.PercolationState;
import java.util.ArrayList;
import java.util.List;
import model.Grid;
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

  @Test
  void testInitialDefaultGridConfiguration() {
    Simulation mySimulation = new PercolationSimulation();
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.OPEN,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED, myGrid.getCell(2, 0).getCurrentState().getStateType());

    mySimulation.updateSimulation();

    myGrid = mySimulation.getCurrentGrid();
    assertEquals(PercolationState.WATER,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(PercolationState.WATER, myGrid.getCell(2, 2).getCurrentState().getStateType());
    assertEquals(PercolationState.BLOCKED,  myGrid.getCell(2, 0).getCurrentState().getStateType());
  }

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
}
