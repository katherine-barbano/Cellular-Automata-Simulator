package model;

public class Grid {

  private Cell[][] cellGrid;

  public Grid(SimulationType simulationType, int[][] allStatesInCSV) {
    cellGrid = new Cell[allStatesInCSV.length][allStatesInCSV[0].length];
    initializeCurrentCellGrid(simulationType, allStatesInCSV);
  }

  /***
   * If number of rows (or number of columns) is not constant throughout entire allStatesInCSV,
   * assume that allStatesInCSV[index][index] is -1 if no Cell should be initialized for that position.
   * This method leaves an empty position in cellGrid if the state is -1 in CSV.
   */
  private void initializeCurrentCellGrid(SimulationType simulationType, int[][] allStatesInCSV) {
    for (int csvRow = 0; csvRow < allStatesInCSV.length; csvRow++) {
      for (int csvColumn = 0; csvColumn < allStatesInCSV[csvRow].length; csvColumn++) {
        if (allStatesInCSV[csvRow][csvColumn] != -1) {
          putCellWithNeighborhoodInGrid(simulationType, csvRow, csvColumn, allStatesInCSV);
        }
      }
    }
  }

  private void putCellWithNeighborhoodInGrid(SimulationType simulationType, int csvRow, int csvColumn, int[][] allStatesInCSV) {
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(simulationType, csvRow, csvColumn, allStatesInCSV);
    Cell cellInPosition = new Cell (cellNeighborhood);
    cellGrid[csvRow][csvColumn] = cellInPosition;
  }

  /***
   * Must edit this to create a new type of Neighborhood when adding a new type of simulation.
   */
  private Neighborhood createNeighborhoodForSimulationType(SimulationType simulationType, int centerCellRow, int centerCellColumn, int[][] allStatesInCSV) {
    switch (simulationType) {
      case GAME_OF_LIFE:
        return new GameOfLifeNeighborhood(centerCellRow, centerCellColumn, allStatesInCSV);
      default:
        //should never be reached, because all simulation types are listed above
        return null;
    }
  }
}
