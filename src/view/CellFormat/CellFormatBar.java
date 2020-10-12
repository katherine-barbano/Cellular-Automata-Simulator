package view.CellFormat;

import controller.State;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import model.SimulationType;
import view.CellDisplay;
import view.GridDisplay;

public class CellFormatBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  private GridDisplay myGridDisplay;
  private State[] myPossibleStates;
  private CellColorChooser myColorChoice;
  private StateChooser myStateChoice;
  private FormatButton myFormatButton;

  public CellFormatBar(GridDisplay gridDisplay, State[] possibleStates, ResourceBundle resources) {
    super();

    this.myGridDisplay = gridDisplay;
    this.myPossibleStates = possibleStates;

    myStateChoice = new StateChooser(myPossibleStates);
    this.getChildren().add(myStateChoice);

    myColorChoice = new CellColorChooser();
    this.getChildren().add(myColorChoice);

    myFormatButton = new FormatButton(resources);
    this.getChildren().add(myFormatButton);
    myFormatButton.setOnAction(event -> updateCellColor());

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  public void updateCellColor(){
    State chosenState = myStateChoice.getMySelection();
    Paint chosenColor= myColorChoice.getChosenColor();

    List<CellDisplay> cellsWithChosenState = myGridDisplay.getCellListByState(chosenState);
    for(CellDisplay cell: cellsWithChosenState){
      cell.setFill(chosenColor);
    }
  }

}
