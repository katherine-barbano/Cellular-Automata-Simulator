package model;

public class Grid {

  private Cell[][] cellGrid;
  private SimulationType simulationType;

  /***
   * Constructor used for creating first initial grid from CSV file.
   * @param simulationType type of simulation from SimulationType enum
   * @param allStatesInCSV int[][] of all the states in the csv file
   */
  public Grid(SimulationType simulationType, int[][] allStatesInCSV) {
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
    this.simulationType = simulationType;
    cellGrid = new Cell[rowLength][columnLength];
  }

  /***
   * Checks if states of cells in current cellGrid are the same as states of cells in next grid
   * @return true if current grid is stable
   */
  public boolean currentGridIsStable() {
    //TODO: do this method
    getNextGrid();
    return true;
  }

  /***
   * Call this method from controller to return a Grid object filled with cells in the next state.
   * @return Grid for one step later
   */
  public Grid getNextGrid() {
    Grid nextGridWithOldNeighborhoods = getGridWithNextCells();
    nextGridWithOldNeighborhoods.updateNeighborhoods();
    Grid nextGridWithNewNeighborhoods = nextGridWithOldNeighborhoods;
    return nextGridWithNewNeighborhoods;
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

  private void updateNeighborhoods() {
    for(int gridRow = 0; gridRow < cellGrid.length; gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid[0].length; gridColumn++) {
        int[][] stateIntegerGrid = createStateIntegerGridFromCellGrid();
        Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(gridRow, gridColumn, stateIntegerGrid);
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
  private void initializeCurrentCellGrid(int[][] allStatesInCSV) {
    for (int csvRow = 0; csvRow < allStatesInCSV.length; csvRow++) {
      for (int csvColumn = 0; csvColumn < allStatesInCSV[csvRow].length; csvColumn++) {
        if (allStatesInCSV[csvRow][csvColumn] != -1) {
          putCellWithNeighborhoodInGrid(csvRow, csvColumn, allStatesInCSV);
        }
      }
    }
  }

  private void putCellWithNeighborhoodInGrid(int csvRow, int csvColumn, int[][] allStatesInCSV) {
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(csvRow, csvColumn, allStatesInCSV);
    Cell cellInPosition = new Cell (cellNeighborhood, allStatesInCSV[csvRow][csvColumn]);
    cellGrid[csvRow][csvColumn] = cellInPosition;
  }

  /***
   * Must edit this to create a new type of Neighborhood when adding a new type of simulation.
   */
  private Neighborhood createNeighborhoodForSimulationType(int centerCellRow, int centerCellColumn, int[][] stateIntegerGrid) {
    switch (simulationType) {
      case GAME_OF_LIFE:
        return new GameOfLifeNeighborhood(centerCellRow, centerCellColumn, stateIntegerGrid);
      default:
        //should never be reached, because all simulation types are listed above
        return null;
    }
  }

  int[][] createStateIntegerGridFromCellGrid() {
    int[][] stateIntegerGrid = new int[cellGrid.length][cellGrid[0].length];
    for (int cellGridRow = 0; cellGridRow < cellGrid.length; cellGridRow++) {
      for (int cellGridColumn = 0; cellGridColumn < cellGrid[cellGridRow].length; cellGridColumn++) {
        stateIntegerGrid = cellAddedToStateIntegerGrid(cellGridRow,cellGridColumn,stateIntegerGrid);
      }
    }
    return stateIntegerGrid;
  }

  private int[][] cellAddedToStateIntegerGrid(int cellGridRow, int cellGridColumn, int[][] stateIntegerGrid) {
    if (cellGrid[cellGridRow][cellGridColumn] == null) {
      stateIntegerGrid[cellGridRow][cellGridColumn] = -1;
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

  public Cell[][] getCellGrid() {
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
        if(!otherCell.equals(thisCell)) {
          return false;
        }
      }
    }
    return true;
  }
}
