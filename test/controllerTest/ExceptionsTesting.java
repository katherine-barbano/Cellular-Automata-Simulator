package controllerTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.ControllerException;
import controller.GameOfLifeSimulation;
import org.junit.jupiter.api.Test;

public class ExceptionsTesting {
  @Test
  void testInvalidLocationCSVFile() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //located in output grids folder and not in initial configurations folder
    String invalidFileLocation = "newGameOfLife.csv";
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation(invalidFileLocation));
  }

  @Test
  void testInvalidFileName() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //This is not a valid file name
    String invalidFileName = "notValidName";
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation(invalidFileName));
  }

  @Test
  void testIncorrectRowColListedFile() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //This file is incorrectly formatted - it lists 3 rows and 4 cols but does not contain that many values
    String incorrectConfigurationFile = "incorrectRowColFile.csv";
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation(incorrectConfigurationFile));
  }

  @Test
  void testIncorrectFormattingFile() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //This file is incorrectly formatted - it contains non integer values
    String incorrectFormattingFile = "incorrectFormattedFile.csv";
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation(incorrectFormattingFile));
  }

  @Test
  void testNegativeIntegersFile() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    String negativeValuesFile = "negativeValuesConfiguration.csv";
    //mySimulation.setSimulationFileLocation(negativeValuesFile);
    //This file is incorrectly formatted - it contains non integer values
    assertThrows(ControllerException.class, () -> mySimulation.setSimulationFileLocation(negativeValuesFile));
  }

  @Test
  void testPropertiesFileIncorrectLocation() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    String incorrectPropertiesLocation = "invalidProperties";
    assertThrows(ControllerException.class, () -> mySimulation.setNewPropertiesFile(incorrectPropertiesLocation));
  }

  @Test
  void testPropertiesFileWithNoFileNameListed() {
    GameOfLifeSimulation mySimulation = new GameOfLifeSimulation();
    //this properties file is incorrect because it indicates the configuration is based on the file
    //but it does not list a file name
    String incorrectPropertiesFile = "newPropertyFiles.invalidProperties";
    assertThrows(ControllerException.class, () -> mySimulation.setNewPropertiesFile(incorrectPropertiesFile));
  }




}
