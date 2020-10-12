package controller;

public enum RockPaperScissorState implements State{
  ROCK,
  PAPER,
  SCISSOR;

  @Override
  public int[] getNextPosition() {
    return new int[0];
  }

  @Override
  public String toString() {
    return "RockPaperScissors";
  }
}
