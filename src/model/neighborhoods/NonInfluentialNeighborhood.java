package model.neighborhoods;

import controller.State;
import java.util.Map;
import model.NeighborPolicy;
import model.Neighborhood;

/***
 * Represents a Neighborhood that does not need to influence and be influenced by other Neighborhoods
 * in the Grid. These Neighborhoods, such as GameOfLifeNeighborhood, PercolationNeighborhood,
 * RockPaperScissorsNeighborhood, and SpreadingOfFireNeighborhood implement getNextState using information about
 * the States of only ITS OWN neighbors, not from other neighborhoods.
 *
 * This class defines getStateOfOverlappingNeighbors to reduce code duplication.
 *
 * @author Katherine Barbano
 */
public abstract class NonInfluentialNeighborhood extends Neighborhood {

  /***
   * Constructor overrides neighborhood constructor
   * @param neighborPolicy Neighbor policy object
   */
  public NonInfluentialNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  /***
   * Because in these neighborhoods, the next state is determined only using information about
   * the States of only ITS OWN neighbors, not from other neighborhoods, there will be no conflicts
   * that arise. Thus, simply returning the nextState as determined by its own neighborhood is sufficient.
   * @param nextState The proposed nextState returned by getNextState, only from the info in Cell's own neighborhood
   * @param statesOfOverlappingNeighborsOnCell A Map of keys with int[] relative positions, and values of States in those positions in the Grid
   * @return
   */
  @Override
  public State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell) {
    return nextState;
  }
}
