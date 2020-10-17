package controller.states;

import controller.State;
import controller.StateType;
import java.util.List;
import java.util.Random;

/***
 * Used directly by segregation. Extended by WaTor World to include age
 */
public class MovingState extends State {

  private int[] nextPosition;

  public MovingState(StateType name) {
    super(name);
  }

  public int[] getNextPosition() {
    return this.nextPosition;
  }

  public boolean nextPositionIsStationary() {
    return nextPosition[0] == 0 && nextPosition[1] == 0;
  }

  public void setNextPositionMove(List<int[]> newOpenPositions) {
    this.nextPosition = getOpenPosition((newOpenPositions));
  }

  public void setNextPositionStationary() {
    this.nextPosition = new int[]{0,0};
  }

  public int[] getOpenPosition(List<int[]> openPositions) {
    Random random = new Random();
    int randomIndex = random.nextInt(openPositions.size());
    return openPositions.get(randomIndex);
  }
}
