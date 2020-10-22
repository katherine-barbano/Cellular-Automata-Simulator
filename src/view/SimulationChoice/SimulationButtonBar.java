package view.SimulationChoice;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationView;

/**
 * The Simulation Button Bar contains a dropdown menu of all of the Simulation Types and a
 * SetSimulationButton.  The user can use this menu to select a type of simulation and change
 * the Simulation View to start a new simulation with the chosen type.
 * @author Heather Grune (hlg20)
 */
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

  /**
   * Accessor for the set simulation button
   * @return Set Simulation Button
   */
  public Button getMySimulationButton(){
    return mySimulationButton;
  }

  /**
   * Accessor for the Simulation Type Dropdown menu
   * @return SimulationChooser
   */
  public SimulationChooser getSimulationChooser() {
    return mySimulationChoices;
  }

}
