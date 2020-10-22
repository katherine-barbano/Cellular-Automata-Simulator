package view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * The Title Bar displays the title of the current simulation at the top of the screen.
 * @author Heather Grune (hlg20)
 */
public class TitleBar extends FlowPane {

  public static final int TITLE_BAR_HEIGHT=50;
  public static final String TITLE_STRING_IN_RESOURCES = "Title";
  private ResourceBundle myResources;
  private Text titleText;

  public TitleBar(ResourceBundle resources, String simulationType){
    super();

    this.getStyleClass().add("title-bar");
    this.myResources=resources;

    addText(simulationType);

    this.setAlignment(Pos.CENTER);
    this.setPrefHeight(TITLE_BAR_HEIGHT);

  }

  private void addText(String simulationType){
    String title = myResources.getString(simulationType+TITLE_STRING_IN_RESOURCES);
    this.titleText= new Text(title);
    this.titleText.getStyleClass().add("title-text");
    this.getChildren().add(titleText);
  }

  /**
   * Method to update the title text.  This should have been deleted as it is not used
   * throughout the program (a new scene and titleBar are created for every new simulation).
   * @param simulationType String representing the simulationType
   */
  public void updateTitleText(String simulationType){
    String title = myResources.getString(simulationType+TITLE_STRING_IN_RESOURCES);
    this.titleText.setText(title);
  }


}
