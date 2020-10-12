package controller;

import java.util.List;
import java.util.Random;

public enum WaTorWorldState implements State{
  FISH(0),
  SHARK(0),
  EMPTY();

  private int age;
<<<<<<< HEAD
  private int[] nextPosition = new int[] {0,0};

  WaTorWorldState(int defaultAge){
    this.age = defaultAge;
    //this.nextPosition = determineOpenPosition(openPositions);
=======
  private int[] nextPosition;

  WaTorWorldState(int defaultAge){
    this.age = defaultAge;
    this.nextPosition = new int[2];
    nextPosition[0]=0;
    nextPosition[1]=0;
>>>>>>> 298aecb6147066bd57059626a1756cdb16b7031d
  }

  WaTorWorldState() {
  }

  public int getAge(){
    return this.age;
  }
  public void setAge(int newAge) {
    this.age = newAge;
  }

<<<<<<< HEAD
  @Override
=======
>>>>>>> 298aecb6147066bd57059626a1756cdb16b7031d
  public int[] getNextPosition() {
    return this.nextPosition;
  }

<<<<<<< HEAD
/*  public void setNextPosition(List<int[]> newOpenPositions) {
    this.nextPosition = determineOpenPosition((newOpenPositions));
  }*/

  public void determineOpenPosition(List<int[]> openPositions) {
=======
  public void setNextPosition(List<int[]> newOpenPositions) {
    this.nextPosition = getOpenPosition((newOpenPositions));
  }

  private int[] getOpenPosition(List<int[]> openPositions) {
>>>>>>> 298aecb6147066bd57059626a1756cdb16b7031d
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size()-1);
    this.nextPosition = openPositions.get(randomIndex);
  }

}
