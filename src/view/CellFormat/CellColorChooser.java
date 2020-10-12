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
  private ObservableList myColors;
  private Paint myChosenColor;

  public CellColorChooser(){
    super();
    this.setTooltip(new Tooltip("Select a Color: "));

    myColors = FXCollections.observableArrayList(Arrays.asList(CELL_COLORS));
    this.setItems(myColors);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenColor= (Paint) myColors.get(newValue.intValue());
      }
    });
  }

  public Paint getChosenColor(){
    return myChosenColor;
  }
}
