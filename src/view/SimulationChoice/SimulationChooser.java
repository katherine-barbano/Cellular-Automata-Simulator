package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class GameOfLifeButton extends Button {

  public static final String BUTTON_PROPERTIES = "GameOfLifeButton";

  public GameOfLifeButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setOnAction(event -> chooseSimulation());
  }

  private void chooseSimulation(){
    //System.out.println("change sim");
  }

}
