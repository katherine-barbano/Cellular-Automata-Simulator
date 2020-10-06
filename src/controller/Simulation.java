package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

//CHECK can remove this method if initializing in the constructor itself
  void initializeSimulation(Group root) { //CHECK might not need to pass root in

  }

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

  public Grid getCurrentGrid() {
    return currentGrid;
  }

  void updateSimulationGrid(boolean shouldRun) {
    if (shouldRun) {
      this.currentGrid = nextGrid;
      this.nextGrid = currentGrid.getNextGrid();
      simulationView.updateGridDisplay(currentGrid);
      }
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

  public void storeNewCellConfig(boolean shouldStore, Grid gridToStore) {
    if (shouldStore) {
      System.out.println("storing");
      try {
        FileWriter csvWriter = new FileWriter("data/gameOfLifeSample/new.csv");
        System.out.println("got new file");
        csvWriter.append(Integer.toString(rowNumber));
        csvWriter.append(",");
        csvWriter.append(Integer.toString(colNumber));
        csvWriter.append(",");
        csvWriter.append("\n");

        for(int row=0; row<gridToStore.getGridNumberOfRows(); row++){
          for(int col=0; col<gridToStore.getGridNumberOfColumns();col++) {
            csvWriter.append(Integer.toString(gridToStore.getCell(row,col).getCurrentState()));
            csvWriter.append(",");
          }
          csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
      } catch (IOException e) {
        System.out.println("not working");
      }
    }
  }

}
