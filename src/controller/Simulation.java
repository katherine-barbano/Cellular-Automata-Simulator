package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import javafx.scene.Group;
import javafx.scene.control.TextInputDialog;
import javax.swing.JOptionPane;
import model.*; //CHECK may need to change so not all classes from model package
import view.SimulationView;

public abstract class Simulation {

  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private String simulationFileLocation;
  //private SimulationView simulationView;
  private Group root;
  private final String ERRORS_LOCATION = "resources/ControllerErrors";
  private final String CSV_SUFFIX = ".csv";
  private final String SIM_SUFFIX = ".sim";
  private final String PROPERTIES_SUFFIX = ".properties";



  private int rowNumber;
  private int colNumber;
  private HashMap<Integer, StateType> statesForInteger;
  private HashMap<StateType, Integer> integerForStates;
  private StateType[] possibleStateTypes;
  private HashMap<String, String> propertiesInformation;
  private final String STORING_FILE_NAME = "data/outputGrids/";
  private final String PROPERTIES_LOCATION = "simulationProperties/";



  public Simulation(String newSimulationName){
      this.simulationName = newSimulationName;
      this.propertiesInformation = new HashMap<String, String>();
      readPropertiesFile(newSimulationName);
      simulationFileLocation =
          "data/initialConfigurations/" + propertiesInformation.get("fileName");
      //simulationFileLocation = "data/initialConfigurations/testingGOL.csv";
      this.possibleStateTypes = getStateTypesForSimulation();
      currentGrid = new Grid(simulationName, propertiesInformation.get("edgePolicy"),
          propertiesInformation.get("neighborPolicy"),
          createStates(readCellStatesFile(), possibleStateTypes));
      nextGrid = currentGrid.getNextGrid();
   // simulationView = new SimulationView(currentGrid);
  }


  public void readPropertiesFile(String propertiesFileName) throws ControllerException {
      try{
        String resourceName = "simulationProperties/" + propertiesFileName + ".properties"; // could also be a constant*/
        //String resourceName = "simulationProperties/" + propertiesFileName + ".properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
          props.load(resourceStream);
        }
        for (Object s : props.keySet()) {
          propertiesInformation.put(s.toString(), props.get(s).toString());
        }
      }
      catch (Exception e) {
        String improperPropertiesFileMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
            getString("InvalidFile");
        throw new ControllerException(improperPropertiesFileMessage);
      }
  }

//CHECK can remove this method if initializing in the constructor itself
  public void setSimulationFileLocation(String newFileLocation) {
    simulationFileLocation = "data/initialConfigurations/" + newFileLocation;
    currentGrid = new Grid(simulationName, propertiesInformation.get("edgePolicy"),
        propertiesInformation.get("neighborPolicy"), createStates(readCellStatesFile(), getStateTypesForSimulation()));
    nextGrid = currentGrid.getNextGrid();
    //simulationView = new SimulationView(currentGrid);
    System.out.println("new simulation set");
  }

  //abstract public void storeNewCellConfig(Grid gridToStore);

 // abstract public String readInPropertiesFile();

  public void saveNewCellConfiguration(Grid gridToStore) {
    try {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("Text Input Dialog");
      dialog.setHeaderText("Look, a Text Input Dialog");
      dialog.setContentText("Please enter new file name:");
      dialog.showAndWait();
      String res = dialog.getResult()+".csv";
      System.out.println(res);

      //Optional<String> result = dialog.showAndWait();
      File file = new File(res);
      //result.ifPresent(name -> System.out.println("Your name: " + name));

      /*
      String newFileName = JOptionPane.showInputDialog("Enter new file name");
      File file = new File(newFileName);
      String newTitle = JOptionPane.showInputDialog("Enter new title"); */
      FileWriter csvWriter = new FileWriter(STORING_FILE_NAME + file.getName());
    //FileWriter csvWriter = new FileWriter(file.getName());
      csvWriter.append(Integer.toString(rowNumber));
      csvWriter.append(",");
      csvWriter.append(Integer.toString(colNumber));
      csvWriter.append(",");
      csvWriter.append("\n");

      for(int row=0; row<gridToStore.getGridNumberOfRows(); row++){
        for(int col=0; col<gridToStore.getGridNumberOfColumns();col++) {
          csvWriter.append(integerForStates.get(gridToStore.getCell(row,col).getCurrentState().getStateType()).toString());
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
      File propFile = new File(resourceName);
    /*
      Object s = "fileName";
      System.out.println(props.get(s));
      Object q = props.get("fileName");
      props.setProperty("fileName", "work");
      System.out.println(props.get(s));
      System.out.println("saved");
    */
      OutputStream out = new FileOutputStream(
          String.valueOf(loader.getResourceAsStream(resourceName)));
      //OutputStream out = new FileOutputStream(propFile);
      props.setProperty("fileName", file.getName());
      //props.put("fileName", file.getName());
      props.store(out, null);
      //props.save(out,null);
      out.close();
      System.out.println("done");
    } catch (IOException e) {
      //System.out.println("not working");
      String invalidFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("InvalidFile");
      throw new ControllerException(invalidFileExceptionMessage);
    }
  }


  abstract public StateType[] getStateTypesForSimulation();

  public StateType[] getPossibleStateTypes() {
    return possibleStateTypes;
  }

  //CHECK - remove this method!
 // abstract public StateType[][] createStatesFromInteger(int[][] integerCellStates);

  public State[][] createStates(int[][] integerCellStates,
    StateType[] possibleStatesForSimulation) {
    statesForInteger = new HashMap<>();
    integerForStates = new HashMap<>();
    int stateNumber = 0;
    for(StateType state : possibleStatesForSimulation) {
      integerForStates.put(state, stateNumber);
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
    State[][] cellStates = new State[integerCellStates.length][integerCellStates[0].length];
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = new State(statesForInteger.get(integerCellStates[row][col]));
      }
    }
    return cellStates;
  }

  public int[][] readCellStatesFile() throws ControllerException {
    int[][] cellStates;
    List<String[]> readFiles = new ArrayList<String[]>();
    try {
      readFiles = readAll(new FileInputStream(simulationFileLocation));
    } catch (Exception e) {
      String invalidFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("InvalidFileName");
      throw new ControllerException(invalidFileExceptionMessage);
    }
    try {
      rowNumber = Integer.parseInt(readFiles.get(0)[0]);
      colNumber = Integer.parseInt(readFiles.get(0)[1]);
      cellStates = new int[rowNumber][colNumber];
      for (int curr = 1; curr < readFiles.size(); curr++) {
        for (int col = 0; col < colNumber; col++) {
          cellStates[curr - 1][col] = Integer.parseInt(readFiles.get(curr)[col]);
        }
      }
    } catch (Exception f){
      String incorrectConfigurationExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
          getString("InvalidConfigSize");
      throw new ControllerException(incorrectConfigurationExceptionMessage);
    }
    return cellStates;
  }

  public List<String[]> readAll (InputStream data) {
    try (CSVReader csvReader = new CSVReader(new InputStreamReader(data))) {
      return csvReader.readAll();
    }
    catch (IOException | CsvException e) {
      //e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public Grid getCurrentGrid() {
    return currentGrid;
  }

/*  public void updateSimulationGrid(boolean shouldRun) {
    if (shouldRun) {
      checkGridUpdatesInDisplay();
      //updateToNextSimulation();
      simulationView.updateGridDisplay(currentGrid);
    }
  }*/



  public void updateSimulationGrid(boolean shouldRun, SimulationView simulationView) {
    if (shouldRun) {
      checkGridUpdatesInDisplay(simulationView);
      this.currentGrid = nextGrid;
      this.nextGrid = currentGrid.getNextGrid();
      simulationView.updateGridDisplay(currentGrid);
    }
  }

  public void checkGridUpdatesInDisplay(SimulationView currentSimView){
    Grid newGrid = currentSimView.getCurrentGridInDisplay();
    this.currentGrid=newGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public void updateSimulation(boolean shouldRun) {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }


  public void updateToNextSimulation() {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }


  public List<Integer> getMatrixSize() {
    List<Integer> sizeValues = new ArrayList<>();
    sizeValues.add(rowNumber);
    sizeValues.add(colNumber);
    return sizeValues;
  }

/*  public SimulationView getSimulationView() {
    return simulationView;
  }*/


  public int getRowNumber() {
    return rowNumber;
  }

  public int getColNumber() {
    return colNumber;
  }


}
