package controller;

import java.util.List;

public interface State {

  abstract int[] getNextPosition();

  abstract String toString();
}
