package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import model.*; //CHECK may need to change so not all classes from model package

public abstract class Simulation {

  private Grid currentGrid;
  private Grid nextGrid;
  private final SimulationType simulationName;
  private final String simulationFileLocation;
  private Group root;
  private final int rowNumber;
  private final int colNumber;
  private int[][] cells;



  public Simulation(SimulationType SimulationNameType, String simulationConfigurationName) {
    simulationName = SimulationNameType;
    simulationFileLocation = "data/gameOfLifeSample/" + simulationConfigurationName;
    rowNumber = getSizeMatrix(simulationFileLocation).get(0);
    colNumber = getSizeMatrix(simulationFileLocation).get(1);
    cells = getStatesFromFile();
    //currentGrid = new Grid(SimulationType.GAME_OF_LIFE, getStatesFromFile());
    //nextGrid = currentGrid.getNextGrid(); //CHECK should set nextGrid equal to the currentGrid.getUpdateGrid();


  }

/*//CHECK can remove this method if initializing in the constructor itself
  void initializeSimulation(Group root) { //CHECK might not need to pass root in
    //currentGrid.initalizeGrid(this.simulationName); //CHECK use method in Grid
    //nextGrid = currentGrid.getNextGrid(this.simulationName); //CHECK method names?
    //displayGridScene(currentGrid);
  }*/

  private int [][] getStatesFromFile() {
    String nextline = "";
    String splitBy = ",";
    int rowValue = getSizeMatrix(simulationFileLocation).get(0);
    int colVal = getSizeMatrix(simulationFileLocation).get(1);
    int[][] cellStatesTotal = new int[rowNumber][colNumber];
    try {
      BufferedReader br = new BufferedReader(new FileReader(simulationFileLocation));
      String firstLine = br.readLine();
      int rowCount = 0;
      while ((nextline = br.readLine()) != null) { //returns a Boolean value
        String[] cellStates = nextline.split(splitBy);
        for (int colCount = 0; colCount <= cellStates.length-1; colCount++) {
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


  private List<Integer> getSizeMatrix(String simulationFileLocation){
    int numberRows = 0;
    int numberCols = 0;
    List<Integer> numberData = new ArrayList<>();
    try
    {
//parsing a CSV file into BufferedReader class constructor
      BufferedReader br = new BufferedReader(new FileReader(simulationFileLocation));
      while ((br.readLine()) != null)   //returns a Boolean value
      {
        numberCols = br.readLine().split(",").length;
        numberRows++;
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
    //CHECK depending on how Grid is storing cells
    //CHECK need to create instance of cellDisplay ?
    for (int rowCount = 0; rowCount < gridToBeDisplayed.getCellGrid().length; rowCount++) {
      for (int colCount = 0; colCount < gridToBeDisplayed.getCellGrid()[0].length; colCount++) {
        System.out.println("displaying");
      }
    }
  }

/*
  void updateSimulationGrid() { //CHECK should take in boolean checking if should even update
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getUpdateGrid(this.simulationName);
    //Need to update display
    for (int i = 0; i < currentGrid.getNumberOfCells(); i++) {
      root.getChildren().remove(currentGrid.getCell(i));
    }
    displayGridScene(currentGrid);
  };*/

}
