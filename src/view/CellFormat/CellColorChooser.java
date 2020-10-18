package view.CellFormat;

import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

/**
 * CellColor Chooser Class will allow the user to change the color of cells with a chosen state.
 */
public class CellColorChooser extends ChoiceBox {

  public static final CellColors[] CELL_COLORS = {CellColors.BLACK , CellColors.WHITE, CellColors.RED, CellColors.ORANGE,
      CellColors.YELLOW,
                                              CellColors.GREEN, CellColors.BLUE, CellColors.PURPLE};
  public static final String[] CELL_COLOR_NAMES= {"Black", "White", "Red", "Orange","Yellow","Green","Blue","Purple"};
  private ObservableList myColors;
  private CellFill myChosenColor;

  public CellColorChooser(){
    super();
    this.setId("color-chooser");
    this.setTooltip(new Tooltip("Select a Color: "));

    myColors = FXCollections.observableArrayList(Arrays.asList(CELL_COLOR_NAMES));
    this.setItems(myColors);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenColor = CELL_COLORS[newValue.intValue()];
      }
    });
  }

  /**
   * Accessor for the Users chosen color
   * @return the chosen CellColor
   */
  public CellFill getChosenColor(){
    return myChosenColor;
  }

  public void setMyChosenColor(CellFill color){
    myChosenColor=color;
  }
}
