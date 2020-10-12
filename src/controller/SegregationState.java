package controller;

public enum SegregationState implements State{
  XAGENT("xagent"),
  OAGENT("oagent"),
  EMPTY("empty");

  private String stateName;

  SegregationState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  @Override
  public String toString() {
    return "Segregation";
  }
}
