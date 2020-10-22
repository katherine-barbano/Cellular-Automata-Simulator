package controller;

import java.util.List;
import java.util.Random;

/**
 * The purpose of the class is to represent the different states available for each simulation that
 * different cells can be. It takes in a statetype in order to create the state and store its own
 * name, age, and potential next position. For some simulations, it can be used for
 * determining the open positions around the current cell state. In addition, some cells for certain
 * simulations need to know which age it is to determine the next state. This class can be used when
 * creating the grid because the grid takes in a 2d array of states so that each state will be
 * responsible for knowing its own age and state type.
 */

public class State {

  private StateType stateType;
  private int[] nextPosition;
  private int age;

  public State(StateType stateName) {
    this.stateType = stateName;
    this.age = 0;
    setNextPositionStationary();
  }

  /*
  getter method for the state type associated with the State
   */
  public StateType getStateType(){
    return stateType;
  }

  /*
  Method for setting the value of the state type for the State
   */
  public void setStateType(StateType inputStateType) { this.stateType = inputStateType; }

  public boolean equalsState(State otherState) {
    return stateType == otherState.getStateType();
  }

  public boolean equalsState(StateType otherStateType) {
    return stateType == otherStateType;
  }

  /*
  Method that returns the State's next position
   */
  public int[] getNextPosition() {
    return this.nextPosition;
  }

  /*
  Method that can set the next position for the State based on the available open positions of the
  State
   */
  public void setNextPositionMove(List<int[]> newOpenPositions) {
    if(newOpenPositions.size()>0) {
      this.nextPosition = getOpenPosition((newOpenPositions));
    }
  }

  /*
  Sets the next position in the case where the State cannot be moved
   */
  public void setNextPositionStationary() {
    this.nextPosition = new int[]{0,0};
  }

  /*
  Method that randomly selects a position from a list of open positions
   */
  public int[] getOpenPosition(List<int[]> openPositions) {
    if(openPositions.size()>0) {
      Random random = new Random();
      int randomIndex = random.nextInt(openPositions.size());
      return openPositions.get(randomIndex);
    }
    else {
      return null;
    }
  }

  /*
  getter method to return the State's age
   */
  public int getAge(){
    return this.age;
  }

  /*
  setter method for setting the age of the State
   */
  public void setAge(int newAge) {
    this.age = newAge;
  }
}
