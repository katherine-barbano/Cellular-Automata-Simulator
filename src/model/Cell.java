package model;

import controller.State;
import java.util.Map;

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

  public State getCurrentState() {
    return currentState;
  }

  public Neighborhood getNeighborhood() {
    return neighborhood;
  }

  void setNeighborhood(Neighborhood neighborhood) {
    this.neighborhood = neighborhood;
  }

  public boolean equals(Cell otherCell) {
    boolean statesAreEqual = otherCell.getCurrentState().equals(currentState);
    boolean neighborhoodsBothNull = neighborhood == null && otherCell.getNeighborhood()==null;
    boolean atLeastOneNeighborhoodNull = neighborhood==null || otherCell.neighborhood==null;
    boolean neighborhoodsAreEqual = false;
    if(neighborhoodsBothNull || (!atLeastOneNeighborhoodNull && otherCell.getNeighborhood().equals(neighborhood))){
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

  public void setCurrentState(State state) {
    currentState = state;
  }
}