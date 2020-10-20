package view.CellFormat;

import controller.StateType;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.CellDisplay;
import view.GridDisplay;
import view.SimulationView;

/**
 * The Cell Format Bar allows the user to choose colors and images to fill cells with a chosen state.
 */
public class CellFormatBar extends HBox {

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

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
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
    CellFill chosenColor= myColorChoice.getChosenColor();
    if(chosenState !=null && chosenColor !=null){
      updateAllCellsWithChosenState(chosenState,chosenColor);
    }

  }

  private void updateCellImage() {
    StateType chosenState = myStateChoice.getMySelection();
    CellFill chosenImage = myImageChoice.getChosenImage();
    if(chosenState !=null && chosenImage !=null){
      updateAllCellsWithChosenState(chosenState,chosenImage);
    }


  }

  private void updateAllCellsWithChosenState(StateType chosenState, CellFill chosenFill){
    List<CellDisplay> cellsWithChosenState = myGridDisplay.getCellListByState(chosenState);
    for (CellDisplay cell : cellsWithChosenState) {
      cell.setFill(chosenFill.getCellFill());
      SimulationView.STATE_COLOR_MAP.put(chosenState,chosenFill);
    }
  }

}
