package view;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TitleBar {

  public static final int TITLE_BAR_X_POSITION =150;
  public static final int TITLE_BAR_Y_POSITION =10;
  public static final int TITLE_BAR_WIDTH=100;
  public static final int TITLE_BAR_HEIGHT =20;

  public static final int TITLE_TEXT_X_POSITION=290;
  public static final int TITLE_TEXT_Y_POSITION=12;

  private BorderPane myRoot;
  private ResourceBundle myResources;
  private Rectangle titleBar;
  private Text titleText;
  private String titleString;

  public TitleBar(BorderPane root, ResourceBundle resources, String simulationType){
    this.myRoot = root;
    this.myResources = resources;

    this.titleBar = new Rectangle(TITLE_BAR_X_POSITION, TITLE_BAR_Y_POSITION,TITLE_BAR_WIDTH,
        TITLE_BAR_HEIGHT);
    this.titleBar.setId("title_bar");
    this.myRoot.setTop(titleBar);

    this.titleString=myResources.getString(simulationType);
    this.titleText= new Text(TITLE_TEXT_X_POSITION,TITLE_TEXT_Y_POSITION, titleString);
    this.titleText.setId("title_text");
    this.myRoot.setTop(titleText);

  }

  public void updateTitleText(){

  }

}
