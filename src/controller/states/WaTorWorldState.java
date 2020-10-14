package controller.states;

import controller.State;
import java.util.List;
import java.util.Random;
import view.CellFormat.CellColors;

public enum WaTorWorldState implements State {
  FISH(0, "Fish"),
  SHARK(0, "Shark"),
  EMPTY("Empty");

  private int age;
  private String stateName;
  private int[] nextPosition;
  private CellColors stateColor;

  WaTorWorldState(int defaultAge, String nameOfState){
    this.age = defaultAge;
    setNextPositionStationary();
    this.stateName = nameOfState;
  }

  WaTorWorldState(String nameOfState) {
    this.stateName = nameOfState;
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int newAge) {
    this.age = newAge;
  }

  public int[] getNextPosition() {
    return this.nextPosition;
  }

  public void setNextPositionMove(List<int[]> newOpenPositions) {
    this.nextPosition = getOpenPosition((newOpenPositions));
  }

  public void setNextPositionStationary() {
    this.nextPosition = new int[]{0,0};
  }

  public int[] getOpenPosition(List<int[]> openPositions) {
    Random random = new Random();
    int size = openPositions.size();
    int randomIndex;
    if(size == 1) {
      randomIndex=0;
    }
    else {
      randomIndex = random.nextInt(openPositions.size()-1);
    }
    return openPositions.get(randomIndex);
  }

  public String toString() {
    return this.stateName;
  }

  public void setNextPosition(int[] nextPosition) {
    this.nextPosition = nextPosition;
  }

  public void setStateName (String stateName) {
    this.stateName = stateName;
  }

  public String getStateName() {
    return stateName;
  }

  @Override
  public void setStateColor(CellColors color){
    this.stateColor = color;
  }

  @Override
  public CellColors getStateColor(){
    return stateColor;
  }

  @Override
  public int getOrdinal(){
    return this.ordinal();
  }

}
