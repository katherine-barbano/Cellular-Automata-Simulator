package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.SpreadingOfFireState;
import java.util.Map;
import java.util.Random;
import model.Neighborhood;
import model.neighborhoods.NonInfluentialNeighborhood;

public class SpreadingOfFireNeighborhood extends NonInfluentialNeighborhood {

  public static final String PROBABILITY_CATCH_PROPERTIES = "SpreadingOfFire_probabilityOfCatching";

  private double probabilityCatchFire;
  private Random random;
  private double nextDouble;

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
  public State getNextState(State currentState) {
    if(!currentState.equals(SpreadingOfFireState.TREE)) {
      return new State(SpreadingOfFireState.EMPTY);
    }
    else if(getNumberOfNeighborsWithGivenState(new State(SpreadingOfFireState.BURNING)) >0 && treeCatchesFire()){
      return new State(SpreadingOfFireState.BURNING);
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
