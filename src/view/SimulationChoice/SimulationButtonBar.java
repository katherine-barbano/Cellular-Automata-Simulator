package view.SimulationChoice;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SimulationButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT = 50;
  private SimulationChooser myGameOfLife;
  private Button mySimulationButton;

  public SimulationButtonBar(ResourceBundle resources){
    myGameOfLife = new SimulationChooser(resources);
    this.getChildren().add(myGameOfLife);

    mySimulationButton=new SetSimulationButton(resources);
    this.getChildren().add(mySimulationButton);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  public Button getMySimulationButton(){
    return mySimulationButton;
  }

  public SimulationChooser getSimulationChooser() {
    return myGameOfLife;
  }

}
