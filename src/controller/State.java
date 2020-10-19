package controller;

import java.util.List;
import java.util.Random;

public class State {

  private StateType stateType;
  private int[] nextPosition;
  private int age;

  public State(StateType stateName) {
    this.stateType = stateName;
    this.age = 0;
    setNextPositionStationary();
  }

  public StateType getStateType(){
    return stateType;
  }

  public void setStateType(StateType inputStateType) { this.stateType = inputStateType; }

  public boolean equals(State otherState) {
    return stateType == otherState.getStateType();
  }

  public boolean equals(StateType otherStateType) {
    return stateType == otherStateType;
  }

  public int[] getNextPosition() {
    return this.nextPosition;
  }

  public void setNextPositionMove(List<int[]> newOpenPositions) {
    if(newOpenPositions.size()>0) {
      this.nextPosition = getOpenPosition((newOpenPositions));
    }
  }

  public void setNextPositionStationary() {
    this.nextPosition = new int[]{0,0};
  }

  public int[] getOpenPosition(List<int[]> openPositions) {
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size());
    return openPositions.get(randomIndex);
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int newAge) {
    this.age = newAge;
  }
}
