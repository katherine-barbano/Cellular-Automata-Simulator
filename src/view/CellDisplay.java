package view;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class CellDisplay extends Rectangle {

  private BorderPane myRoot;

  public CellDisplay(int xPos, int yPos,int width, int height, String state, BorderPane root){
    super(xPos,yPos,width, height);
    this.setId(state);
    this.myRoot=root;
    this.myRoot.setCenter(this);
  }

}
