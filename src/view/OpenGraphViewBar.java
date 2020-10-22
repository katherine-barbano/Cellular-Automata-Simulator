package view;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationChoice.SetSimulationButton;
import view.SimulationChoice.SimulationChooser;

/**
 * The Open Graph View Bar creates the bar which contains the button that is used to open the
 * GraphView from the SimulationView
 * @author Heather Grune (hlg20)
 */
public class OpenGraphViewBar extends HBox {

  public static final String START_GRAPH_PROPERTIES = "GraphViewButton";
  private Button myStartGraphViewButton;

  public OpenGraphViewBar(ResourceBundle resources){
    myStartGraphViewButton = new Button(resources.getString(START_GRAPH_PROPERTIES));
    this.getChildren().add(myStartGraphViewButton);

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");

  }

  /**
   * Accessor for the Start Graph View Button
   * @return StartGraphViewButton
   */
  public Button getMyStartGraphViewButton(){
    return myStartGraphViewButton;
  }



}
