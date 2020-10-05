package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class CellDisplay extends Rectangle {

  public CellDisplay(int state,double cellSize){
    super(cellSize, cellSize);
    this.setId(getStateFromInt(state));
    getStyleClass().add("cell-display");
  }

  private String getStateFromInt(int input){
    switch(input){
      case 0: return "dead";
      case 1: return "alive";
      default: return "none";
    }
  }

}
