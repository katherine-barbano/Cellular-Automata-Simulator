/*
package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Group;
import model.*; //CHECK may need to change so not all classes from model package
import view.SimulationView;
import javax.swing.JOptionPane;

public abstract class Simulation {

  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private String simulationFileLocation;
  private SimulationView simulationView;
  private Group root;


  private int rowNumber;
  private int colNumber;
  private HashMap<Integer, StateType> statesForInteger;
  private HashMap<StateType, Integer> integerForStates;
  private HashMap<String, String> propertiesInformation;
 // private int[][] cells;
  private final String STORING_FILE_NAME = "data/outputGrids/";
  private final String PROPERTIES_LOCATION = "simulationProperties/";



  public Simulation(String newSimulationName) {
    this.simulationName = newSimulationName;
    readPropertiesFile(newSimulationName);
    simulationFileLocation = "data/initialConfigurations/" + propertiesInformation.get("fileName");
    currentGrid = new Grid(simulationName, propertiesInformation.get("edgePolicy"),
        propertiesInformation.get("neighborPolicy"), createStateTypes(readCellStatesFile(), getStateTypesForSimulation()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
  }


  public void readPropertiesFile(String propertiesFileName) throws ControllerException {
      try {
        String resourceName = "simulationProperties/" + propertiesFileName
            + ".properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
          props.load(resourceStream);
        }
        for (Object s : props.keySet()) {
          propertiesInformation.put(s.toString(), props.get(s).toString());
        }
      }
      catch (Exception e) {
        String improperPropertiesFileMessage = ResourceBundle.getBundle("resources/ControllerErrors").
            getString("InvalidFile");
        throw new ControllerException(improperPropertiesFileMessage);
      }
  }

//CHECK can remove this method if initializing in the constructor itself
  public void setSimulationFileLocation(String newFileLocation) {
    simulationFileLocation = "data/initialConfigurations/" + newFileLocation;
    currentGrid = new Grid(simulationName, propertiesInformation.get("edgePolicy"),
        propertiesInformation.get("neighborPolicy"), createStateTypes(readCellStatesFile(), getStateTypesForSimulation()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
    System.out.println("new simulation set");
  }

  abstract public void storeNewCellConfig(Grid gridToStore);

 // abstract public String readInPropertiesFile();

  abstract public StateType[] getStateTypesForSimulation();

  //CHECK - remove this method!
 // abstract public StateType[][] createStatesFromInteger(int[][] integerCellStates);

  public State[][] createStateTypes(int[][] integerCellStates,
    StateType[] possibleStatesForSimulation) {
    statesForInteger = new HashMap<>();
    integerForStates = new HashMap<>();
    int stateNumber = 0;
    for(StateType state : possibleStatesForSimulation) {
      integerForStates.put(state, stateNumber);
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
    State[][] cellStates = new State[integerCellStates.length][integerCellStates[0].length];
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = new State(statesForInteger.get(integerCellStates[row][col]));
      }
    }
    return cellStates;
  }

  public int[][] readCellStatesFile() throws ControllerException {
    int[][] cellStates;
    try {
      List<String[]> readFiles = readAll(new FileInputStream(simulationFileLocation));
      rowNumber = Integer.parseInt(readFiles.get(0)[0]);
      colNumber = Integer.parseInt(readFiles.get(0)[1]);
      cellStates = new int[rowNumber][colNumber];
      for (int curr = 1; curr < readFiles.size(); curr++) {
        for (int col = 0; col < colNumber; col++) {
          cellStates[curr - 1][col] = Integer.parseInt(readFiles.get(curr)[col]);
        }
      }
    } catch (Exception f){
      String incorrectConfigurationExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("InvalidConfigSize");
      throw new ControllerException(incorrectConfigurationExceptionMessage);
    }
    return cellStates;
  }

  public List<String[]> readAll (InputStream data) {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    }
    catch (IOException | CsvException e) {
      //e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public Grid getCurrentGrid() {
    return currentGrid;
  }

  public void updateSimulationGrid(boolean shouldRun) {
    if (shouldRun) {
      checkGridUpdatesInDisplay();
      updateToNextSimulation();
      simulationView.updateGridDisplay(currentGrid);
    }
  }

  public void checkGridUpdatesInDisplay(){
    Grid newGrid = simulationView.getCurrentGridInDisplay();
    this.currentGrid=newGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public void updateToNextSimulation() {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public void updateSimulation(boolean shouldRun) {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public List<Integer> getMatrixSize() {
    List<Integer> sizeValues = new ArrayList<>();
    sizeValues.add(rowNumber);
    sizeValues.add(colNumber);
    return sizeValues;
  }

  public SimulationView getSimulationView() {
    return simulationView;
  }


  public int getRowNumber() {
    return rowNumber;
  }

  public int getColNumber() {
    return colNumber;
  }


}
*/
