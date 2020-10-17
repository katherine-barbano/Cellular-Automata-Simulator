package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import model.Grid;

public class WatorWorldSimulation extends Simulation{

  private static final String STORING_FILE_NAME = "data/outputGrids/watorWorld";
  private int numberRows;
  private int numberCols;
  private HashMap<Integer,State> statesForInteger;
  private HashMap<State, Integer> integerForStates;
  ;

  public WatorWorldSimulation() {
    super(SimulationType.WATOR_WORLD, "testingWatorWorld.csv");
  }

  @Override
  public State[][] createStatesFromInteger(int[][] integerCellStates) {
    /*statesForInteger = new HashMap<>();
    integerForStates = new HashMap<>();
    State possibleStatesInGameOfLife[] = GameOfLifeState.values();
    int stateNumber = 0;
    for(State state : possibleStatesInGameOfLife) {
      integerForStates.put(state, stateNumber);
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
    State[][] cellStates = new State[integerCellStates.length][integerCellStates[0].length];
    numberRows = integerCellStates.length;
    numberCols = integerCellStates[0].length;
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = statesForInteger.get(integerCellStates[row][col]);
      }
    }
    return cellStates;*/
    return null;
  }

  @Override
  public String readInPropertiesFile() {
    try {
      Properties myProperties = new Properties();
      InputStream is = GameOfLifeSimulation.class.getClass().getResourceAsStream("ControllerError.properties");
      myProperties.load(is);
      System.out.println("loaded now");
      return "";
    }
    catch (Exception e) {
      System.out.println("exception");
    }
    return null;
  }

  @Override
  public void storeNewCellConfig(Grid gridToStore) {
    try {
      FileWriter csvWriter = new FileWriter(STORING_FILE_NAME+"New.csv");
      csvWriter.append(Integer.toString(numberRows));
      csvWriter.append(",");
      csvWriter.append(Integer.toString(numberCols));
      csvWriter.append(",");
      csvWriter.append("\n");

      for(int row=0; row<gridToStore.getGridNumberOfRows(); row++){
        for(int col=0; col<gridToStore.getGridNumberOfColumns();col++) {
          csvWriter.append(integerForStates.get(gridToStore.getCell(row,col).getCurrentState()).toString());
          //csvWriter.append(gridToStore.getCell(row,col).getCurrentState().toString());
          csvWriter.append(",");
        }
        csvWriter.append("\n");
      }
      csvWriter.flush();
      csvWriter.close();
      System.out.println("saved");
    } catch (IOException e) {
      String invalidFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("InvalidFile");
      throw new ControllerException(invalidFileExceptionMessage);
    }
  }
}
