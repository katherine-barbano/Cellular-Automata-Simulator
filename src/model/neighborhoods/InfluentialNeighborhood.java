package model.neighborhoods;

import controller.State;
import java.util.List;
import model.NeighborPolicy;
import model.Neighborhood;

/***
 * Represents a Neighborhood that must influence and be influenced by other Neighborhoods
 * in the Grid. This provides functionality for movement and maintaining States/age
 * between Grids in WaTorWorld and Segregation.
 *
 * Provides helpers to allow conflicts to be resolved between neighborhoods of neighbors
 * in the second step of the nextGrid retrieval while still giving the needed flexibility by
 * maintaining abstractions for getNextState and getStateOfOverlappingNeighbors.
 *
 * @author Katherine Barbano
 */
public abstract class InfluentialNeighborhood extends Neighborhood {

  /***
   * Overrides constructor for Neighborhood
   * @param neighborPolicy NeighborPolicy object
   */
  public InfluentialNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  protected State handleMoveToNeighbor(State currentState, State neighborState) {
    List<int[]> positionsOfEmptyNeighbors = positionsOfTargetStateNeighbors(neighborState);
    currentState.setNextPositionMove(positionsOfEmptyNeighbors);
    int[] positionToMoveInto = currentState.getNextPosition();
    replaceNeighborStateWithNewState(positionToMoveInto,currentState);
    deleteMovedStateFromNeighborhoodsOfNeighbors(neighborState);
    return new State(neighborState.getStateType());
  }

  /***
   * Helper to delete a state from the neighborhoods of neighbors and replace it with
   * another State object (usually EMPTY).
   * @param newState new State to replace the old state
   */
  public void deleteMovedStateFromNeighborhoodsOfNeighbors(State newState) {
    for(int[] neighborPosition : getNeighborhoodsOfNeighbors().keySet()) {
      int[] positionOfCenterCellInNeighbor = negateArray(neighborPosition);
      Neighborhood neighborhoodOfNeighbor = getNeighborhoodsOfNeighbors().get(neighborPosition);
      neighborhoodOfNeighbor.replaceNeighborStateWithNewState(positionOfCenterCellInNeighbor,newState);
    }
  }

  private int[] negateArray(int[] array) {
    int[] newArray = new int[array.length];
    for(int index = 0; index<array.length; index++) {
      newArray[index] = array[index]*(-1);
    }
    return newArray;
  }
}
