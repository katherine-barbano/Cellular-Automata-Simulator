package view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ErrorMessageScreen {

  public static final String RESOURCES = "resources/";
  public static final String LIGHT_MODE_CSS = "lightMode.css";

  private ResourceBundle myResources;
  private VBox myRoot = new VBox();
  private String myErrorString;

  private Scene myScene;
  private FlowPane myErrorBox;
  private Text myErrorText;

  /**
   */
  public ErrorMessageScreen(String errorString){
    this.myErrorString = errorString;
  }

  /**
   */
  public Scene setupScene(int width, int height) {

    createUIElements();
    addUIElementsToRoot();

    myScene= new Scene(myRoot, width, height);
    myScene.getStylesheets().add(RESOURCES + LIGHT_MODE_CSS);
    return myScene;
  }

  private void createUIElements(){
    myRoot = new VBox();
    myRoot.setId("language-vbox");

    myErrorBox = new FlowPane();
    myErrorText = new Text(myResources.getString(myErrorString));
    myErrorBox.getStyleClass().add("title-bar");

  }

  private void addUIElementsToRoot(){
    myErrorBox.getChildren().add(myErrorText);
    myRoot.getChildren().add(myErrorBox);
  }

}
