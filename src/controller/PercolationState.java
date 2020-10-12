package controller;

public enum PercolationState implements State{
  OPEN("Open"),
  BLOCKED("Blocked");

  private String stateName;

  PercolationState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }

  @Override
  public String toString() {
    return "Percolation";
  }
}
