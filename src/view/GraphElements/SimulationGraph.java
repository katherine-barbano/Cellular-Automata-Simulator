package view.GraphElements;

import controller.StateType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * The Simulation graph displays the number of cells of each type over the course of the simulation.
 * In order to design this class, I referenced this source:
 * https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
 * @author Heather Grune (hlg20)
 */
public class SimulationGraph extends LineChart {

  public static final Axis TIME_AXIS = new NumberAxis();
  public static final Axis NUMBER_CELLS_AXIS = new NumberAxis();
  private Map<StateType,XYChart.Series> myStateSeriesMap;

  public SimulationGraph(StateType[] states){
    super(TIME_AXIS,NUMBER_CELLS_AXIS);
    this.setTitle("Graph of State Population Over Time");

    createSeriesForStates(states);

    this.getData().addAll(myStateSeriesMap.values());

    this.getStyleClass().add("simulation-graph");
    this.setId("simulationGraph");
  }

  private void createSeriesForStates(StateType[] states){
    myStateSeriesMap = new HashMap<StateType,XYChart.Series>();
    for(int stateNum=0; stateNum<states.length; stateNum++){
      XYChart.Series stateSeries = new XYChart.Series<>();
      stateSeries.setName(states[stateNum].toString());
      myStateSeriesMap.put(states[stateNum], stateSeries);
    }
  }

  /**
   * Update the series in the simulation graph to add an additional data point for the current
   * number cells in the grid.
   * @param state The state type of the cells
   * @param stepNumber The number of steps that the simulation has gone through
   * @param numCells The number of cells with the given stateType
   */
  public void updateStateSeries(StateType state, double stepNumber, int numCells){
    myStateSeriesMap.get(state).getData().add(new XYChart.Data(stepNumber,numCells));
  }

  /**
   * Accessor for the Map containing the series for each state.  This is only used in testing.
   * @return Map of the series for each state.
   */
  public Map<StateType,XYChart.Series> getMyStateSeriesMap(){
    return myStateSeriesMap;
  }


}
