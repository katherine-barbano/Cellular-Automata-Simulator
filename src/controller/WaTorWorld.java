package controller;

import java.util.List;
import java.util.Random;

public enum WaTorWorld implements State{
  FISH(0, List.of(1,3,4,6)),
  SHARK(0, List.of(1,3,4,6)),
  EMPTY();

  private int age;
  private int nextPosition;

  WaTorWorld(int defaultAge, List<Integer> openPositions){
    this.age = defaultAge;
    this.nextPosition = getOpenPosition(openPositions);
  }

  WaTorWorld(){
  }

  public int getAge(){
    return this.age;
  }
  public void setAge(int newAge) {
    this.age = newAge;
  }

  public int getNextPosition() {
    return this.nextPosition;
  }

  public void setNextPosition(List<Integer> newOpenPositions) {
    this.nextPosition = getOpenPosition((newOpenPositions));
  }

  private int getOpenPosition(List<Integer> openPositions) {
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size()-1);
    return openPositions.get(randomIndex);
  }

}
