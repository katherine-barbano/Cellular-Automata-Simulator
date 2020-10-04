package model;

/***
 * States represented as the integers from csv file.
 */
abstract class Neighborhood {
  Neighborhood() {
    createCSVValueToStateMap();
  }
  abstract int getNextState(int currentState);

  /***
   * Chose to make this abstract because some types of simulations might not have a regular mapping
   * of 0:first state, 1: second state, etc. correspondence. Can add letters, additional states, etc.
   */
  abstract void createCSVValueToStateMap();
}
