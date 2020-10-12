package controller;

public enum SegregationState implements State{
  XAGENT,
  OAGENT,
  EMPTY;

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }
}
