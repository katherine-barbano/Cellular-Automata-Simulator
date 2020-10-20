package controllerTest.SimulationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Simulation;
import controller.SpreadingOfFireSimulation;
import controller.WaTorWorldSimulation;
import controller.stateType.SpreadingOfFireState;
import controller.stateType.WaTorWorldState;
import java.util.ArrayList;
import java.util.List;
import model.Grid;
import org.junit.jupiter.api.Test;

public class WatorWorldSimulationTest {

  @Test
  void testSimulationCreatesArray() {
    Simulation mySimulation = new WaTorWorldSimulation();
    List<Integer> toCompare = new ArrayList<>();
    toCompare.add(4);
    toCompare.add(4);
    assertEquals(toCompare, mySimulation.getMatrixSize());
  }

  @Test
  void testInitialDefaultGridConfiguration() {
    Simulation mySimulation = new WaTorWorldSimulation();
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(WaTorWorldState.FISH,  myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(WaTorWorldState.SHARK, myGrid.getCell(0, 0).getCurrentState().getStateType());
    assertEquals(WaTorWorldState.EMPTY, myGrid.getCell(3, 1).getCurrentState().getStateType());

    mySimulation.updateSimulation();
    }

  @Test
  void testSettingNewConfiguration() {
    Simulation mySimulation = new WaTorWorldSimulation();
    mySimulation.setSimulationFileLocation("testingNewWatorWorldConfiguration.csv");
    Grid myGrid = mySimulation.getCurrentGrid();
    assertEquals(WaTorWorldState.FISH,  myGrid.getCell(0, 2).getCurrentState().getStateType());
    assertEquals(WaTorWorldState.SHARK, myGrid.getCell(1, 1).getCurrentState().getStateType());
    assertEquals(WaTorWorldState.EMPTY, myGrid.getCell(2, 2).getCurrentState().getStateType());
    mySimulation.updateSimulation();
    }



}
