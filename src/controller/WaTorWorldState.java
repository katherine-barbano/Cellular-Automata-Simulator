package controller;

import java.util.List;
import java.util.Random;

public enum WaTorWorldState implements State{
  FISH(0, "Fish"),
  SHARK(0, "Shark"),
  EMPTY("Empty");

  private int age;
  private String stateName;
  private int[] nextPosition;

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
    int randomIndex = random.nextInt(openPositions.size()-1);
    return openPositions.get(randomIndex);
  }

  public String toString() {
    return this.stateName;
  }

}
