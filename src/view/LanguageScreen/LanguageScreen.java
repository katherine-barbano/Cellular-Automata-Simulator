package view.LanguageScreen;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The language screen creates the interface for the user to choose the language of the simulation.
 * @author Heather Grune (hlg20)
 */
public class LanguageScreen{
  public static final String RESOURCES = "resources/";
  public static final String LANGUAGE_PROPERTIES = "LanguageScreen";
  public static final String USER_PROMPT_PROPERTIES = "UserPrompt";
  public static final String ENGLISH_PROPERTIES = "EnglishButton";
  public static final String SPANISH_PROPERTIES = "SpanishButton";
  public static final String FRENCH_PROPERTIES = "FrenchButton";
  public static final String LIGHT_MODE_CSS = "lightMode.css";

  private ResourceBundle myResources;
  private VBox myRoot = new VBox();

  private Scene myScene;
  private Text myUserPromptText;
  private FlowPane myUserPromptBox;
  private HBox myLanguageBar;
  private Button myEnglishButton;
  private Button mySpanishButton;
  private Button myFrenchButton;


  public LanguageScreen(){
    myResources = ResourceBundle.getBundle(RESOURCES+LANGUAGE_PROPERTIES);
  }

  /**
   * Setup Scene adds all the UI components to the Language Screen scene, applies a stylesheet,
   * and returns the scene.
   */
  public Scene setupScene(int width, int height) {

    createUIElements();
    addUIElementsToRoot();

    myScene= new Scene(myRoot, width, height);
    myScene.getStylesheets().add(RESOURCES+ LIGHT_MODE_CSS);
    return myScene;
  }

  private void createUIElements(){
    myRoot = new VBox();
    myRoot.setId("language-vbox");

    myUserPromptBox = new FlowPane();
    myUserPromptText = new Text(myResources.getString(USER_PROMPT_PROPERTIES));
    myUserPromptBox.getStyleClass().add("title-bar");

    myLanguageBar = new HBox();
    myLanguageBar.getStyleClass().add("button-bar");
    myEnglishButton = new Button(myResources.getString(ENGLISH_PROPERTIES));
    myEnglishButton.setId("english-button");
    mySpanishButton = new Button(myResources.getString(SPANISH_PROPERTIES));
    mySpanishButton.setId("spanish-button");
    myFrenchButton = new Button(myResources.getString(FRENCH_PROPERTIES));
    myFrenchButton.setId("french-button");
  }

  private void addUIElementsToRoot(){
    myUserPromptBox.getChildren().add(myUserPromptText);
    myRoot.getChildren().add(myUserPromptBox);

    myLanguageBar.getChildren().add(myEnglishButton);
    myLanguageBar.getChildren().add(mySpanishButton);
    myLanguageBar.getChildren().add(myFrenchButton);
    myRoot.getChildren().add(myLanguageBar);
  }

  /**
   * Accessor for the English Button
   * @return English Button
   */
  public Button getMyEnglishButton() {
    return myEnglishButton;
  }

  /**
   * Accessor for the Spanish button
   * @return Spanish Button
   */
  public Button getMySpanishButton(){
    return mySpanishButton;
  }

  /**
   * Accessor for the French Button
   * @return French Button
   */
  public Button getMyFrenchButton(){
    return myFrenchButton;
  }
}
