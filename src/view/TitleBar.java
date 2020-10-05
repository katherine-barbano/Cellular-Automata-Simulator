package view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class TitleBar extends FlowPane {

  public static final int TITLE_BAR_HEIGHT=50;
  public static final String TITLE_STRING_IN_RESOURCES = "Title";
  private ResourceBundle myResources;
  private Text titleText;

  public TitleBar(ResourceBundle resources, String simulationType){
    super();

    this.setId("title-bar");
    this.myResources=resources;

    addText(simulationType);

    this.setAlignment(Pos.CENTER);
    this.setPrefHeight(TITLE_BAR_HEIGHT);

  }

  private void addText(String simulationType){
    String title = myResources.getString(simulationType+TITLE_STRING_IN_RESOURCES);
    this.titleText= new Text(title);
    this.titleText.setId("title-text");
    this.getChildren().add(titleText);
  }

  public void updateTitleText(String simulationType){
    String title = myResources.getString(simulationType+TITLE_STRING_IN_RESOURCES);
    this.titleText.setText(title);
  }


}
