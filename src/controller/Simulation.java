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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.control.TextInputDialog;
import model.*; //CHECK may need to change so not all classes from model package
import view.SimulationView;

public abstract class Simulation {

  private State[][] gridStateFormation;
  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private String simulationFileLocation;
  private final String ERRORS_LOCATION = "resources/ControllerErrors";
  private final String SETTINGS_LOCATION = "resources/SimulationSettings";
  private final String CSV_SUFFIX = ".csv";
  private final String SIM_SUFFIX = ".sim";
  private final String PROPERTIES_SUFFIX = ".properties";
  private final String NEIGHBOR = "neighborPolicy";
  private final String EDGE = "edgePolicy";
  private final String STATE_CONFIG = "stateConfiguration";
  private final String PROBABILITY = "probability";
  private String configurationType;
  private final ResourceBundle myBundle = ResourceBundle.getBundle(SETTINGS_LOCATION);


  private final int randomConfigRowColNumber;
  private int rowNumber;
  private int colNumber;
  private HashMap<Integer, StateType> statesForInteger;
  private HashMap<StateType, Integer> integerForStates;
  private StateType[] possibleStateTypes;


  private HashMap<String, String> propertiesInformation;
  private final String STORING_FILE_NAME = "data/initialConfigurations/";
  private final String NEW_PROPERTIES_LOCATION = "data/newPropertyFiles/";


  public Simulation(String newSimulationName){
    this.simulationName = newSimulationName;
    this.randomConfigRowColNumber = Integer.parseInt(myBundle.getString("randomConfigurationSize"));
    this.propertiesInformation = new HashMap<>();
    readPropertiesFile(newSimulationName);
    this.configurationType = propertiesInformation.get(STATE_CONFIG);
    this.possibleStateTypes = getStateTypesForSimulation();
    this.gridStateFormation = createInitialGridConfiguration(propertiesInformation.get(STATE_CONFIG));
    currentGrid = createCorrectGrid();
    nextGrid = currentGrid.getNextGrid();
  }

  public Grid createCorrectGrid() {
    if (propertiesInformation.containsKey(PROBABILITY)) {
      return new Grid(simulationName, propertiesInformation.get(EDGE), propertiesInformation.get(NEIGHBOR),
          this.gridStateFormation, Double.parseDouble(propertiesInformation.get(PROBABILITY)));
    }
    return new Grid(simulationName, propertiesInformation.get(EDGE),
        propertiesInformation.get(NEIGHBOR),this.gridStateFormation);
  }


  public void readPropertiesFile(String propertiesFileName) throws ControllerException {
      try{
        String resourceName = "simulationProperties/" + propertiesFileName + PROPERTIES_SUFFIX; // could also be a constant*/
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

  public void setSimulationFileLocation(String newFileLocation) {
    simulationFileLocation = STORING_FILE_NAME + newFileLocation;
    currentGrid = new Grid(simulationName, propertiesInformation.get(EDGE),
        propertiesInformation.get(NEIGHBOR), createStates(readCellStatesFile(), getStateTypesForSimulation()));
    nextGrid = currentGrid.getNextGrid();
    System.out.println("new simulation set");
  }

  public void setNewPropertiesFile(String newPropertiesFile) {
    this.propertiesInformation = new HashMap<>();
    readPropertiesFile(newPropertiesFile);
    this.configurationType = propertiesInformation.get(STATE_CONFIG);
    this.possibleStateTypes = getStateTypesForSimulation();
    this.gridStateFormation = createInitialGridConfiguration(propertiesInformation.get(STATE_CONFIG));
    currentGrid = createCorrectGrid();
    nextGrid = currentGrid.getNextGrid();
  }

  public void saveNewCellConfiguration(Grid gridToStore) {
    try {
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle("To Save a New File");
      dialog.setContentText("Please enter new file name:");
      dialog.showAndWait();
      String newFileName = dialog.getResult();
      File newCSVFile = new File(newFileName + CSV_SUFFIX);

      dialog.setContentText("Please enter new title");
      dialog.showAndWait();
      String newTitle = dialog.getResult();

      dialog.setContentText("Please enter new author");
      dialog.showAndWait();
      String newAuthorName = dialog.getResult();
      dialog.setContentText("Please enter new description");
      dialog.showAndWait();
      String newDescription = dialog.getResult();

      Properties properties = new Properties();
      properties.setProperty("fileName", newFileName);
      properties.setProperty(STATE_CONFIG, "file");
      properties.setProperty("title", newTitle);
      properties.setProperty("author", newAuthorName);
      properties.setProperty("description", newDescription);
      properties.setProperty(EDGE, propertiesInformation.get(EDGE));
      properties.setProperty(NEIGHBOR, propertiesInformation.get(NEIGHBOR));

      File newPropertiesFiles = new File(NEW_PROPERTIES_LOCATION+newFileName+PROPERTIES_SUFFIX);
      FileOutputStream fileOut = new FileOutputStream(newPropertiesFiles);
      properties.store(fileOut, null);
      fileOut.close();

      createCSVGridFile(gridToStore, newCSVFile);

    } catch (Exception e) {
      String invalidFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("InvalidFile");
      throw new ControllerException(invalidFileExceptionMessage);
    }
  }

  private void createCSVGridFile(Grid gridToStore, File newCSVFile) throws IOException {
    FileWriter csvWriter = new FileWriter(STORING_FILE_NAME + newCSVFile.getName());
    csvWriter.append(Integer.toString(gridToStore.getGridNumberOfRows()));
    csvWriter.append(",");
    csvWriter.append(Integer.toString(gridToStore.getGridNumberOfColumns()));
    csvWriter.append(",");
    csvWriter.append("\n");
    createMapOfStates(possibleStateTypes);

    for(int row=0; row< gridToStore.getGridNumberOfRows(); row++){
      for(int col=0; col< gridToStore.getGridNumberOfColumns();col++) {
        csvWriter.append(integerForStates.get(gridToStore.getCell(row,col).getCurrentState().getStateType()).toString());
        if (col != gridToStore.getGridNumberOfColumns() - 1) {
          csvWriter.append(",");
        }
      }
      csvWriter.append("\n");
    }
    csvWriter.flush();
    csvWriter.close();
  }

  public State[][] createInitialGridConfiguration(String configType) {
    if (configType.equals("file")) {
      try {
        simulationFileLocation =
            "data/initialConfigurations/" + propertiesInformation.get("fileName");
        return createStates(readCellStatesFile(), possibleStateTypes);

      } catch (Exception e) {
        String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
            getString("InvalidFileName");
        throw new ControllerException(invalidFileExceptionMessage);
      }
    }
    if (configType.equals("probability")) {
      try {
        double probability = Double.parseDouble(propertiesInformation.get("configProbability"));
        return createProbabilityBasedStateConfiguration(probability);
      } catch (Exception e) {
        String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
            getString("InvalidFileConfiguration");
        throw new ControllerException(invalidFileExceptionMessage);
      }
    }
    //if not defined, or if type listed is not found :
    return createRandomLocationConfig();
  }

/*  private double[] extractProbabilities(String probabilities) {
    String[] probabilityForStates = probabilities.split(",");
    double[] probabilityValuesByState  = new double[probabilityForStates.length];
    int count = 0;
    for (String currentProbability: probabilityForStates) {
      probabilityValuesByState[count] = Double.parseDouble(currentProbability);
    }
    return probabilityValuesByState;
  }*/


  abstract public StateType[] getStateTypesForSimulation();

  public StateType[] getPossibleStateTypes() {
    return possibleStateTypes;
  }

  public State[][] createStates(int[][] integerCellStates,
    StateType[] possibleStatesForSimulation) {
    createMapOfStates(possibleStatesForSimulation);
    State[][] cellStates = new State[integerCellStates.length][integerCellStates[0].length];
    for (int row = 0; row < integerCellStates.length; row++) {
      for (int col = 0; col < integerCellStates[0].length; col++) {
        cellStates[row][col] = new State(statesForInteger.get(integerCellStates[row][col]));
      }
    }
    return cellStates;
  }

  private void createMapOfStates(StateType[] possibleStatesForSimulation) {
    statesForInteger = new HashMap<>();
    integerForStates = new HashMap<>();
    int stateNumber = 0;
    for(StateType state : possibleStatesForSimulation) {
      integerForStates.put(state, stateNumber);
      statesForInteger.put(stateNumber,state);
      stateNumber++;
    }
  }

  public HashMap<String, String> getPropertiesInformation() {
    return propertiesInformation;
  }

  public int[][] readCellStatesFile() throws ControllerException {
    int[][] cellStates;
    List<String[]> readFiles = new ArrayList<>();
    try {
      readFiles = readAll(new FileInputStream(simulationFileLocation));
    } catch (Exception e) {
      String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
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
      String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
          getString("InvalidFileName");
      throw new ControllerException(invalidFileExceptionMessage);
    }
  }

  public Grid getCurrentGrid() {
    return currentGrid;
  }

  public State[][] createRandomLocationConfig() {
    State[][] randomLocationCells = new State[randomConfigRowColNumber][randomConfigRowColNumber];
    StateType[] possibilities = getPossibleStateTypes();
    Random random = new Random();
    for (int row = 0; row < randomConfigRowColNumber; row ++) {
      for (int col = 0; col < randomConfigRowColNumber; col++) {
        int randomIndex = random.nextInt(possibleStateTypes.length);
        System.out.println(randomIndex);
        randomLocationCells[row][col] = new State(possibilities[randomIndex]);
      }
    }
    return randomLocationCells;
  }

  public State[][] createProbabilityBasedStateConfiguration(double probabilities) {
    State[][] probabilityConfiguration = new State[randomConfigRowColNumber][randomConfigRowColNumber];
    StateType[] possibleStates = getPossibleStateTypes();
    Random random = new Random();
    int numberOfCells = (int) (randomConfigRowColNumber * randomConfigRowColNumber * probabilities);
    for (int row = 0; row < probabilityConfiguration.length; row ++) {
      for (int col = 0; col < probabilityConfiguration.length; col++) {
        while (numberOfCells>0) {
          probabilityConfiguration[row][col] = new State(possibleStates[0]);
        }
        int randomIndex = random.nextInt(possibleStateTypes.length);
        probabilityConfiguration[row][col] = new State(possibleStates[randomIndex]);
      }
    }
    return probabilityConfiguration;
  }


  public void updateSimulationGrid(boolean shouldRun, SimulationView simulationView) {
    if (shouldRun) {
      checkGridUpdatesInDisplay(simulationView);
      updateSimulation();
      simulationView.updateGridDisplay(currentGrid);
    }
  }

  public void checkGridUpdatesInDisplay(SimulationView currentSimView){
    Grid newGrid = currentSimView.getCurrentGridInDisplay();
    this.currentGrid=newGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public void updateSimulation() {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  public List<Integer> getMatrixSize() {
    List<Integer> sizeValues = new ArrayList<>();
    sizeValues.add(rowNumber);
    sizeValues.add(colNumber);
    return sizeValues;
  }

  public String getSimulatonName(){
    return simulationName;
  }


}
