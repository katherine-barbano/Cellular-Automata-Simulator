package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
    //rowNumber = findSizeMatrix(simulationFileLocation).get(0);
    //colNumber = findSizeMatrix(simulationFileLocation).get(1);
    //cells = determineStatesFromFile();
    //currentGrid = new Grid(SimulationType.GAME_OF_LIFE, determineStatesFromFile());
    //currentGrid = new Grid(SimulationNameType, readCellStatesFile());
    currentGrid = new Grid(SimulationNameType, createStatesFromInteger(readCellStatesFile()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
    //readPropertiesFile("GameOfLife.properties");
  }



  public String readPropertiesFile(String propertiesFileName) {
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
        //InputStream toRead = Simulation.class.getClassLoader().getResourceAsStream(
         //   "Testing.properties");
        //InputStream is = Main.class.getResourceAsStream(name);
        //ResourceBundle test = ResourceBundle.getBundle("resources.View");
        //ResourceBundle trying = ResourceBundle.getBundle("data/simulationProperties.GameOfLife");
        //ResourceBundle resources = ResourceBundle.getBundle("Testing");
        //InputStream trying = Main.class.getClassLoader().getResourceAsStream("resources.Testing");
        //String output = Main.class.getClassLoader().getResourceAsStream(resources.getString("amma"));
        //System.out.println(Simulation.class.getClassLoader().getResourceAsStream(trying.getString("kind")));
        //System.out.println(Simulation.class.getClassLoader().getResourceAsStream(trying.getString("amma")));
        //System.out.println("loaded now");
      }
      catch (Exception e) {
        String improperPropertiesFileMessage = ResourceBundle.getBundle("resources/ControllerErrors").
            getString("InvalidFile");
        throw new ControllerException(improperPropertiesFileMessage);
      }
      return null;
    }

//CHECK can remove this method if initializing in the constructor itself
  public void setSimulationFileLocation(String newFileLocation) { //CHECK might not need to pass root in
    simulationFileLocation = "data/gameOfLifeSample/" + newFileLocation;
    //rowNumber = findSizeMatrix(simulationFileLocation).get(0);
    //colNumber = findSizeMatrix(simulationFileLocation).get(1);
    //cells = determineStatesFromFile();
    //currentGrid = new Grid(SimulationType.GAME_OF_LIFE, determineStatesFromFile());
    //currentGrid = new Grid(simulationName, readCellStatesFile());
    currentGrid = new Grid(simulationName, createStatesFromInteger(readCellStatesFile()));
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
    //simulationView.setupScene("gameOfLife", )
    System.out.println("new simulation set");
  }


  abstract public void storeNewCellConfig(boolean shouldStore, Grid gridToStore);

  abstract public String readInPropertiesFile();

  abstract public State[][] createStatesFromInteger(int[][] integerCellStates);

/*
  protected int [][] determineStatesFromFile() {
    String nextLine = "";
    String splitBy = ",";
    int[][] cellStatesTotal = new int[rowNumber][colNumber];
    try {
      BufferedReader br = new BufferedReader(new FileReader(simulationFileLocation));
      int rowCount = 0;
      String firstLine = br.readLine();
      while ((nextLine = br.readLine()) != null) { //returns a Boolean value
        String[] cellStates = nextLine.split(splitBy);
        for (int colCount = 0; colCount <= colNumber-1; colCount++) {
          cellStatesTotal[rowCount][colCount] = (Integer.parseInt(cellStates[colCount]));
        }
        rowCount++;
      }
    }
     catch (IOException ioException) {
       System.out.println("unable to create full matrix");
    }
    return cellStatesTotal;
  }


  private List<Integer> findSizeMatrix(String simulationFileLocation){
    int numberRows = 0;
    int numberCols = 0;
    String line = "";
    List<Integer> numberData = new ArrayList<>();
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(simulationFileLocation));
      while ((line = br.readLine()) != null)   //returns a Boolean value
      {
        numberRows = Integer.parseInt(line.split(",")[0]);
        numberCols = Integer.parseInt(line.split(",")[1]);
        break;
        }
    }
    catch (IOException e)
    {
      System.out.println("not working");
    }
    numberData.add(numberRows);
    numberData.add(numberCols);
    return numberData;
  }*/

  public int[][] readCellStatesFile() {
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
      e.printStackTrace();
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
