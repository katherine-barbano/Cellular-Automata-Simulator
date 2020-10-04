package controller;

import java.util.List;

public class GameOfLifeSimulation extends Simulation {


  public GameOfLifeSimulation() {
    super("GameOfLife", List.of("DEAD", "ALIVE"), "GameOfLife.csv");
  }
}
