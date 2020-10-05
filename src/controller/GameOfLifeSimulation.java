package controller;

import model.SimulationType;

public class GameOfLifeSimulation extends Simulation {


  public GameOfLifeSimulation() {
    super(SimulationType.GAME_OF_LIFE, "testingGOL.csv");
  }
}
