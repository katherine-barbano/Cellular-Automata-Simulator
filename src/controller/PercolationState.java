package controller;

public enum PercolationState implements State{
;
  @Override
  public int[] getNextPosition() {
    return new int[] {0,0} ;
  }
}
