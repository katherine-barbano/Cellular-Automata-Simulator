package controller.states;

/***
 * Used by Wator World
 */
public class MovingStateWithAge extends MovingState {

  private int age;

  public MovingStateWithAge(String nameOfState){
    super(nameOfState);
    this.age = 0;
    setNextPositionStationary();
  }

  public int getAge(){
    return this.age;
  }

  public void setAge(int newAge) {
    this.age = newAge;
  }
}
