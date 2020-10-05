package view;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import model.Cell;
import model.Grid;

public class GridDisplay extends GridPane {


  public GridDisplay(int[][] cellArray){
    super();
    //Cell[][] cellArray = grid.getCellGrid();
    addAllCells(cellArray);
    this.setAlignment(Pos.CENTER);
    this.getStyleClass().add("grid-display");

  }

  private void addAllCells(int[][] cellArray){
    for(int row=0; row<cellArray.length; row++){
      for(int col=0; col<cellArray[row].length;col++){
        super.add(new CellDisplay(cellArray[row][col]),row,col);
      }
    }
  }
}
