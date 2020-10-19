package controllerTest.SimulationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.RockPaperScissorsSimulation;
import controller.Simulation;
import controller.SpreadingOfFireSimulation;
import controller.stateType.RockPaperScissorsState;
import controller.stateType.SpreadingOfFireState;
import java.util.ArrayList;
import java.util.List;
import model.Grid;
import org.junit.jupiter.api.Test;

public class SpreadingOfFireSimulationTest {

  @Test
  void testSimulationCreatesArray() {
    Simulation mySimulation = new SpreadingOfFireSimulation();
    List<Integer> toCompare = new ArrayList<>();
    toCompare.add(3);
    toCompare.add(4);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }

  @Test
  void testInitialDefaultGridConfiguration() {
    Simulation mySimulation = new SpreadingOfFireSimulation();
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 3).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.BURNING, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.TREE, myGrid.getCell(1, 0).getCurrentState().getStateType());

    mySimulation.updateSimulation();
    myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 3).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.BURNING, myGrid.getCell(1, 0).getCurrentState().getStateType());
  }

  @Test
  void testSettingNewConfiguration() {
    Simulation mySimulation = new SpreadingOfFireSimulation();
    mySimulation.setSimulationFileLocation("testingNewConfigurationFire.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.BURNING, myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.TREE, myGrid.getCell(0, 1).getCurrentState().getStateType());

    mySimulation.updateSimulation();
    myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 2).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 0).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY, myGrid.getCell(2, 2).getCurrentState().getStateType());

  }

  @Test
  void testLargerNewCellConfiguration() {
    Simulation mySimulation = new SpreadingOfFireSimulation();
    mySimulation.setSimulationFileLocation("testLargerFireSimulation.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 2).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 3).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.BURNING, myGrid.getCell(2, 2).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.TREE, myGrid.getCell(0, 1).getCurrentState().getStateType());

    mySimulation.updateSimulation();
    myGrid = mySimulation.getCurrentGrid();
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 2).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY,  myGrid.getCell(1, 3).getCurrentState().getStateType());
    assertEquals(SpreadingOfFireState.EMPTY, myGrid.getCell(2, 2).getCurrentState().getStateType());
  }

}
