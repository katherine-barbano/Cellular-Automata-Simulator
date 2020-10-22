package controller.stateType;

import controller.StateType;
import view.CellFormat.CellColors;
import view.CellFormat.CellFill;
import view.CellFormat.CellImages;

/*
This enum serves to store the various states available for the rock paper scissors simulation and
assumes that the
order of the values listed correspond to integer values that would be found in the initial csv
configuration file. In addition, because this implements state type, each state type also keeps
track of its own color, which is set in this enum as a default color.
 */

public enum RockPaperScissorsState implements StateType {
  ROCK(CellImages.ROCK),
  PAPER(CellImages.PAPER),
  SCISSORS(CellImages.SCISSORS);

  private CellFill defaultColor;

  RockPaperScissorsState(CellFill color){
    this.defaultColor = color;
  }

  @Override
  public CellFill getDefaultColor() {
    return defaultColor;
  }
}
