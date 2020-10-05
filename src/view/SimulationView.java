package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.Grid;
import view.buttons.ControlButtonBar;
import view.buttons.SimulationButtonBar;

public class SimulationView {

  private static final String RESOURCES = "src/resources/";
  public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final String STYLESHEET = "view.css";

  private Grid myGrid;
  private ResourceBundle myBundle;
  private VBox myRoot;

  private int myHeight;
  private int myWidth;

  private TitleBar myTitleBar;
  private GridDisplay myGridDisplay;
  private double myGridHeight;
  private ControlButtonBar myControlButtons;
  private SimulationButtonBar mySimulationButtons;

  public SimulationView(Grid grid){
    //myBundle = ResourceBundle.getBundle("/src/resources/Model");
    myGrid=grid;
  }

  public Scene setupScene(String simulationType, int width, int height)
      throws MalformedURLException {

    this.myWidth=width;
    this.myHeight=height;

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

    myGridHeight=findGridHeight();
    myGridDisplay = new GridDisplay(myGrid, myGridHeight);
    myRoot.getChildren().add(myGridDisplay);

    myControlButtons = new ControlButtonBar();
    myRoot.getChildren().add(myControlButtons);

    mySimulationButtons = new SimulationButtonBar();
    myRoot.getChildren().add(mySimulationButtons);

  }

  public void updateGridDisplay(Grid nextGrid){
    myGrid=nextGrid;
    myGridDisplay=new GridDisplay(nextGrid,myGridHeight);
  }

  public double findGridHeight(){
    return myHeight - myTitleBar.getHeight() - myControlButtons.getHeight() - mySimulationButtons.getHeight();
  }

}
