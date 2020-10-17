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
 * https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI
 */
public class SimulationGraph extends LineChart {

  public static final Axis TIME_AXIS = new NumberAxis();
  public static final Axis NUMBER_CELLS_AXIS = new NumberAxis();
  private Map<StateType,Series> myStateSeriesMap;

  public SimulationGraph(StateType[] states){
    super(TIME_AXIS,NUMBER_CELLS_AXIS);
    this.setTitle("Graph of State Population Over Time");

    createSeriesForStates(states);

    this.getData().addAll(myStateSeriesMap.values());
  }

  private void createSeriesForStates(StateType[] states){
    myStateSeriesMap = new HashMap<StateType,Series>();
    for(int stateNum=0; stateNum<states.length; stateNum++){
      Series stateSeries = new Series();
      stateSeries.setName(states[stateNum].toString());
      myStateSeriesMap.put(states[stateNum], stateSeries);
    }
  }

  public void updateStateSeries(StateType state, int timeValue, int numCells){
    myStateSeriesMap.get(state).getData().add(timeValue,numCells);
  }


}
