package controller;

import java.util.List;
import java.util.Random;

public enum WaTorWorldState implements State{
  FISH(0),
  SHARK(0),
  EMPTY();

  private int age;
  private int[] nextPosition;

  WaTorWorldState(int defaultAge){
    this.age = defaultAge;
    this.nextPosition = new int[2];
    nextPosition[0]=0;
    nextPosition[1]=0;
  }

  WaTorWorldState() {
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

  public void setNextPosition(List<int[]> newOpenPositions) {
    this.nextPosition = getOpenPosition((newOpenPositions));
  }

  private int[] getOpenPosition(List<int[]> openPositions) {
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size()-1);
    return openPositions.get(randomIndex);
  }

  @Override
  public String toString() {
    return "WatorWorld";
  }

}
