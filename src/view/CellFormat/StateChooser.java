package view.CellFormat;

import controller.State;
import controller.StateType;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

/**
 * The StateChooser creates a dropdown menu with all of the state choices for a given type of
 * Simulation
 */
public class StateChooser extends ChoiceBox {

  private ObservableList myStates;
  private StateType mySelection;

  public StateChooser(StateType[] simulationStates){
    super();
    this.setId("state-chooser");
    this.setTooltip(new Tooltip("Select a State: "));

    myStates = FXCollections.observableArrayList(Arrays.asList(simulationStates));
    this.setItems(myStates);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
          mySelection= (StateType) myStates.get(newValue.intValue());
      }
    });
  }

  /**
   * Acessor for the selected State.
   * @return the selected StateType
   */
  public StateType getMySelection(){
    return mySelection;
  }

  /**
   * Primarily used in testing, set selected state
   * @param state selected StateType
   */
  public void setMySelection(StateType state){
    mySelection=state;
  }

}
