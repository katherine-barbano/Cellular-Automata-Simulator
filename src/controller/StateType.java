package controller;

import view.CellFormat.CellColors;
import view.CellFormat.CellFill;

/*
This interface is responsible for organizing the state types and has a default color method that
returns the default color associated with the enums of state types. There are no real instances of
this interface, but the enums of state types for the various simulations all extend this interface
 */

public interface StateType {

  /*
  This method will return the default color of type cell fill associated with a certain state type
   */
  CellFill getDefaultColor();

}
