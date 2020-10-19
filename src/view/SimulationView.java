package view;

import controller.StateType;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Grid;
import view.CellFormat.CellFill;
import view.CellFormat.CellFormatBar;
import view.ControlButtons.ControlButtonBar;
import view.SimulationChoice.SimulationButtonBar;
import view.DarkLightModeButtons.DarkLightModeBar;
import view.FileButtons.FileButtonBar;

/**
 * SimulationView sets up and updates the User Interface.
 */
public class SimulationView {

  public static final String RESOURCES = "resources/";
  public static final String LIGHT_MODE_CSS = "lightMode.css";
  public static final String DARK_MODE_CSS = "darkMode.css";
  public static final String RESOURCE_BUNDLE = "View";
  public static final int BUTTON_BAR_HEIGHT = 50;
  public static final Map<StateType, CellFill> STATE_COLOR_MAP = new HashMap<>();
  public static final String START_GRAPH_PROPERTIES = "GraphViewButton";

  private Grid myGrid;
  private ResourceBundle myBundle;
  private VBox myRoot;

  private int myHeight;
  private int myWidth;

  private Scene myScene;
  private TitleBar myTitleBar;
  private GridDisplay myGridDisplay;
  private double myGridHeight;
  private ControlButtonBar myControlButtons;
  private CellFormatBar myCellFormatBar;
  private DarkLightModeBar myDarkLightModeBar;
  private FileButtonBar myFileButtons;
  private SimulationButtonBar mySimulationButtons;
  private OpenGraphViewBar myOpenGraphViewBar;
  private int numButtonBars=6;

  /**
   * Create Simulation View from initial Grid
   * @param grid Initial grid in scene
   */
  public SimulationView(Grid grid, String language){
    myBundle = ResourceBundle.getBundle(RESOURCES+RESOURCE_BUNDLE+language);
    myGrid=grid;
  }


  /**
   * Setup the Scene for the UI
   * @param simulationType Type of simulation
   * @param width Width of window
   * @param height Height of Window
   * @return Scene to be displayed in window
   */
  public Scene setupScene(String simulationType,  StateType[] states, int width, int height) {
    this.myWidth=width;
    this.myHeight=height;

    initializeStateColorMap(states);
    createUIElements(simulationType, states);
    addUIElementsToRoot();
    setDarkLightModeActions();

    myScene= new Scene(myRoot, width, height);
    myScene.getStylesheets().add(RESOURCES+ LIGHT_MODE_CSS);
    return myScene;
  }

  private void createUIElements(String simulationType, StateType[] states){
    myRoot = new VBox();
    myRoot.getStyleClass().add("vbox");

    myTitleBar=new TitleBar(myBundle, simulationType);
    myControlButtons = new ControlButtonBar(myBundle);
    myDarkLightModeBar = new DarkLightModeBar(myBundle);
    myFileButtons = new FileButtonBar(myBundle);
    mySimulationButtons = new SimulationButtonBar(myBundle);
    myOpenGraphViewBar = new OpenGraphViewBar(myBundle);

    myGridHeight = findGridHeight();
    myGridDisplay = new GridDisplay(myGrid, myGridHeight, simulationType);
    myCellFormatBar = new CellFormatBar(myGridDisplay, states, myBundle);

  }

  private void addUIElementsToRoot(){
    myRoot.getChildren().add(myTitleBar);
    myRoot.getChildren().add(myGridDisplay);
    myRoot.getChildren().add(myControlButtons);
    myRoot.getChildren().add(myCellFormatBar);
    myRoot.getChildren().add(myDarkLightModeBar);
    myRoot.getChildren().add(myFileButtons);
    myRoot.getChildren().add(mySimulationButtons);
    myRoot.getChildren().add(myOpenGraphViewBar);
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
    return myHeight - numButtonBars * BUTTON_BAR_HEIGHT;
  }

  /**
   * Accessor for buttons in Control Button Bar
   * @return the ControlButtonBar
   */
  public ControlButtonBar getMyControlButtons() {
    return myControlButtons;
  }

  /**
   * Accessor for buttons in File Button Bar
   * @return the FileButtonBar
   */
  public FileButtonBar getMyFileButtons() { return myFileButtons; }

  public OpenGraphViewBar getMyOpenGraphViewBar() {
    return myOpenGraphViewBar;
  }

  public Grid getCurrentGridInDisplay(){
    return myGrid;
  }

  public void addExceptionMessage(String message){
    //Text ExceptionText = new Text(message);
    //myRoot.getChildren().add(ExceptionText);
    Alert alert = new Alert(AlertType.ERROR);
    //alert.setTitle(myResources.getString("ErrorTitle"));
    alert.setContentText(message);
    alert.showAndWait();

  }

  public SimulationButtonBar getMySimulationButtons() {
    return mySimulationButtons;
  }

  private void setDarkLightModeActions(){
    myDarkLightModeBar.getMyDarkButton().setOnAction(event -> setDarkMode());
    myDarkLightModeBar.getMyLightButton().setOnAction(event -> setLightMode());
  }

  public void setDarkMode(){
    myScene.getStylesheets().add(RESOURCES + DARK_MODE_CSS);
  }

  public void setLightMode(){
    myScene.getStylesheets().remove(RESOURCES + DARK_MODE_CSS);
  }


  private void initializeStateColorMap(StateType[] stateTypes){
    for(StateType state: stateTypes){
      STATE_COLOR_MAP.put(state,state.getDefaultColor());
    }
  }

}
