package controller;

public enum FireState implements State{
  TREE,
  BURNING,
  EMPTY;

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }
}
