package model.neighborhoods.concrete;

import controller.stateType.GameOfLifeState;
import controller.State;
import java.util.ArrayList;
import java.util.List;
import model.NeighborPolicy;
import model.neighborhoods.NonInfluentialNeighborhood;

/***
 * Neighborhood for GameOfLife simulation
 *
 * @author Katherine Barbano
 */
public class GameOfLifeNeighborhood extends NonInfluentialNeighborhood {

  public static final String NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForLiveCellToSurvive";
  public static final String NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES = "GameOfLife_NumberLiveNeighborsForDeadCellToSurvive";

  /***
   * Constructor overrides Neighborhood constructor
   * @param neighborPolicy NeighborPolicy object
   */
  public GameOfLifeNeighborhood(NeighborPolicy neighborPolicy) {
    super(neighborPolicy);
  }

  /***
   * Gets the next state of the center cell for this type of simulation
   * @param currentState State object currently in the center Cell
   * @return State object that should be in the center cell for the next grid
   */
  @Override
  public State getNextState(State currentState) {
    State nextState = new State(GameOfLifeState.DEAD);
    int numberOfLivingNeighbors = getNumberOfNeighborsWithGivenState(new State(GameOfLifeState.ALIVE));
    List<Integer> numberLiveNeighborsForLiveCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_LIVE_CONSTANT_IN_MODEL_PROPERTIES);
    List<Integer> numberLiveNeighborsForDeadCellToSurvive = getNumberOfNeighborsFromResources(NAME_OF_DEAD_CONSTANT_IN_MODEL_PROPERTIES);

    boolean liveCellSurvives = currentState.equalsState(GameOfLifeState.ALIVE) && numberLiveNeighborsForLiveCellToSurvive.contains(numberOfLivingNeighbors);
    boolean deadCellSurvives = currentState.equalsState(GameOfLifeState.DEAD) && numberLiveNeighborsForDeadCellToSurvive.contains(numberOfLivingNeighbors);

    if(liveCellSurvives || deadCellSurvives) {
      nextState = new State(GameOfLifeState.ALIVE);
    }

    return nextState;
  }

  private List<Integer> getNumberOfNeighborsFromResources(String numberListName) {
    String numberOfNeighborsString = getModelResources().getString(numberListName);
    String[] numberOfNeighborsStringArray = numberOfNeighborsString.split(",");
    List<Integer> numberOfNeighborsIntList = new ArrayList<>();
    for(String numberOfNeighbors:numberOfNeighborsStringArray) {
      numberOfNeighborsIntList.add(Integer.parseInt(numberOfNeighbors));
    }
    return numberOfNeighborsIntList;
  }
}
