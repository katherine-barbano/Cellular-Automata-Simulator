package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class SimulationView {

  private static final String RESOURCES = "data/";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final String STYLESHEET = "resources/view.css";

  private ResourceBundle myBundle;
  private TitleBar myTitleBar;
  private Group myRoot;

  public SimulationView(){
    //myBundle = ResourceBundle.getBundle("src/resources/Model");

  }

  public Scene setupScene(String simulationType, int width, int height)
      throws MalformedURLException {
    myRoot = new Group();

    createUIElements(simulationType);

    Scene scene= new Scene(myRoot, width, height);
    File file = new File("src/resources/view.css");
    scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
    return scene;
  }

  private void createUIElements(String simulationType){
    myTitleBar=new TitleBar(myRoot, myBundle, simulationType);
    myRoot.getChildren().add(myTitleBar);

  }


}
