package model.neighborhoods.concrete;

import controller.State;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class SpreadingOfFireNeighborhood extends NonInfluentialNeighborhood {

  public static final String PROBABILITY_CATCH_PROPERTIES = "SpreadingOfFire_probabilityOfCatching";

  private double probabilityCatchFire;

  public SpreadingOfFireNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
    probabilityCatchFire = Double.parseDouble(getModelResources().getString(PROBABILITY_CATCH_PROPERTIES));
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentNeighborsOnly(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {

  }

}
