package view;

import controller.State;
import controller.StateType;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.css.StyleClass;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

/**
 * The GridDisplay class extends the GridPane class.  It is responsible for displaying all of the
 * CellDisplay objects in the current grid.
 * @author Heather Grune (hlg20)
 */
public class GridDisplay extends GridPane {

  private Grid myGrid;
  private String mySimulationType;
  private double myCellSize;
  private ResourceBundle myResources;

  public GridDisplay(Grid grid, double height, String simulationType, ResourceBundle resources){
    super();
    this.myGrid=grid;
    this.mySimulationType=simulationType;
    this.myResources = resources;
    this.setAlignment(Pos.CENTER);
    this.setPrefHeight(height);
    this.getStyleClass().add("grid-display");
    this.setId("gridDisplay");

    this.myCellSize=height/grid.getGridNumberOfRows();
    addAllCells();
  }

  private void addAllCells(){
    for(int row=0; row<myGrid.getGridNumberOfRows(); row++){
      for(int col=0; col<myGrid.getGridNumberOfColumns();col++){
        CellDisplay newCell = new CellDisplay(myGrid.getCell(row,col).getCurrentState().getStateType(),myCellSize, this, myResources);
        this.add(newCell,col,row);
      }
    }
  }

  /**
   * This method is used to update the GridDisplay to display a new Grid
   * @param nextGrid The new Grid to be displayed
   * @return the new GridDisplay
   */
  public GridDisplay updateCellsInGridDisplay(Grid nextGrid){
    this.getChildren().clear();
    myGrid=nextGrid;

    addAllCells();

    return this;
  }

  /**
   * Get a list of the CellDisplay objects in the GridDisplay with a given state
   * @param inputState chosen StateType
   * @return List of CellDisplay objects with the chosen state
   */
  public List<CellDisplay> getCellListByState(StateType inputState){
    List<CellDisplay> stateCells = new ArrayList();
    for(Node cell: this.getChildren()){
        checkStateOfCellDisplay(stateCells,inputState,cell);
    }
    return stateCells;
  }

  private void checkStateOfCellDisplay(List<CellDisplay> currentCellList, StateType searchState, Node node){
    CellDisplay cellDisplay = (CellDisplay) node;

    if (cellDisplay.getMyStateType().equals(searchState)){
      currentCellList.add(cellDisplay);
    }
  }

  /**
   * This is method is used to update the cells in the GRID data structure, after a CellDisplay
   * component has been clicked.
   * @param row Row of cellDisplay clicked
   * @param col Column of cellDisplay clicked
   * @param newState New StateType of the cell
   */
  public void updateCellInGrid(int row, int col, StateType newState){
    myGrid.getCell(row,col).getCurrentState().setStateType(newState);
  }

  /**
   * Accessor for the current grid in the GridDisplay
   * @return
   */
  public Grid getMyGrid(){
    return myGrid;
  }

  /**
   * Accessor for the current Simulation type of the Grid Display
   * @return
   */
  public String getMySimulationType(){
    return mySimulationType;
  }
}
