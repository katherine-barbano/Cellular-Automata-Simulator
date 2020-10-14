package controller.states;

/***
 * Used by Wator World
 */
public class MovingStateWithAge extends MovingState {

  private int age;

  public MovingStateWithAge(int defaultAge, String nameOfState){
    super(nameOfState);
    this.age = defaultAge;
    setNextPositionStationary();
  }

  public MovingStateWithAge(String nameOfState) {
    super(nameOfState);
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int newAge) {
    this.age = newAge;
  }
}
