package controller;

import controller.stateType.GameOfLifeState;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import model.Grid;

public class GameOfLifeSimulation extends Simulation {

  private static final String STORING_FILE_NAME = "data/outputGrids/";
  private int numberRows;
  private int numberCols;
  private HashMap<Integer,StateType> statesForInteger;
  private HashMap<StateType, Integer> integerForStates;
  ;

  public GameOfLifeSimulation() {
    super("GameOfLife");
  }

/*  @Override
  public StateType[][] createStatesFromInteger(int[][] integerCellStates) {
*//*    statesForInteger = new HashMap<>();
    integerForStates = new HashMap<>();
    StateType possibleStatesInGameOfLife[] = GameOfLifeState.values();
    int stateNumber = 0;
    for(StateType state : possibleStatesInGameOfLife) {
      integerForStates.put(state, stateNumber);
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
    StateType[][] cellStates = new StateType[integerCellStates.length][integerCellStates[0].length];
    numberRows = integerCellStates.length;
    numberCols = integerCellStates[0].length;
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = statesForInteger.get(integerCellStates[row][col]);
      }
    }
    return cellStates;*//*
    return null;
  }*/

/*  @Override
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
  }*/

  @Override
  public StateType[] getStateTypesForSimulation() {
    return GameOfLifeState.values();
  }

  @Override
  public void storeNewCellConfig(Grid gridToStore) {
/*      try {
        String input = JOptionPane.showInputDialog("Enter new File name (with csv)");
        File file = new File(input);
        FileWriter csvWriter = new FileWriter(STORING_FILE_NAME+ file.getName());
        //FileWriter csvWriter = new FileWriter(file.getName());
        csvWriter.append(Integer.toString(numberRows));
        csvWriter.append(",");
        csvWriter.append(Integer.toString(numberCols));
        csvWriter.append(",");
        csvWriter.append("\n");

        for(int row=0; row<gridToStore.getGridNumberOfRows(); row++){
          for(int col=0; col<gridToStore.getGridNumberOfColumns();col++) {
            State x = gridToStore.getCell(row,col).getCurrentState();
            Integer t = integerForStates.get(gridToStore.getCell(row,col).getCurrentState());
            String s = t.toString();
            csvWriter.append(integerForStates.get(gridToStore.getCell(row,col).getCurrentState()).toString());
            //csvWriter.append(gridToStore.getCell(row,col).getCurrentState().toString());
            csvWriter.append(",");
          }
          csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        String resourceName = "simulationProperties/GameOfLife.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
          props.load(resourceStream);
        }
        Object s = "fileName";
        props.replace(s, props.get(s), input);
        FileOutputStream out = new FileOutputStream(
            String.valueOf(loader.getResourceAsStream(resourceName)));
        props.setProperty("fileName", file.getName());
        props.store(out, null);
        out.close();
        System.out.println("done");

      } catch (IOException e) {
        //System.out.println("not working");
        String invalidFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
            getString("InvalidFile");
        throw new ControllerException(invalidFileExceptionMessage);
      }*/
  }
}