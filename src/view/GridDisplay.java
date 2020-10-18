package view;

import controller.State;
import controller.StateType;
import java.util.ArrayList;
import java.util.List;
import javafx.css.StyleClass;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

public class GridDisplay extends GridPane {

  private Grid myGrid;
  private String mySimulationType;
  private double myCellSize;

  public GridDisplay(Grid grid, double height, String simulationType){
    super();
    this.myGrid=grid;
    this.mySimulationType=simulationType;
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
        CellDisplay newCell = new CellDisplay(myGrid.getCell(row,col).getCurrentState().getStateType(),myCellSize, this);
        this.add(newCell,col,row);
      }
    }
  }

  public GridDisplay updateCellsInGridDisplay(Grid nextGrid){
    this.getChildren().clear();
    myGrid=nextGrid;

    addAllCells();

    return this;
  }

  public List<CellDisplay> getCellListByState(StateType inputState){
    List<CellDisplay> stateCells = new ArrayList();
    for(Node cell: this.getChildren()){
        checkStateOfCellDisplay(stateCells,inputState,cell);
    }
    return stateCells;
  }

  private void checkStateOfCellDisplay(List<CellDisplay> currentCellList, StateType searchState, Node node){
    CellDisplay cellDisplay = (CellDisplay) node;
    if (cellDisplay.getMyStateType().equals(searchState))
      currentCellList.add(cellDisplay);
  }

  public void updateCellInGrid(int row, int col, StateType newState){
    myGrid.getCell(row,col).getCurrentState().setStateType(newState);
  }

  public Grid getMyGrid(){
    return myGrid;
  }

  public String getMySimulationType(){
    return mySimulationType;
  }
}
