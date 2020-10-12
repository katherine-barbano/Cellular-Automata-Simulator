package controller;

public enum PercolationState implements State{
  OPEN,
  BLOCKED;

  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }

  @Override
  public String toString() {
    return "Percolation";
  }
}
