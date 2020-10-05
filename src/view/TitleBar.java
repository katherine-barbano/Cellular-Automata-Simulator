package view;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class TitleBar extends FlowPane {

  private ResourceBundle myResources;
  private Text titleText;

  public TitleBar(ResourceBundle resources, String simulationType){
    super();
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
