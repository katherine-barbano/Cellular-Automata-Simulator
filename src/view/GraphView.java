package view;

import controller.StateType;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Grid;
import view.GraphElements.SimulationGraph;


public class GraphView {

  public static final String RESOURCES = "resources/";
  public static final String STYLESHEET = "view.css";
  public static final String RESOURCE_BUNDLE = "View";

  private int myWidth;
  private int myHeight;

  private Grid myGrid;
  private VBox myRoot;
  private ResourceBundle myBundle;

  private Node myTitleBar;
  private Node myGraph;
  private StateType[] myStates;

  /**
   * Create Simulation View from initial Grid
   * @param grid Initial grid in scene
   */
  public GraphView(Grid grid){
    myBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+SimulationView.RESOURCE_BUNDLE);
    myGrid = grid;
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
    this.myStates = states;

    createUIElements(simulationType);

    Scene scene= new Scene(myRoot, width, height);
    scene.getStylesheets().add(RESOURCES+STYLESHEET);
    return scene;
  }

  private void createUIElements(String simulationType){
    myRoot = new VBox();
    myRoot.getStyleClass().add("vbox");

    myTitleBar = new TitleBar(myBundle, simulationType);
    myGraph = new SimulationGraph(myStates);
  }

  private void addUIElementsToRoot(){
    myRoot.getChildren().add(myTitleBar);
    myRoot.getChildren().add(myGraph);
  }

  public void updateGraphForNewGrid(){
    for(StateType state: myStates){
      //myGraph.updateSeries(state,myGrid.getNumCellsForStateType(state))
    }
  }

}
