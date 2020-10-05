package view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

public class GridDisplay extends GridPane {

  private double myCellSize;

  public GridDisplay(Grid grid, double height){
    super();
    this.setAlignment(Pos.CENTER);
    this.setPrefHeight(height);
    this.getStyleClass().add("grid-display");

    this.myCellSize=height/grid.getGridNumberOfRows();
    addAllCells(grid);
  }

  private void addAllCells(Grid grid){
    for(int row=0; row<grid.getGridNumberOfRows(); row++){
      for(int col=0; col<grid.getGridNumberOfColumns();col++){
        CellDisplay newCell = new CellDisplay(grid.getCell(row,col).getCurrentState(),myCellSize);
        super.add(newCell,col,row);
      }
    }
  }
}
