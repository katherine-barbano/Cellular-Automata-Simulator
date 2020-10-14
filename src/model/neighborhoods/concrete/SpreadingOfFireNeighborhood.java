package model.neighborhoods.concrete;

import controller.State;
import java.util.Map;
import java.util.Random;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class SpreadingOfFireNeighborhood extends NonInfluentialNeighborhood {

  public static final String PROBABILITY_CATCH_PROPERTIES = "SpreadingOfFire_probabilityOfCatching";
  public static final String BURNING_PROPERTIES="burningStateName";
  public static final String TREE_PROPERTIES="treeStateName";
  private final String EMPTY_PROPERTIES="emptyStateName";

  private double probabilityCatchFire;
  private Random random;
  private double nextDouble;
  private String treeStateName = getModelResources().getString(TREE_PROPERTIES);
  private String burningStateName = getModelResources().getString(BURNING_PROPERTIES);
  private String emptyStateName = getModelResources().getString(EMPTY_PROPERTIES);

  public SpreadingOfFireNeighborhood(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    super(centerCellRow, centerCellColumn, stateGrid);
    probabilityCatchFire = Double.parseDouble(getModelResources().getString(PROBABILITY_CATCH_PROPERTIES));
    random = new Random();
    nextDouble = random.nextDouble();
  }

  @Override
  public void createNeighborMap(int centerCellRow, int centerCellColumn, State[][] allStatesInCSV){
    createNeighborMapForAdjacentNeighborsOnly(centerCellRow, centerCellColumn, allStatesInCSV);
  }

  @Override
  public State getNextState(State currentState, Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    if(!currentState.equals(treeStateName)) {
      return new State(emptyStateName);
    }
    else if(getNumberOfNeighborsWithGivenState(new State(burningStateName)) >0 && treeCatchesFire()){
      return new State(burningStateName);
    }
    else {
      return currentState;
    }
  }

  //referenced https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range to learn how to generate a random double
  private boolean treeCatchesFire() {
    double actualEvent = nextDouble;
    nextDouble = random.nextDouble();
    return actualEvent>=probabilityCatchFire;
  }
}
