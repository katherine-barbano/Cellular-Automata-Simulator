package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import model.*; //CHECK may need to change so not all classes from model package
import view.SimulationView;

public abstract class Simulation {

  private Grid currentGrid;


  private Grid nextGrid;
  private final SimulationType simulationName;
  private final String simulationFileLocation;
  private SimulationView simulationView;
  private Group root;
  private final int rowNumber;
  private final int colNumber;
  private int[][] cells;



  public Simulation(SimulationType SimulationNameType, String simulationConfigurationName) {
    simulationName = SimulationNameType;
    simulationFileLocation = "data/gameOfLifeSample/" + simulationConfigurationName;
    rowNumber = findSizeMatrix(simulationFileLocation).get(0);
    colNumber = findSizeMatrix(simulationFileLocation).get(1);
    cells = determineStatesFromFile();
    currentGrid = new Grid(SimulationType.GAME_OF_LIFE, determineStatesFromFile());
    nextGrid = currentGrid.getNextGrid();
    simulationView = new SimulationView(currentGrid);
  }

/*//CHECK can remove this method if initializing in the constructor itself
  void initializeSimulation(Group root) { //CHECK might not need to pass root in
    //displayGridScene(currentGrid);
  }*/

  protected int [][] determineStatesFromFile() {
    String nextLine = "";
    String splitBy = ",";
    int[][] cellStatesTotal = new int[rowNumber][colNumber];
    try {
      BufferedReader br = new BufferedReader(new FileReader(simulationFileLocation));
      String firstLine = br.readLine();
      int rowCount = 0;
      while ((nextLine = br.readLine()) != null) { //returns a Boolean value
        String[] cellStates = nextLine.split(splitBy);
        for (int colCount = 0; colCount <= colNumber-1; colCount++) {
          int rc = rowCount;
          int cc = colCount;
          int tobe = Integer.parseInt(cellStates[colCount]);
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
  }

  //CHECK see why this method isn't working
/*  public List<String[]> readAll (InputStream data) {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    }
    catch (IOException | CsvException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }*/

  void displayGridScene(Grid gridToBeDisplayed) {

  }


  void updateSimulationGrid(boolean isPaused) { //CHECK should take in boolean checking if should even update
    if (! isPaused) {
      this.currentGrid = nextGrid;
      this.nextGrid = currentGrid.getNextGrid();
      simulationView.updateGridDisplay(currentGrid);
      }
    }


    public List<Integer> getMatrixSize() {
    List<Integer> sizeValues = new ArrayList<Integer>();
    sizeValues.add(rowNumber);
    sizeValues.add(colNumber);
    return sizeValues;
    }


  public SimulationView getSimulationView() {
    return simulationView;
  }


}
