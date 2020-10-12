package model;

import controller.State;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Grid {

  public static final String CLASS_NAME_PREFIX_PROPERTIES = "classNamePrefix";
  public static final String CLASS_NAME_SUFFIX_PROPERTIES = "classNameSuffix";
  public static final String SIMULATION_TYPE_EXCEPTION_MESSAGE_PROPERTIES = "simulationTypeExceptionMessage";

  private Cell[][] cellGrid;
  private SimulationType simulationType;
  private ResourceBundle modelResources;

  /***
   * Constructor used for creating first initial grid from CSV file.
   * @param simulationType type of simulation from SimulationType enum
   * @param allStatesInCSV State[][] of all the states in the csv file
   */
  public Grid(SimulationType simulationType, State[][] allStatesInCSV) {
    modelResources = ResourceBundle.getBundle(Neighborhood.MODEL_RESOURCE_PATH);
    this.simulationType = simulationType;
    cellGrid = new Cell[allStatesInCSV.length][allStatesInCSV[0].length];
    initializeCurrentCellGrid(allStatesInCSV);
  }

  /***
   * Constructor used for creating all Grids after the initial grid. Initializes cellGrid as empty,
   * and the model should later populate it with next state data.
   * @param simulationType type of simulation from SimulationType enum
   * @param rowLength number of rows in grid
   * @param columnLength number of columns in grid
   */
  public Grid(SimulationType simulationType, int rowLength, int columnLength) {
    modelResources = ResourceBundle.getBundle(Neighborhood.MODEL_RESOURCE_PATH);
    this.simulationType = simulationType;
    cellGrid = new Cell[rowLength][columnLength];
  }

  /***
   * Checks if states of cells in current cellGrid are the same as states of cells in next grid
   * @return true if current grid is stable
   */
  public boolean currentGridIsStable() {
    return getNextGrid().equals(this);
  }

  /***
   * Call this method from controller to return a Grid object filled with cells in the next state.
   * @return Grid for one step later
   */
  public Grid getNextGrid() {
    Grid nextGridWithOldNeighborhoods = getGridWithNextCells();
    nextGridWithOldNeighborhoods.updateNeighborhoods(this);
    nextGridWithOldNeighborhoods.updateCellsFromOverlappedNeighborsAfterInitialMove();
    Grid nextGridWithNewNeighborhoods = nextGridWithOldNeighborhoods;
    return nextGridWithNewNeighborhoods;
  }

  /***
   * Iterates through all cells in the table. For each cell, populate a position to state map of all the
   * neighbors of neighbors that overlap on the center cell.
   */
  private void updateCellsFromOverlappedNeighborsAfterInitialMove() {
    for(int row = 0; row<cellGrid.length; row++) {
      for(int column = 0; column<cellGrid[0].length; column++) {
        Cell centerCell = cellGrid[row][column];
        Neighborhood centerCellNeighborhood = centerCell.getNeighborhood();
        Map<int[], State> statesOfOverlappingNeighbors = new HashMap<>();
        populateStatesOfOverlappingNeighbors(statesOfOverlappingNeighbors, row, column, centerCellNeighborhood);
        centerCell.setStatesOfOverlappingNeighbors(statesOfOverlappingNeighbors);
        cellGrid[row][column] = centerCell.getCellFromOverlappingNeighbors();
        System.out.println();
      }
    }
    updateNeighborhoods(this);
  }

  /***
   * Iterate through each neighbor of the center cell
   * @param statesOfOverlappingNeighbors
   * @param row
   * @param column
   * @param centerCellNeighborhood
   */
  private void populateStatesOfOverlappingNeighbors(Map<int[], State> statesOfOverlappingNeighbors, int row, int column, Neighborhood centerCellNeighborhood) {
    Map<int[], State> centerCellNeighborPositionToState = centerCellNeighborhood.getNeighborPositionToState();
    for (int[] neighborPosition : centerCellNeighborPositionToState.keySet()) {
      putStatesOfOverlappingNeighborsCenterCell(row, column, neighborPosition, statesOfOverlappingNeighbors);
    }
  }

  private void putStatesOfOverlappingNeighborsCenterCell(int row, int column, int[] neighborPosition, Map<int[], State> statesOfOverlappingNeighbors) {
    try{
      Cell neighborCellOfNeighbor = cellGrid[row + neighborPosition[0]][column + neighborPosition[1]];
      Neighborhood neighborhoodOfNeighbor = neighborCellOfNeighbor.getNeighborhood();
      State stateOfNeighbor = neighborhoodOfNeighbor.getStateFromNeighborPosition(neighborPosition);
      System.out.println(stateOfNeighbor);
      int[] nextPositionOfNeighbor = stateOfNeighbor.getNextPosition();
      /*if(nextPositionOfNeighbor[0] == row && nextPositionOfNeighbor[1] == column) {
        statesOfOverlappingNeighbors.put(neighborPosition,stateOfNeighbor);
      }*/
      statesOfOverlappingNeighbors.put(neighborPosition,stateOfNeighbor);
    }
    catch(NullPointerException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
  }

  private Grid getGridWithNextCells() {
    Grid nextGrid = new Grid(simulationType, cellGrid.length, cellGrid[0].length);
    for(int gridRow = 0; gridRow < cellGrid.length; gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid[0].length; gridColumn++) {
        addNextCellToNextGrid(nextGrid, gridRow, gridColumn);
      }
    }
    return nextGrid;
  }

  private void addNextCellToNextGrid(Grid nextGrid, int gridRow, int gridColumn) {
    Cell currentCell = cellGrid[gridRow][gridColumn];
    Cell nextCell = currentCell.getNextCell();
    nextGrid.addCellToGrid(nextCell, gridRow, gridColumn);
  }

  private void updateNeighborhoods(Grid oldGrid) {
    for(int gridRow = 0; gridRow < cellGrid.length; gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid[0].length; gridColumn++) {
        State[][] stateIntegerGrid = createStateIntegerGridFromCellGrid();
        Neighborhood cellNeighborhood = oldGrid.getCell(gridRow, gridColumn).getNeighborhood();
        //changed here createNeighborhoodForSimulationType(gridRow, gridColumn, stateIntegerGrid)
        Cell cell = cellGrid[gridRow][gridColumn];
        cell.setNeighborhood(cellNeighborhood);
      }
    }
  }

  /***
   * If number of rows (or number of columns) is not constant throughout entire allStatesInCSV,
   * assume that allStatesInCSV[index][index] is -1 if no Cell should be initialized for that position.
   * This method leaves an empty position in cellGrid if the state is -1 in CSV.
   */
  private void initializeCurrentCellGrid(State[][] allStatesInCSV) {
    for (int csvRow = 0; csvRow < allStatesInCSV.length; csvRow++) {
      for (int csvColumn = 0; csvColumn < allStatesInCSV[csvRow].length; csvColumn++) {
        putCellWithNeighborhoodInGrid(csvRow, csvColumn, allStatesInCSV);
      }
    }
    updateNeighborhoods(this);
  }

  private void putCellWithNeighborhoodInGrid(int csvRow, int csvColumn, State[][] allStatesInCSV) {
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(csvRow, csvColumn, allStatesInCSV);
    Cell cellInPosition = new Cell(cellNeighborhood, allStatesInCSV[csvRow][csvColumn]);
    cellGrid[csvRow][csvColumn] = cellInPosition;
  }

  private Neighborhood createNeighborhoodForSimulationType(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    try {
      //code referenced from https://java2blog.com/invoke-constructor-using-reflection-java/ provided on course website
      String classNamePrefix = modelResources.getString(CLASS_NAME_PREFIX_PROPERTIES);
      String classNameSuffix = modelResources.getString(CLASS_NAME_SUFFIX_PROPERTIES);

      Class<?> cl = Class.forName(classNamePrefix + simulationType.toString() + classNameSuffix);
      Class<?>[] type = { int.class,int.class,State[][].class};
      Constructor<?> cons = cl.getConstructor(type);
      Object[] obj = {centerCellRow,centerCellColumn,stateGrid};
      Object newInstance = cons.newInstance(obj);
      return (Neighborhood)newInstance;
    }
    catch(Exception e) {
      String simulationTypeExceptionMessage = modelResources.getString(SIMULATION_TYPE_EXCEPTION_MESSAGE_PROPERTIES);
      throw new ModelException(simulationTypeExceptionMessage);
    }
  }

  State[][] createStateIntegerGridFromCellGrid() {
    State[][] stateIntegerGrid = new State[cellGrid.length][cellGrid[0].length];
    for (int cellGridRow = 0; cellGridRow < cellGrid.length; cellGridRow++) {
      for (int cellGridColumn = 0; cellGridColumn < cellGrid[cellGridRow].length; cellGridColumn++) {
        stateIntegerGrid = cellAddedToStateIntegerGrid(cellGridRow,cellGridColumn,stateIntegerGrid);
      }
    }
    return stateIntegerGrid;
  }

  private State[][] cellAddedToStateIntegerGrid(int cellGridRow, int cellGridColumn, State[][] stateIntegerGrid) {
    if (cellGrid[cellGridRow][cellGridColumn] == null) {
      stateIntegerGrid[cellGridRow][cellGridColumn] = null;
    }
    else {
      Cell cellAtIndex = cellGrid[cellGridRow][cellGridColumn];
      stateIntegerGrid[cellGridRow][cellGridColumn] = cellAtIndex.getCurrentState();
    }
    return stateIntegerGrid;
  }

  void addCellToGrid(Cell newCell, int cellRow, int cellColumn) {
    cellGrid[cellRow][cellColumn] = newCell;
  }

  Cell[][] getCellGrid() {
    return cellGrid;
  }

  public boolean equals (Grid otherGrid) {
    Cell[][] otherGridCellMatrix = otherGrid.getCellGrid();
    Cell[][] thisGridCellMatrix = getCellGrid();

    if(otherGridCellMatrix.length!=thisGridCellMatrix.length) {
      return false;
    }
    if(otherGridCellMatrix[0].length!=thisGridCellMatrix[0].length) {
      return false;
    }

    for(int row = 0; row< thisGridCellMatrix.length; row++) {
      for(int column = 0; column< thisGridCellMatrix[0].length; column++) {
        Cell otherCell = otherGridCellMatrix[row][column];
        Cell thisCell = thisGridCellMatrix[row][column];
        boolean onlyOneCellEmpty = (otherCell==null && thisCell!=null) || (otherCell!=null && thisCell==null);
        boolean bothCellsEmpty = otherCell==null && thisCell==null;
        if(onlyOneCellEmpty || (!bothCellsEmpty && !otherCell.equals(thisCell))) {
          return false;
        }
      }
    }
    return true;
  }

  public Cell getCell(int rowNumber, int columnNumber) {
    return cellGrid[rowNumber][columnNumber];
  }

  public int getGridNumberOfRows() {
    return cellGrid.length;
  }

  public int getGridNumberOfColumns() {
    return cellGrid[0].length;
  }
}
