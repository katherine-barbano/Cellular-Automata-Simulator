package view;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import model.Grid;
import view.buttons.ControlButtonBar;
import view.buttons.SimulationButtonBar;
import view.buttons.FileButtonBar;

/**
 * SimulationView sets up and updates the User Interface.
 */
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
  private FileButtonBar myFileButtons;
  private SimulationButtonBar mySimulationButtons;

  /**
   * Create Simulatoin View from initial Grid
   * @param grid Initial grid in scene
   */
  public SimulationView(Grid grid){
    myBundle = ResourceBundle.getBundle(RESOURCES+RESOURCE_BUNDLE);
    myGrid=grid;
  }

  /**
   * Setup the Scene for the UI
   * @param simulationType Type of simulation
   * @param width Width of window
   * @param height Height of Window
   * @return Scene to be displayed in window
   */
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
    myFileButtons = new FileButtonBar(myBundle);
    mySimulationButtons = new SimulationButtonBar(myBundle);

    myGridHeight = findGridHeight();
    myGridDisplay = new GridDisplay(myGrid, myGridHeight);
  }

  private void addUIElementsToRoot(){
    myRoot.getChildren().add(myTitleBar);
    myRoot.getChildren().add(myGridDisplay);
    myRoot.getChildren().add(myControlButtons);
    myRoot.getChildren().add(myFileButtons);
    myRoot.getChildren().add(mySimulationButtons);
  }

  /**
   * Update the GridDisplay to show the next grid in the simulation
   * @param nextGrid The next grid in the simulation
   */
  public void updateGridDisplay(Grid nextGrid){
    myGrid=nextGrid;
    myGridDisplay=myGridDisplay.updateCellsInGridDisplay(nextGrid);
  }

  /**
   * Get the height in pixels for the GridDisplay in the scene
   * @return Height of the GridDisplay
   */
  public double findGridHeight(){
    return myHeight - myTitleBar.getPrefHeight() - myControlButtons.getPrefHeight() - mySimulationButtons.getPrefHeight() - myFileButtons
        .getPrefHeight();
  }

  /**
   * Accessor for buttons in Control Button Bar
   * @return the ControlButtonBar
   */
  public ControlButtonBar getMyControlButtons() {
    return myControlButtons;
  }

  /**
   * Accessor for buttons in Speed Button Bar
   * @return the SpeedButtonBar
   */
  public FileButtonBar getMyFileButtons() { return myFileButtons; }

}
