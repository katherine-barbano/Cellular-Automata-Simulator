package view.SimulationChoice;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationView;

public class SimulationButtonBar extends HBox {

  private SimulationChooser mySimulationChoices;
  private Button mySimulationButton;

  public SimulationButtonBar(ResourceBundle resources){
    mySimulationChoices = new SimulationChooser(resources);
    this.getChildren().add(mySimulationChoices);

    mySimulationButton=new SetSimulationButton(resources);
    this.getChildren().add(mySimulationButton);

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
    this.setId("simulation-bar");
  }

  public Button getMySimulationButton(){
    return mySimulationButton;
  }

  public SimulationChooser getSimulationChooser() {
    return mySimulationChoices;
  }

}
