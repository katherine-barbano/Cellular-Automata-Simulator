package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.Group;
import model.*; //CHECK may need to change so not all classes from model package
import view.SimulationView;

public abstract class Simulation {

  private Grid currentGrid;
  private Grid nextGrid;
  private final SimulationType simulationName;
  private String simulationFileLocation;
  private SimulationView simulationView;
  private Group root;


  private int rowNumber;
  private int colNumber;
 // private int[][] cells;
  private final String STORING_FILE_NAME = "data/outputGrids/";
  private final String PROPERTIES_LOCATION = "simulationProperties/";



  public Simulation(SimulationType SimulationNameType, String propertiesName) {
    simulationName = SimulationNameType;
    //simulationFileLocation = "data/gameOfLifeSample/" + simulationConfigurationName;
    simulationFileLocation = "data/gameOfLifeSample/" + readPropertiesFile(SimulationNameType.toString());
    currentGrid = new Grid(SimulationNameType, createStatesFromInteger(readCellStatesFile()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
    //readPropertiesFile("GameOfLife.properties");
  }



  public String readPropertiesFile(String propertiesFileName) throws ControllerException {
      try {
        String resourceName = "simulationProperties/"+propertiesFileName + ".properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
          props.load(resourceStream);
        }
        for (Object s : props.keySet()) {
          if (s.toString().equals("fileName")) {
            return props.get(s).toString();
          }
        }
      }
      catch (Exception e) {
        String improperPropertiesFileMessage = ResourceBundle.getBundle("resources/ControllerErrors").
            getString("InvalidFile");
        throw new ControllerException(improperPropertiesFileMessage);
      }
      return "";
    }

//CHECK can remove this method if initializing in the constructor itself
  public void setSimulationFileLocation(String newFileLocation) { //CHECK might not need to pass root in
    simulationFileLocation = "data/gameOfLifeSample/" + newFileLocation;
    currentGrid = new Grid(simulationName, createStatesFromInteger(readCellStatesFile()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
    System.out.println("new simulation set");
  }

  abstract public void storeNewCellConfig(Grid gridToStore);

  abstract public String readInPropertiesFile();

  abstract public State[][] createStatesFromInteger(int[][] integerCellStates);


  public int[][] readCellStatesFile() throws ControllerException {
    int[][] cellStates = new int[0][];
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
      //System.out.println("not working");
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
      this.currentGrid = nextGrid;
      this.nextGrid = currentGrid.getNextGrid();
      simulationView.updateGridDisplay(currentGrid);
      }
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
