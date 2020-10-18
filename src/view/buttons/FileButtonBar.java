package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import view.SimulationView;

/**
 * File Button Bar contains Buttons to save the tile Configuration to a file, and to upload a
 * file with a new tile configuration.
 */
public class FileButtonBar extends HBox {

  private Button mySave;
  private Button myNewFile;

  public FileButtonBar(ResourceBundle bundle){

    this.mySave = new SaveButton(bundle);
    this.getChildren().add(mySave);

    this.myNewFile = new NewFileButton(bundle);
    this.getChildren().add(myNewFile);

    this.setPrefHeight(SimulationView.BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  /**
   * Accessor for Save button
   * @return Save button
   */
  public Button getMySave() {
    return mySave;
  }

  /**
   * Accessor for New File Button
   * @return Upload new file button
   */
  public Button getMyNewFile() {
    return myNewFile;
  }

}
