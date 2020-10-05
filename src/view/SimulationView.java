package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Grid;

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
  private SimulationButtonBar mySimulationButtons;

  public SimulationView(){
    //myBundle = ResourceBundle.getBundle("/src/resources/Model");

  }

  public Scene setupScene(Grid grid, String simulationType, int width, int height)
      throws MalformedURLException {

    createUIElements(grid, simulationType);

    Scene scene= new Scene(myRoot, width, height);
    File file = new File(RESOURCES+STYLESHEET);
    scene.getStylesheets().add(file.toURI().toURL().toExternalForm());
    return scene;
  }

  private void createUIElements(Grid grid, String simulationType){
    myRoot = new VBox();
    myRoot.getStyleClass().add("vbox");

    myTitleBar=new TitleBar(myBundle, simulationType);
    myRoot.getChildren().add(myTitleBar);

    myGridDisplay = new GridDisplay(grid);
    myRoot.getChildren().add(myGridDisplay);

    myControlButtons = new ControlButtonBar();
    myRoot.getChildren().add(myControlButtons);

    mySimulationButtons = new SimulationButtonBar();
    myRoot.getChildren().add(mySimulationButtons);

  }


}
