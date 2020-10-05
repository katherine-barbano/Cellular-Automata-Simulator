package view.buttons;

import javafx.scene.control.Button;

public class PlayPauseButton extends Button {

  public static final String PLAY_PAUSE_BUTTON_TEXT = "Play/Pause";

  public PlayPauseButton(){
    super(PLAY_PAUSE_BUTTON_TEXT);
    this.setOnAction(event -> playPause());
  }

  private void playPause(){

  }

}
