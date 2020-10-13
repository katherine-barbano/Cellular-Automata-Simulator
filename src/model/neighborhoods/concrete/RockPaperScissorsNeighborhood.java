package model.neighborhoods.concrete;

import controller.State;
import controller.states.RockPaperScissorState;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class RockPaperScissorsNeighborhood extends NonInfluentialNeighborhood {

  public RockPaperScissorsNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentAndDiagonal(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
;
  }


}
