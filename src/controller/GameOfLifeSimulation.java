package controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import model.SimulationType;

public class GameOfLifeSimulation extends Simulation {

  public GameOfLifeSimulation() {
    super(SimulationType.GAME_OF_LIFE, "GameOfLife");
  }

  @Override
  public State[][] createStatesFromInteger(int[][] integerCellStates) {
    HashMap<Integer, State> statesForInteger = new HashMap<>();
    State possibleStatesInGameOfLife[] = GameOfLifeState.values();
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
    try {
      Properties myProperties = new Properties();
      //Class csl = Class.forName("controller.GameOfLifeSimulation");
      //ClassLoader cl = csl.getClassLoader();
      //myProperties.load()
          //yProperties.load(ControllerErrors.getResourceAsStream());
      InputStream is = GameOfLifeSimulation.class.getClass().getResourceAsStream("ControllerError.properties");
      myProperties.load(is);
      System.out.println("loaded now");
      return "";
    }
    catch (Exception e) {
      System.out.println("exception");
    }
    return null;
  }
}
