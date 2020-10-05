package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class PlayPauseButton extends Button {

  public static final String PLAY_PAUSE_BUTTON_PROPERTIES = "PlayPauseButton";

  public PlayPauseButton(ResourceBundle resources){
    super();
    String buttonText = resources.getString(PLAY_PAUSE_BUTTON_PROPERTIES);
    this.setText(buttonText);
    this.setOnAction(event -> playPause());
  }

  private void playPause(){

  }

}
