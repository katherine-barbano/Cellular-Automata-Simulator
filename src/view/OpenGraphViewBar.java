package view;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationChoice.SetSimulationButton;
import view.SimulationChoice.SimulationChooser;

public class OpenGraphViewBar extends HBox {

  public static final String START_GRAPH_PROPERTIES = "GraphViewButton";
  private Button myStartGraphViewButton;

  public OpenGraphViewBar(ResourceBundle resources){
    myStartGraphViewButton = new Button(resources.getString(START_GRAPH_PROPERTIES));
    this.getChildren().add(myStartGraphViewButton);

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");

  }

  public Button getMyStartGraphViewButton(){
    return myStartGraphViewButton;
  }



}
