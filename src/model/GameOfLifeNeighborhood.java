package model;

import java.util.HashMap;
import java.util.Map;

class GameOfLifeNeighborhood extends Neighborhood {

  private Map<Integer, GameOfLifeState> gameOfLifeStateMap;

  GameOfLifeNeighborhood(int centerCellRow, int centerCellColumn, int[][] allStatesInCSV) {
    super(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  /***
   * Assumes the number of possible state values given in CSV file are equal to the corresponding
   * number of constants in GameOfLifeState enum.
   */
  @Override
  void createCSVValueToStateMap() {
    gameOfLifeStateMap = new HashMap<>();
    GameOfLifeState possibleStatesInGameOfLife[] = GameOfLifeState.values();
    int stateNumber = 0;
    for(GameOfLifeState state : possibleStatesInGameOfLife) {
      gameOfLifeStateMap.put(stateNumber,state);
      stateNumber++;
    }
  }

  @Override
  int getNextState(int currentState) {
    return 1;
  }

  /***
   * State corresponding to 0 in CSV file should be at the top, state corresponding to 1 in CSV
   * file should be next, etc.
   */
  private enum GameOfLifeState {
    DEAD,
    ALIVE
  }
}
