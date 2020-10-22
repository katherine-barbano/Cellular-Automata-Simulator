package model;

import controller.State;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/***
 * Represents the cells treated as "adjacent" to a center cell (although "adjacent"
 * can have different implementations depending on the NeighborPolicy). Maintains
 * a NeighborPolicy to define this, and the Neighborhood objects corresponding to
 * all neighbors within this Neighborhood. Abstracts the process of getting the next
 * state for a center cell 1. based only on its own neighborhood, and 2. using the neighborhoods
 * that overlap ontop the center cell.
 *
 * This class uses the system illustrated in doc/relativePositionOfNeighbors.JPG to
 * keep track of positions of neighbors relative to the center cell in an int[].
 *
 * @author Katherine Barbano
 */
public abstract class Neighborhood {

  public static final String MODEL_RESOURCE_PATH = "resources/Model";
  public static final String POSITION_NOT_FOUND_EXCEPTION_PROPERTIES = "positionNotFoundExceptionMessage";

  private Map<int[], Neighborhood> neighborhoodsOfNeighbors;
  private final ResourceBundle modelResources;
  private final NeighborPolicy neighborPolicy;

  /***
   * Constructor to create a new Neighborhood with any type of NeighborPolicy.
   * Usually, a superclass reference to a subclass NeighborPolicy is passed in.
   * @param neighborPolicy NeighborPolicy abstract class
   */
  public Neighborhood(NeighborPolicy neighborPolicy) {
    modelResources = ResourceBundle.getBundle(MODEL_RESOURCE_PATH);
    neighborhoodsOfNeighbors = new HashMap<>();
    this.neighborPolicy = neighborPolicy;
  }

  /***
   *  To get the next Grid, two things must be done: first, a new Grid object is created
   *  with each cell's value being obtained with information ONLY from its neighbors. This method
   *  performs that first task of returning the next State for a center cell
   *  from only this Neighborhood's information.
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center Cell on the next step
   */
  public abstract State getNextState(State currentState);

  /***
   * To get the next Grid, two things must be done: first, getNextState must be run for every Cell to
   * come up with an initial proposed State based only on the cell's own Neighborhood.
   *
   * However, some simulations are "Influential", meaning that the center cell's previous State
   *  might need to appear in another neighborhood (to enable movement
   *  or maintain an age), conflicting with the State obtained for the next grid from the first
   *  step. Thus, if a center cell's current state must be maintained through movement, the current
   *  state is "pushed" to some adjacent position in ITS OWN Neighborhood where it decides to move.
   *
   *   However, this only updates the center cell's Neighborhood, it doesn't actually update the States
   *  of any of the cells themselves. So the second step is to iterate through the proposed next Grid again
   *  after the first step, and see if there are any Neighborhoods that completed a "push" onto
   *  the center cell, and if so, to resolve the conflicts. The Neighborhoods
   *   that have to potential to push onto the center cell are simply the Neighborhoods of the center cell's neighbors.
   *   When there is conflict arises between what the next State should be for the center cell between
   *   the proposed nextState returned by getNextState, and any of the neighborhoods that overlap on the
   *   center cell, the conflict can be resolved in this method.
   *
   * @param nextState The proposed nextState returned by getNextState, only from the info in Cell's own neighborhood
   * @param statesOfOverlappingNeighborsOnCell A Map of keys with int[] relative positions, and values of States in those positions in the Grid
   * @return The final State that should appear in the Grid based on all information provided in the arguments
   */
  public abstract State getStateOfOverlappingNeighbors(State nextState, Map<int[], State> statesOfOverlappingNeighborsOnCell);

  /***
   * Sets the neighborhoodsOfNeighbors. Used by Grid and Cell to initialize and update this field
   * @param neighborhoodsOfNeighbors Map of keys with int[] relative positions, and values of Neighborhoods associated with that relative position as center cell
   */
  public void setNeighborhoodsOfNeighbors(Map<int[], Neighborhood> neighborhoodsOfNeighbors) {
    this.neighborhoodsOfNeighbors = neighborhoodsOfNeighbors;
  }

  /***
   * Accesses the neighbor policy (which accesses the edge policy) to return the actual
   * position of a neighbor in the Grid from the relative position in the neighborhood.
   * Used by Grid during neighbor update.
   * @param relativePosition int[] of row, col relative position
   * @return int[] of row, col actual position in Grid
   */
  public int[] getPositionOfNeighbor(int[] relativePosition) {
    return neighborPolicy.getPositionOfNeighbor(relativePosition);
  }

  /***
   * Looks through keys of neighborhoodsOfNeighbors to find the given relative position
   * in the Map, then return the neighbor's associated Neighborhood as stored in this object.
   *
   * @throws ModelException if relative position is not found
   * @param openPosition int[] of row, col relative position
   * @return Neighborhood object
   */
  public Neighborhood findPositionInNeighborhoodOfNeighbors(int[] openPosition) {
    for(int[] position:neighborhoodsOfNeighbors.keySet()) {
      if(position[0] == openPosition[0] && position[1] == openPosition[1]) {
        return neighborhoodsOfNeighbors.get(position);
      }
    }
    String positionNotFoundExceptionMessage = modelResources.getString(POSITION_NOT_FOUND_EXCEPTION_PROPERTIES);
    throw new ModelException(positionNotFoundExceptionMessage);
  }

  /***
   * Gets the neighborhoodOfNeighbors in this neighborhood for Grid
   * @return Map with key as relative position, and neighborhood object as value
   */
  public Map<int[], Neighborhood> getNeighborhoodsOfNeighbors() {
    return neighborhoodsOfNeighbors;
  }

  /***
   * Gets the resource bundle for Model properties file
   * @return ResourceBundle
   */
  public ResourceBundle getModelResources() {
    return modelResources;
  }

  /***
   * Two neighborhoods are equal if their neighbor policies are equal
   * @param neighborhood Another neighborhood object
   * @return True if this neighborhood equals another neighborhood
   */
  public boolean equalsNeighborhood(Neighborhood neighborhood) {
    return neighborPolicy.equalsNeighborhood(neighborhood);
  }

  protected boolean neighborPositionToStateContainsState(State target) {
    return neighborPolicy.neighborPositionToStateContainsState(target);
  }

  /***
   * Accesses the neighbor policy to replace a neighbor state with a new state.
   * @param neighborKey relative position int[] row, column
   * @param newState new State object to set in that position
   */
  public void replaceNeighborStateWithNewState(int[] neighborKey, State newState) {
    neighborPolicy.replaceNeighborStateWithNewState(neighborKey, newState);
  }

  protected State getStateFromNeighborPosition(int[] position) {
    return neighborPolicy.getStateFromNeighborPosition(position);
  }

  protected int getNumberOfNeighborsWithGivenState(State targetState) {
    return neighborPolicy.getNumberOfNeighborsWithGivenState(targetState);
  }

  protected int getNumberOfNeighbors() {
    return neighborPolicy.getNumberOfNeighbors();
  }

  protected List<int[]> positionsOfTargetStateNeighbors(State state) {
    return neighborPolicy.positionsOfTargetStateNeighbors(state);
  }

  protected Set<int[]> allPossibleRelativePositions() {
    return neighborPolicy.allPossibleRelativePositions();
  }
}
