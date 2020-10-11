package model;

public enum SimulationType {
  GAME_OF_LIFE("GameOfLife"),
  PERCOLATION("Percolation"),
  ROCK_PAPER_SCISSORS("RockPaperScissors"),
  SPREADING_OF_FIRE("SpreadingOfFire"),
  SEGREGATION("Segregation"),
  WATOR_WORLD("WatorWorld");

  private String className;

  SimulationType(String className) {
    this.className = className;
  }

  @Override
  public String toString() {
    return className;
  }
}
