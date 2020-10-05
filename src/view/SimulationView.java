package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimulationView {

  private static final String RESOURCES = "src/resources/";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final String STYLESHEET = "view.css";

  private ResourceBundle myBundle;
  private TitleBar myTitleBar;
  private VBox myRoot;
  private GridDisplay myGridDisplay;
  private ControlButtonBar myControlButtons;

  public SimulationView(){
    //myBundle = ResourceBundle.getBundle("/src/resources/Model");

  }

  public Scene setupScene(String simulationType, int width, int height)
      throws MalformedURLException {


    createUIElements(simulationType);

    Scene scene= new Scene(myRoot, width, height);
    File file = new File(RESOURCES+STYLESHEET);
    scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
    return scene;
  }

  private void createUIElements(String simulationType){
    myRoot = new VBox();
    myRoot.getStyleClass().add("vbox");

    myTitleBar=new TitleBar(myBundle, simulationType);
    myRoot.getChildren().add(myTitleBar);

    int[][] cellArray = {{1,0,0,0,1},{1,1,1,1,1},{0,0,0,0,1},{1,1,0,1,1},{0,1,1,0,1}};

    myGridDisplay = new GridDisplay(cellArray);
    myRoot.getChildren().add(myGridDisplay);

    myControlButtons = new ControlButtonBar();
    myRoot.getChildren().add(myControlButtons);



  }


}
