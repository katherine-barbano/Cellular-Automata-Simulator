package view.CellFormat;

import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CellColorChooser extends ChoiceBox {

  public static final Paint[] CELL_COLORS = {Color.BLACK ,Color.WHITE, Color.RED, Color.ORANGE,Color.YELLOW,
                                              Color.GREEN,Color.BLUE,Color.PURPLE};
  public static final String[] CELL_COLOR_NAMES= {"Black", "White", "Red", "Orange","Yellow","Green","Blue","Purple"};
  private ObservableList myColors;
  private Paint myChosenColor;

  public CellColorChooser(){
    super();
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

  public Paint getChosenColor(){
    return myChosenColor;
  }
}
