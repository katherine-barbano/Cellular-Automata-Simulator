package model;

public class Cell {

  private Neighborhood neighborhood;
  private int currentState;

  /***
   * Constructor used for creating first initial set of cells from CSV file.
   * @param neighborhood Neighborhood object corresponding to cell
   * @param state int corresponding to state in csv file
   */
  public Cell(Neighborhood neighborhood, int state) {
    this.neighborhood = neighborhood;
    this.currentState = state;
  }

  /***
   * Constructor used for creating all Cells after the initial set of Cells.
   * Must call setNeighborhood after all Cells are created in the new Grid.
   * @param state int corresponding to state in csv file
   */
  public Cell(int state) {
    this.currentState = state;
  }

  Cell getNextCell() {
    int nextState = neighborhood.getNextState(currentState);
    Cell nextCell = new Cell(nextState);
    return nextCell;
  }

  public int getCurrentState() {
    return currentState;
  }

  Neighborhood getNeighborhood() {
    return neighborhood;
  }

  void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public boolean equals(Cell otherCell) {
    boolean statesAreEqual = otherCell.getCurrentState() == currentState;
    boolean neighborhoodsBothNull = neighborhood == null && otherCell.getNeighborhood()==null;
    boolean atLeastOneNeighborhoodNull = neighborhood==null || otherCell.neighborhood==null;
    boolean neighborhoodsAreEqual = false;
    if(neighborhoodsBothNull || (!atLeastOneNeighborhoodNull && otherCell.getNeighborhood().equals(neighborhood))){
      neighborhoodsAreEqual = true;
    }
    return statesAreEqual && neighborhoodsAreEqual;
  }
}