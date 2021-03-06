package model;

import controller.State;
import java.util.Map;

/***
 * Corresponds to a single unit in the Grid with a single currentState.
 * Maintains the currentState and neighborhood of that unit, as well as the
 * statesOfOverlappingNeighborsOnCell for Grid's getNextGridAfterMove.
 * @author Katherine Barbano
 */
public class Cell {

  private Neighborhood neighborhood;
  private State currentState;
  private Map<int[], State> statesOfOverlappingNeighborsOnCell;

  /***
   * Constructor used for creating first initial set of cells from CSV file.
   * @param neighborhood Neighborhood object corresponding to cell
   * @param state State corresponding to state in csv file
   */
  public Cell(Neighborhood neighborhood, State state) {
    this.neighborhood = neighborhood;
    this.currentState = state;
  }

  /***
   * Constructor used for creating all Cells after the initial set of Cells.
   * Must call setNeighborhood after all Cells are created in the new Grid.
   * @param state State corresponding to state in csv file
   */
  public Cell(State state) {
    this.currentState = state;
  }

  Cell getNextCell() {
    State nextState = neighborhood.getNextState(currentState);
    return new Cell(nextState);
  }

  /***
   * Used by the View to return the currentState of this cell.
   * @return State object
   */
  public State getCurrentState() {
    return currentState;
  }

  /***
   * Gets the current Neighborhood of this cell. Used by Grid.
   * @return Neighborhood object
   */
  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  /***
   * Determines whether the states of this cell and another cell are equal, along with
   * their neighborhoods.
   * @param otherCell Another cell object
   * @return true if cells are equal
   */
  public boolean equalsCell(Cell otherCell) {
    boolean statesAreEqual = otherCell.getCurrentState().equalsState(currentState);
    boolean neighborhoodsBothNull = neighborhood == null && otherCell.getNeighborhood()==null;
    boolean atLeastOneNeighborhoodNull = neighborhood==null || otherCell.neighborhood==null;
    boolean neighborhoodsAreEqual = false;
    if(neighborhoodsBothNull || (!atLeastOneNeighborhoodNull && otherCell.getNeighborhood().equalsNeighborhood(neighborhood))){
      neighborhoodsAreEqual = true;
    }
    return statesAreEqual && neighborhoodsAreEqual;
  }

  Cell getCellFromOverlappingNeighbors() {
    State nextState = neighborhood.getStateOfOverlappingNeighbors(getCurrentState(),
        statesOfOverlappingNeighborsOnCell);
    return new Cell(nextState);
  }

  void setStatesOfOverlappingNeighbors(Map<int[], State> statesOfOverlappingNeighbors) {
    statesOfOverlappingNeighborsOnCell = statesOfOverlappingNeighbors;
  }

  void setNeighborhoodsOfNeighbors(Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    neighborhood.setNeighborhoodsOfNeighbors(neighborhoodsOfNeighbors);
  }

  Map<int[], Neighborhood> getNeighborhoodsOfNeighbors() {
    return neighborhood.getNeighborhoodsOfNeighbors();
  }

  /***
   * Used by the View and Controller to set the currentState of the cell (for example,
   * if the user clicks directly on a Cell to change its state).
   * @param state State the Cell should change to
   */
  public void setCurrentState(State state) {
    currentState = state;
  }
}