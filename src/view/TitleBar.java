package view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TitleBar extends FlowPane {

  public static final int TITLE_BAR_X_POSITION =150;
  public static final int TITLE_BAR_Y_POSITION =10;
  public static final int TITLE_BAR_WIDTH=100;
  public static final int TITLE_BAR_HEIGHT =20;

  public static final int TITLE_TEXT_X_POSITION=160;
  public static final int TITLE_TEXT_Y_POSITION=25;

  private Group myRoot;
  private ResourceBundle myResources;
  private Rectangle titleBar;
  private Text titleText;

  public TitleBar(Group root, ResourceBundle resources, String simulationType){
    super();
    this.myRoot = root;
    this.setId("title-bar");

    this.titleText= new Text(simulationType);
    this.titleText.setId("title-text");
    this.getChildren().add(titleText);
    this.setAlignment(Pos.CENTER);

  }

  public void updateTitleText(String inputTitle){
      this.titleText.setText(inputTitle);
  }

}
