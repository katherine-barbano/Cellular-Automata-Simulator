package view.CellFormat;

import controller.State;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

public class StateChooser extends ChoiceBox {

  private ObservableList myStates;
  private State mySelection;

  public StateChooser(State[] simulationStates){
    super();
    this.setId("state-chooser");
    this.setTooltip(new Tooltip("Select a State: "));

    myStates = FXCollections.observableArrayList(Arrays.asList(simulationStates));
    this.setItems(myStates);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
          mySelection= (State) myStates.get(newValue.intValue());
      }
    });
  }

  public State getMySelection(){
    return mySelection;
  }

  public void setMySelection(State state){
    mySelection=state;
  }



}
