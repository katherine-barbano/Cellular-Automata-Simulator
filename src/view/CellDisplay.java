package view;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class CellDisplay extends Rectangle {

  public static final int CELL_SIZE=30;

  public CellDisplay(int state){
    super(CELL_SIZE, CELL_SIZE);
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
