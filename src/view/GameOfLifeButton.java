package view;

import javafx.scene.control.Button;

public class GameOfLifeButton extends Button {

  public static final String BUTTON_TEXT = "Game of Life";

  public GameOfLifeButton(){
    super(BUTTON_TEXT);
    this.setOnAction(event -> chooseSimulation());
  }

  private void chooseSimulation(){

  }

}
