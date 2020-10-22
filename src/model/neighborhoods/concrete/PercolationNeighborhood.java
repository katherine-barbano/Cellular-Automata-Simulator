package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.PercolationState;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

/***
 * Neighborhood for Percolation simulation
 *
 * @author Katherine Barbano
 */
public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  /***
   * Constructor overrides Neighborhood constructor
   * @param neighborPolicy NeighborPolicy object
   */
  public PercolationNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  /***
   * Gets the next state of the center cell for this type of simulation
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center cell for the next grid
   */
  @Override
  public State getNextState(State currentState) {
    if(currentState.equalsState(PercolationState.OPEN)) {
      return handleOpenState();
    }
    return currentState;
  }

  private State handleOpenState() {
    boolean adjacentToWater = neighborPositionToStateContainsState(new State(PercolationState.WATER));
    if(adjacentToWater) {
      return new State(PercolationState.WATER);
    }
    return new State(PercolationState.OPEN);
  }
}
