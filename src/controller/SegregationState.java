package controller;

public enum SegregationState implements State{
  XAGENT,
  OAGENT,
  EMPTY;

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  @Override
  public String toString() {
    return "Segregation";
  }
}
