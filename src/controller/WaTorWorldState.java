package controller;

import java.util.List;
import java.util.Random;

public enum WaTorWorldState implements State{
  FISH(0),
  SHARK(0),
  EMPTY();

  private int age;
  private int[] nextPosition = new int[] {0,0};

  WaTorWorldState(int defaultAge){
    this.age = defaultAge;
    //this.nextPosition = determineOpenPosition(openPositions);
  }

  WaTorWorldState(){
  }

  public int getAge(){
    return this.age;
  }
  public void setAge(int newAge) {
    this.age = newAge;
  }

  @Override
  public int[] getNextPosition() {
    return this.nextPosition;
  }

/*  public void setNextPosition(List<int[]> newOpenPositions) {
    this.nextPosition = determineOpenPosition((newOpenPositions));
  }*/

  public void determineOpenPosition(List<int[]> openPositions) {
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size()-1);
    this.nextPosition = openPositions.get(randomIndex);
  }

}
