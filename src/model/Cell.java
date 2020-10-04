package model;

public class Cell {

  private Neighborhood neighborhood;

  public Cell(SimulationType simulationType) {
    neighborhood = createNeighborhoodForThisCell(simulationType);
  }

  Neighborhood createNeighborhoodForThisCell(SimulationType simulationType) {
  }

  Cell createNextCellWithUpdatedState() {
  }


}