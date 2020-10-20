package view.DarkLightModeButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationView;

/**
 * The DarkLightModeBar includes a light mode button and dark mode button.  It is used to change
 * the Simulation View between light and dark modes
 */
public class DarkLightModeBar extends HBox {

  private Button myLightButton;
  private Button myDarkButton;

  public DarkLightModeBar(ResourceBundle resources){
    this.myLightButton = new LightModeButton(resources);
    this.getChildren().add(myLightButton);

    this.myDarkButton = new DarkModeButton(resources);
    this.getChildren().add(myDarkButton);

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
    this.setId("dark-light-bar");
  }

  public Button getMyLightButton() { return myLightButton; }

  public Button getMyDarkButton() { return myDarkButton; }

}
