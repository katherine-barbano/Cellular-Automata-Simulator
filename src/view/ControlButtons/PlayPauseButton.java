package view.ControlButtons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * The Play/Pause button will start and stop the simulation.
 */
public class PlayPauseButton extends Button {

  public static final String PLAY_PAUSE_BUTTON_PROPERTIES = "PlayPauseButton";

  public PlayPauseButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(PLAY_PAUSE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    //this.setOnAction(event -> playPause());
  }

  private void playPause(){
    System.out.println("should pause/play");
  }

}
