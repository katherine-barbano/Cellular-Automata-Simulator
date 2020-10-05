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

  private static final String RESOURCES = "resources/";
  public static final String STYLESHEET = "view.css";
  public static final String RESOURCE_BUNDLE = "View";

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
    myBundle = ResourceBundle.getBundle(RESOURCES+RESOURCE_BUNDLE);
    myGrid=grid;
  }

  public Scene setupScene(String simulationType, int width, int height) {
    this.myWidth=width;
    this.myHeight=height;

    createUIElements(simulationType);
    addUIElementsToRoot();

    Scene scene= new Scene(myRoot, width, height);
    scene.getStylesheets().add(RESOURCES+STYLESHEET);
    return scene;
  }

  private void createUIElements(String simulationType){
    myRoot = new VBox();
    myRoot.getStyleClass().add("vbox");

    myTitleBar=new TitleBar(myBundle, simulationType);
    myControlButtons = new ControlButtonBar(myBundle);
    mySimulationButtons = new SimulationButtonBar(myBundle);

    myGridHeight=findGridHeight();
    myGridDisplay = new GridDisplay(myGrid, myGridHeight);
  }

  private void addUIElementsToRoot(){
    myRoot.getChildren().add(myTitleBar);
    myRoot.getChildren().add(myGridDisplay);
    myRoot.getChildren().add(myControlButtons);
    myRoot.getChildren().add(mySimulationButtons);
  }

  public void updateGridDisplay(Grid nextGrid){
    myGrid=nextGrid;
    myGridDisplay=myGridDisplay.updateCellsInGridDisplay(nextGrid);
  }

  public double findGridHeight(){
    return myHeight - myTitleBar.getPrefHeight() - myControlButtons.getPrefHeight() - mySimulationButtons.getPrefHeight();
  }

}
