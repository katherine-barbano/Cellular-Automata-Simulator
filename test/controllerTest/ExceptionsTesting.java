package controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.ControllerException;
import controller.GameOfLifeSimulation;
import controller.stateType.GameOfLifeState;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExceptionsTesting {
  @Test
  void testInvalidLocationCSVFile() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //mySimulation.setSimulationFileLocation("newGameOfLife.csv");
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation("newGameOfLife.csv"));
  }

}
