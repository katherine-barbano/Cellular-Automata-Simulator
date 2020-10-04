package view;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SimulationView {

  private static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");

  private ResourceBundle myBundle;
  private TitleBar myTitleBar;

  public SimulationView(){
    myBundle = ResourceBundle.getBundle("resources/View.properties");

  }

  public Scene setupScene(String simulationType, int width, int height){
    BorderPane root = new BorderPane();

    myTitleBar=new TitleBar(root, myBundle, simulationType);

    Scene scene= new Scene(root, width, height);
    return scene;
  }

}
