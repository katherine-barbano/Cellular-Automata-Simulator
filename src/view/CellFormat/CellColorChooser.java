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

  public static final CellColors[] CELL_COLORS = CellColors.values();
  private ObservableList myColors;
  private CellFill myChosenColor;

  public CellColorChooser(){
    super();
    this.setId("color-chooser");
    this.setTooltip(new Tooltip("Select a Color: "));

    String [] colorNames = getColorNames();

    myColors = FXCollections.observableArrayList(Arrays.asList(colorNames));
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


  private String[] getColorNames(){
    String[] names = new String[CELL_COLORS.length];

    int count = 0;
    for(CellColors color: CELL_COLORS){
      names[count] = CELL_COLORS[count].toString();
      count++;
    }

    return names;
  }

}
