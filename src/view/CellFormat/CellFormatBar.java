package view.CellFormat;

import controller.State;
import controller.StateType;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.CellDisplay;
import view.GridDisplay;

/**
 * The Cell Format Bar allows the user to choose colors and images to fill cells with a chosen state.
 */
public class CellFormatBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  private GridDisplay myGridDisplay;
  private StateType[] myPossibleStates;
  private CellColorChooser myColorChoice;
  private ImageChooser myImageChoice;
  private StateChooser myStateChoice;
  private Button myFormatButton;
  private Button myImageChooserButton;

  public CellFormatBar(GridDisplay gridDisplay, StateType[] possibleStates, ResourceBundle resources) {
    super();

    this.myPossibleStates = possibleStates;
    this.myGridDisplay = gridDisplay;
    this.myStateChoice = new StateChooser(myPossibleStates);
    this.myColorChoice = new CellColorChooser();
    this.myFormatButton = new ChangeColorButton(resources);
    myFormatButton.setOnAction(event -> updateCellColor());

    this.myImageChoice = new ImageChooser(resources);
    myImageChooserButton = new ChangeImageButton(resources);
    myImageChooserButton.setOnAction(event -> updateCellImage());

    addToRoot();

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
    this.setId("cell-format-bar");
  }

  private void addToRoot(){
    this.getChildren().add(myStateChoice);
    this.getChildren().add(myColorChoice);
    this.getChildren().add(myFormatButton);
    this.getChildren().add(myImageChoice);
    this.getChildren().add(myImageChooserButton);
  }

   private void updateCellColor() {
    StateType chosenState = myStateChoice.getMySelection();
    CellColors chosenColor= myColorChoice.getChosenColor();
    updateAllCellsWithChosenState(chosenState,chosenColor);
  }

  private void updateCellImage() {
    StateType chosenState = myStateChoice.getMySelection();
    CellColors chosenImage = myImageChoice.getChosenImage();
    updateAllCellsWithChosenState(chosenState,chosenImage);
  }

  private void updateAllCellsWithChosenState(StateType chosenState, CellColors chosenFill){
    List<CellDisplay> cellsWithChosenState = myGridDisplay.getCellListByState(chosenState);
    for (CellDisplay cell : cellsWithChosenState) {
      cell.setFill(chosenFill.getCellColor());
      cell.getMyState().setStateColor(chosenFill);
    }
  }

}
