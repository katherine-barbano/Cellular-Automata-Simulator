/*
package view.SimulationChoice;

import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

public class SimulationChooser extends ChoiceBox {

  public static final String CHOICE_SUFFIX = "Title";
  public static final SimulationType[] SIMULATION_TYPES = SimulationType.values();
  private String[] simulationTypeNames;
  private ResourceBundle myBundle;
  private ObservableList myTypes;
  private SimulationType myChosenType;

  public SimulationChooser(ResourceBundle resources){
    super();
    this.myBundle=resources;
    this.setTooltip(new Tooltip("Select a Simulation Type: "));
    this.simulationTypeNames = getNamesFromSimulationTypes();

    myTypes = FXCollections.observableArrayList(Arrays.asList(simulationTypeNames));
    this.setItems(myTypes);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenType = SIMULATION_TYPES[newValue.intValue()];
      }
    });
  }

  private String[] getNamesFromSimulationTypes(){
    int numTypes = SIMULATION_TYPES.length;
    String[] names = new String[numTypes];
    for(int num=0;num<numTypes;num++){
      names[num]=myBundle.getString(SIMULATION_TYPES[num].toString()+CHOICE_SUFFIX);
    }
    return names;
  }

  public SimulationType getMyChosenType(){
    return myChosenType;
  }

}
*/
