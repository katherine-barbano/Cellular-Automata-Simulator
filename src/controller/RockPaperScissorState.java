package controller;

public enum RockPaperScissorState implements State{
  ROCK("Rock"),
  PAPER("Paper"),
  SCISSOR("Scissor");

  private String stateName;

  RockPaperScissorState(String nameOfState) {
    this.stateName = nameOfState;
  }

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  @Override
  public String toString() {
    return "RockPaperScissors";
  }
}
