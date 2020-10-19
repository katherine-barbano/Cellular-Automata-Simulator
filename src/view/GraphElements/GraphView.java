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


public class GraphView {

  public static final String RESOURCES = "resources/";
  public static final String STYLESHEET = "lightMode.css";


  private Grid myGrid;
  private VBox myRoot;
  private ResourceBundle myBundle;

  private FlowPane myTitleBar;
  private SimulationGraph myGraph;
  private StateType[] myStates;

  /**
   *
   */
  public GraphView(Grid grid, String language){
    myBundle = ResourceBundle.getBundle(SimulationView.RESOURCES+SimulationView.RESOURCE_BUNDLE+language);
    myGrid = grid;
  }

  /**
   *
   */
  public Scene setupScene(String simulationType,  StateType[] states, int width, int height) {
    this.myStates = states;

    createUIElements(simulationType);
    addUIElementsToRoot();
    updateGraph(0);

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

  public void updateCurrentGrid(Grid nextGrid,double elapsedTime){
    myGrid = nextGrid;
    updateGraph(elapsedTime);
  }

  public void updateGraph(double elapsedTime){
    for(StateType state: myStates){
      int numCellsWithState = myGrid.getAllCellsWithSameStateTypeAsTarget(state);
      myGraph.updateStateSeries(state,elapsedTime,numCellsWithState);
    }
  }

}
