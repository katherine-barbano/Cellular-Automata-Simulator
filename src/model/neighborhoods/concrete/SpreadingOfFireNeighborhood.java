package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.SpreadingOfFireState;
import java.util.Random;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

/***
 * Neighborhood for SpreadingOfFire simulation
 *
 * @author Katherine Barbano
 */
public class SpreadingOfFireNeighborhood extends NonInfluentialNeighborhood {

  public static final String PROBABILITY_CATCH_DEFAULT_PROPERTIES = "SpreadingOfFire_probabilityOfCatchingDefault";

  private double probabilityCatchFire;
  private final Random random;
  private double nextDouble;

  /***
   * Constructor overrides Neighborhood constructor
   * @param neighborPolicy NeighborPolicy object
   */
  public SpreadingOfFireNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
    probabilityCatchFire = Double.parseDouble(getModelResources().getString(PROBABILITY_CATCH_DEFAULT_PROPERTIES));
    random = new Random();
    nextDouble = random.nextDouble();
  }

  public SpreadingOfFireNeighborhood(NeighborPolicy neighborPolicy, double optionalProbability) {
    this(neighborPolicy);
    if(optionalProbability!=0.0) {
      probabilityCatchFire = optionalProbability;
    }
  }

  /***
   * Gets the next state of the center cell for this type of simulation
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center cell for the next grid
   */
  @Override
  public State getNextState(State currentState) {
    if(!currentState.equalsState(SpreadingOfFireState.TREE)) {
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
