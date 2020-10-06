package view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

public class GridDisplay extends GridPane {

  private Grid myGrid;
  private double myCellSize;

  public GridDisplay(Grid grid, double height){
    super();
    this.myGrid=grid;
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
        CellDisplay newCell = new CellDisplay(myGrid.getCell(row,col).getCurrentState(),myCellSize);
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
}
