package view.CellFormat;

import controller.GameOfLifeState;
import controller.State;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import view.CellDisplay;
import view.GridDisplay;

public class CellFormatBar extends HBox {

  public static final State[] STATE_PLACEHOLDER = GameOfLifeState.values();
  private GridDisplay myGridDisplay;
  private CellColorChooser myColorChoice;
  private StateChooser myStateChoice;
  private FormatButton myFormatButton;

  public CellFormatBar(GridDisplay gridDisplay, State inputState, ResourceBundle resources){
    super();

    this.myGridDisplay = gridDisplay;

    myStateChoice = new StateChooser(STATE_PLACEHOLDER);
    this.getChildren().add(myStateChoice);

    myColorChoice = new CellColorChooser();
    this.getChildren().add(myColorChoice);

    myFormatButton = new FormatButton(resources);
    this.getChildren().add(myFormatButton);
    myFormatButton.setOnAction(event -> updateCellColor());
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
