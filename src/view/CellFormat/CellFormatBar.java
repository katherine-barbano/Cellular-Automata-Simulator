package view.CellFormat;

import controller.State;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javax.swing.text.html.CSS;
import model.SimulationType;
import view.CellDisplay;
import view.GridDisplay;

public class CellFormatBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  public static final String CSS_FILL_FIELD="-fx-fill:";
  private GridDisplay myGridDisplay;
  private State[] myPossibleStates;
  private CellColorChooser myColorChoice;
  private ImageChooser myImageChoice;
  private StateChooser myStateChoice;
  private FormatButton myFormatButton;

  public CellFormatBar(GridDisplay gridDisplay, State[] possibleStates, ResourceBundle resources) {
    super();

    this.myPossibleStates = possibleStates;
    this.myGridDisplay = gridDisplay;
    this.myStateChoice = new StateChooser(myPossibleStates);
    this.myColorChoice = new CellColorChooser();
    this.myFormatButton = new FormatButton(resources);
    myFormatButton.setOnAction(event -> updateCellColor());

    this.myImageChoice = new ImageChooser(resources);


    addToRoot();

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  private void addToRoot(){
    this.getChildren().add(myStateChoice);
    this.getChildren().add(myColorChoice);
    this.getChildren().add(myFormatButton);
    this.getChildren().add(myImageChoice);

  }

  public void updateCellColor(){

    State chosenState = myStateChoice.getMySelection();
    Paint chosenColor= myColorChoice.getChosenColor();
    Image chosenImage = myImageChoice.getChosenImage();

    List<CellDisplay> cellsWithChosenState = myGridDisplay.getCellListByState(chosenState);
    for(CellDisplay cell: cellsWithChosenState){
      cell.setFill(chosenColor);
      //cell.setFill(new ImagePattern(chosenImage));
    }
  }

}
