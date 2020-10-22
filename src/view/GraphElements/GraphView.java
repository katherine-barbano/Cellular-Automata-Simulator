package view.GraphElements;

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
import view.SimulationView;
import view.TitleBar;

/**
 * The Graph View is responsible for creating the scene which contains the Graph and a title bar
 * in a separate window from the Simulation View.  The GraphView should be updated for each step in
 * the animation.
 * @author Heather Grune (hlg20)
 */
public class GraphView {

  public static final String RESOURCES = "resources/";
  public static final String STYLESHEET = "lightMode.css";


  private Grid myGrid;
  private VBox myRoot;
  private ResourceBundle myBundle;

  private FlowPane myTitleBar;
  private SimulationGraph myGraph;
  private StateType[] myStates;

  public GraphView(Grid grid, String language){
    myBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+SimulationView.RESOURCE_BUNDLE+language);
    myGrid = grid;
  }


  /**
   * This method sets up and returns the initial scene for the graph view.
   * @param simulationType A string representing the type of simulation
   * @param stepNumber The number of steps through the current simulation
   * @param states An array of all the possible states types for the curren simulation
   * @param width The width of the scene
   * @param height The width of the scene
   * @return The initial scene
   */
  public Scene setupScene(String simulationType, double stepNumber, StateType[] states, int width, int height) {
    this.myStates = states;

    createUIElements(simulationType);
    addUIElementsToRoot();
    updateGraph(stepNumber);

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

  /**
   * Update the Graph to display the data for a new grid
   * @param nextGrid The grid to be displayed
   * @param elapsedTime The number of steps through a simulation
   */
  public void updateCurrentGrid(Grid nextGrid,double elapsedTime){
    myGrid = nextGrid;
    updateGraph(elapsedTime);
  }

  private void updateGraph(double stepNumber){
    for(StateType state: myStates){
      int numCellsWithState = myGrid.getCountAllCellsWithSameStateTypeAsTarget(state);
      myGraph.updateStateSeries(state,stepNumber,numCellsWithState);
    }
  }

}
