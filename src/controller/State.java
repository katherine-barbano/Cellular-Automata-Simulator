package controller;

import java.util.List;
import view.CellFormat.CellColors;

public interface State {

  abstract int[] getNextPosition();

  abstract CellColors getStateColor();

  abstract void setStateColor(CellColors color);

  abstract int getOrdinal();

}
