package view.buttons;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class FileButtonBar extends HBox {

  public static final int BUTTON_BAR_HEIGHT=50;
  public static final String NEW_FILE_BUTTON_PROPERTIES = "NewFileButton";

  private Button mySave;
  private Button myNewFile;

  public FileButtonBar(ResourceBundle bundle){

    this.mySave = new SaveButton(bundle);
    this.getChildren().add(mySave);

    this.myNewFile = new ButtonFromResources(bundle, NEW_FILE_BUTTON_PROPERTIES);
    this.getChildren().add(myNewFile);

    this.setPrefHeight(BUTTON_BAR_HEIGHT);
    this.getStyleClass().add("button-bar");
  }

  public Button getMySave() {
    return mySave;
  }

  public Button getMyNewFile() {
    return myNewFile;
  }

}
