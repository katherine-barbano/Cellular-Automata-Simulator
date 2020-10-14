package model.neighborhoods.concrete;

import controller.State;
import java.util.Map;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  private final String EMPTY_PROPERTIES="emptyStateName";
  public static final String OPEN_PROPERTIES="openStateName";
  public static final String WATER_PROPERTIES="waterStateName";

  private String openStateName = getModelResources().getString(OPEN_PROPERTIES);
  private String waterStateName = getModelResources().getString(WATER_PROPERTIES);
  private String emptyStateName = getModelResources().getString(EMPTY_PROPERTIES);

  public PercolationNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentNeighborsOnly(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    if(currentState.equals(openStateName)) {
      return handleOpenState();
    }
    return currentState;
  }

  private State handleOpenState() {
    boolean adjacentToWater = neighborPositionToStateContainsState(new State(waterStateName));
    if(adjacentToWater) {
      return new State(waterStateName);
    }
    return new State(openStateName);
  }
}
