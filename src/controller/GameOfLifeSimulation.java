package controller;

import java.util.HashMap;
import model.SimulationType;

public class GameOfLifeSimulation extends Simulation {

  public GameOfLifeSimulation() {
    super(SimulationType.GAME_OF_LIFE, "testingGOL.csv");
  }

  @Override
  public State[][] createStatesFromInteger(int[][] integerCellStates) {
    HashMap<Integer, State> statesForInteger = new HashMap<>();
    State possibleStatesInGameOfLife[] = GameOfLifeStates.values();
    int stateNumber = 0;
    for(State state : possibleStatesInGameOfLife) {
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
    State[][] cellStates = new State[integerCellStates.length][integerCellStates[0].length];
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = statesForInteger.get(integerCellStates[row][col]);
      }
    }
    return cellStates;
  }

  @Override
  public String readInPropertiesFile() {


    return "";
  }
}
