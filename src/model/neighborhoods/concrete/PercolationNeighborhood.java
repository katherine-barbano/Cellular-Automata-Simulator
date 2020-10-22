package model.neighborhoods.concrete;

import controller.State;
import controller.stateType.PercolationState;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

public class PercolationNeighborhood extends NonInfluentialNeighborhood {

  public PercolationNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

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
