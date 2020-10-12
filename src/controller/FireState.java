package controller;

public enum FireState implements State{
  TREE("Tree"),
  BURNING("Burning"),
  EMPTY("Empty");

  private String stateName;

  FireState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  public String toString() {
    return this.stateName;
  }
}
