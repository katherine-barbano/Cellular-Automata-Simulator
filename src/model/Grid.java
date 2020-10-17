package model;

import controller.State;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Grid {

  public static final String CLASS_NAME_PREFIX_PROPERTIES = "classNamePrefix";
  public static final String CLASS_NAME_SUFFIX_PROPERTIES = "classNameSuffix";
  public static final String SIMULATION_TYPE_EXCEPTION_MESSAGE_PROPERTIES = "simulationTypeExceptionMessage";

  private Cell[][] cellGrid;
  private String simulationType;
  private ResourceBundle modelResources;

  /***
   * Constructor used for creating first initial grid from CSV file.
   * @param simulationType type of simulation from SimulationType enum
   * @param allStatesInCSV State[][] of all the states in the csv file
   */
  public Grid(String simulationType, State[][] allStatesInCSV) {
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
  public Grid(String simulationType, int rowLength, int columnLength) {
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
    Grid initialNextGridFromSurroundingStates = getInitialNextGrid();
    /*for(int r=0;r<3;r++) {
      for (int c=0;c<4;c++) {
        System.out.println(initialNextGridFromSurroundingStates.getCell(r,c).getCurrentState().getStateType());
        //initialNextGridFromSurroundingStates.getCell(r,c).getNeighborhood().printNeighborPositionToState();
      }
      System.out.println();
    }
    System.out.println();*/
    Grid nextGridAfterInfluentialNeighborsHaveMoved = getNextGridAfterMove(initialNextGridFromSurroundingStates);
    return nextGridAfterInfluentialNeighborsHaveMoved;
  }

  private Grid getInitialNextGrid() {
    Grid nextGridWithOldNeighborhoods = getGridWithNextCells();
    nextGridWithOldNeighborhoods.updateNeighborhoodsWithOldNeighborhoods(this);
    nextGridWithOldNeighborhoods.updateNeighborhoodsOfNeighbors();
    Grid nextGridWithNewNeighborhoods = nextGridWithOldNeighborhoods;
    return nextGridWithNewNeighborhoods;
  }

  private Grid getNextGridAfterMove(Grid initialNextGrid) {
    initialNextGrid.updateCellsFromOverlappedNeighborsAfterInitialMove();
    /*for(int r=0;r<3;r++) {
      for (int c=0;c<4;c++) {
        //System.out.println(initialNextGrid.getCell(r,c).getCurrentState().getStateType());
        initialNextGrid.getCell(r,c).getNeighborhood().printNeighborPositionToState();
      }
      System.out.println();
    }
    System.out.println();*/
    initialNextGrid.updateNeighborhoodsWithNewNeighborhoods();
    initialNextGrid.updateNeighborhoodsOfNeighbors();
    return initialNextGrid;
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
        populateStatesOfOverlappingNeighborsRedo(centerCellNeighborhood, row, column, statesOfOverlappingNeighbors);
        centerCell.setStatesOfOverlappingNeighbors(statesOfOverlappingNeighbors);

        Cell updatedCell = centerCell.getCellFromOverlappingNeighbors();
        updatedCell.setNeighborhood(centerCellNeighborhood);
        cellGrid[row][column] = updatedCell;
      }
    }
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

  private void populateStatesOfOverlappingNeighborsRedo(Neighborhood centerNeighborhood, int row, int column, Map<int[], State> statesOfOverlappingNeighbors) {
    Map<int[], Neighborhood> neighborhoodsOfNeighbors = cellGrid[row][column].getNeighborhoodsOfNeighbors();
    for(int[] neighborPosition : neighborhoodsOfNeighbors.keySet()) {
      int[] positionOfCenterCellInNeighbor = negateArray(neighborPosition);
      Neighborhood neighborhoodOfNeighbor = neighborhoodsOfNeighbors.get(neighborPosition);
      State stateOfCenterCellInNeighborsNeighbor = neighborhoodOfNeighbor.getStateFromNeighborPosition(positionOfCenterCellInNeighbor);
      statesOfOverlappingNeighbors.put(neighborPosition,stateOfCenterCellInNeighborsNeighbor);
    }
  }

  private void putStatesOfOverlappingNeighborsCenterCell(int row, int column, int[] neighborPositionRelativeToCenterCell, Map<int[], State> statesOfOverlappingNeighbors) {
    try{
      Cell neighborCellOfNeighbor = cellGrid[row + neighborPositionRelativeToCenterCell[0]][column + neighborPositionRelativeToCenterCell[1]];
      Neighborhood neighborhoodOfNeighbor = neighborCellOfNeighbor.getNeighborhood();
      int[] positionOfCenterCellInNeighborsNeighborhood = negateArray(neighborPositionRelativeToCenterCell);
      State stateOfNeighbor = neighborhoodOfNeighbor.getStateFromNeighborPosition(
          positionOfCenterCellInNeighborsNeighborhood);
      statesOfOverlappingNeighbors.put(neighborPositionRelativeToCenterCell,stateOfNeighbor);
    }
    catch(ModelException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
    catch(NullPointerException e) {
      //If index is out of bounds, this means the center cell is on the edge, and the neighbor in question does not exist. Nothing should happen in this case because edge cells do not need to keep track of neighbors beyond the edge of the grid
    }
  }

  private int[] negateArray(int[] array) {
    int[] newArray = new int[array.length];
    for(int index = 0; index<array.length; index++) {
      newArray[index] = array[index]*(-1);
    }
    return newArray;
  }

  private Map<int[], Neighborhood> getNeighborhoodsOfNeighbors(Neighborhood centerCellNeighborhood, int row, int column) {
    Map<int[], Neighborhood> neighborhoodsOfNeighbors = new HashMap<>();
    Map<int[], State> centerNeighborPositionToState = centerCellNeighborhood.getNeighborPositionToState();
    for (int[] neighborPosition : centerNeighborPositionToState.keySet()) {
      Cell neighborCellOfNeighbor = cellGrid[row + neighborPosition[0]][column + neighborPosition[1]];
      Neighborhood neighborhoodOfNeighbor = neighborCellOfNeighbor.getNeighborhood();
      neighborhoodsOfNeighbors.put(neighborPosition, neighborhoodOfNeighbor);
    }
    return neighborhoodsOfNeighbors;
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

  private void updateNeighborhoodsWithOldNeighborhoods(Grid oldGrid) {
    for(int gridRow = 0; gridRow < cellGrid.length; gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid[0].length; gridColumn++) {
        Neighborhood cellNeighborhood = oldGrid.getCell(gridRow, gridColumn).getNeighborhood();
        Cell cell = cellGrid[gridRow][gridColumn];
        cell.setNeighborhood(cellNeighborhood);
      }
    }
  }

  private void updateNeighborhoodsWithNewNeighborhoods() {
    for(int gridRow = 0; gridRow < cellGrid.length; gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid[0].length; gridColumn++) {
        updateNewNeighborhood(gridRow, gridColumn);
      }
    }
  }

  private void updateNewNeighborhood(int gridRow, int gridColumn) {
    State[][] stateIntegerGrid = createStateIntegerGridFromCellGrid();
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(gridRow, gridColumn, stateIntegerGrid);
    Cell cell = cellGrid[gridRow][gridColumn];
    cell.setNeighborhood(cellNeighborhood);
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
    updateNeighborhoodsWithOldNeighborhoods(this);
    updateNeighborhoodsOfNeighbors();
  }

  private void putCellWithNeighborhoodInGrid(int csvRow, int csvColumn, State[][] allStatesInCSV) {
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(csvRow, csvColumn, allStatesInCSV);
    Cell cellInPosition = new Cell(cellNeighborhood, allStatesInCSV[csvRow][csvColumn]);
    cellGrid[csvRow][csvColumn] = cellInPosition;
  }

  private void updateNeighborhoodsOfNeighbors() {
    for(int row = 0; row<cellGrid.length; row++) {
      for(int column = 0; column < cellGrid[0].length; column++) {
        Neighborhood centerNeighborhood = cellGrid[row][column].getNeighborhood();
        Map<int[], Neighborhood> neighborhoodOfNeighbors = getNeighborhoodsOfNeighbors(centerNeighborhood, row, column);
        cellGrid[row][column].setNeighborhoodsOfNeighbors(neighborhoodOfNeighbors);
      }
    }
  }

  private Neighborhood createNeighborhoodForSimulationType(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    try {
      //code referenced from https://java2blog.com/invoke-constructor-using-reflection-java/ provided on course website
      String classNamePrefix = modelResources.getString(CLASS_NAME_PREFIX_PROPERTIES);
      String classNameSuffix = modelResources.getString(CLASS_NAME_SUFFIX_PROPERTIES);

      Class<?> cl = Class.forName(classNamePrefix + simulationType + classNameSuffix);
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

  private State[][] createStateIntegerGridFromCellGrid() {
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

  public boolean equals (Grid otherGrid) {

    if(otherGrid.getGridNumberOfRows()!=getGridNumberOfRows()) {
      return false;
    }
    if(otherGrid.getGridNumberOfColumns()!=getGridNumberOfColumns()) {
      return false;
    }

    for(int row = 0; row< getGridNumberOfRows(); row++) {
      for(int column = 0; column< getGridNumberOfColumns(); column++) {
        Cell otherCell = otherGrid.getCell(row,column);
        Cell thisCell = getCell(row, column);
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
