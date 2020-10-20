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
import model.*;
import view.SimulationView;

public abstract class Simulation {

  private State[][] gridStateFormation;
  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private String simulationFileLocation;
  private final String ERRORS_LOCATION = "resources/ControllerErrors";
  private final String SETTINGS_LOCATION = "resources/SimulationSettings";
  private final String NEW_CONFIG_LOCATION = "data/initialConfigurations/";
  private final String RANDOM_SIZE = "randomConfigurationSize";
  private final String CSV_SUFFIX = ".csv";
  private final String PROPERTIES_SUFFIX = ".properties";
  private final String NEIGHBOR = "neighborPolicy";
  private final String EDGE = "edgePolicy";
  private final String STATE_CONFIG = "stateConfiguration";
  private final String PROBABILITY = "probability";
  private final String CONFIG_PROBABILITY = "configProbability";
  private final String PROPERTIES_PREFIX = "simulationProperties/";
  private final String FILE_NAME = "fileName";
  private final String FILE = "file";
  private final String AUTHOR = "author";
  private final String DESCRIPTION = "description";
  private final String TITLE = "title";
  private final String DIALOG_PREFIX = "dialogPrefix";
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
      this.randomConfigRowColNumber = Integer.parseInt(myBundle.getString(RANDOM_SIZE));
      initializeSimulation(PROPERTIES_PREFIX, newSimulationName);
  }

  private void initializeSimulation(String locationFolder, String newPropertiesFile) {
    this.propertiesInformation = new HashMap<>();
    readPropertiesFile(locationFolder, newPropertiesFile);
    this.configurationType = propertiesInformation.get(STATE_CONFIG);
    this.possibleStateTypes = getStateTypesForSimulation();
    try {
      this.gridStateFormation = createInitialGridConfiguration(
          propertiesInformation.get(STATE_CONFIG));
    } catch (Exception e) {
      String improperPropertiesFileMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
          getString("InvalidFile");
      throw new ControllerException(improperPropertiesFileMessage);
    } currentGrid = createCorrectGrid();
    nextGrid = currentGrid.getNextGrid();
  }

  private Grid createCorrectGrid() {
    if (propertiesInformation.containsKey(PROBABILITY)) {
      return new Grid(simulationName, propertiesInformation.get(EDGE), propertiesInformation.get(NEIGHBOR),
          this.gridStateFormation, Double.parseDouble(propertiesInformation.get(PROBABILITY)));
    }
    return new Grid(simulationName, propertiesInformation.get(EDGE),
        propertiesInformation.get(NEIGHBOR),this.gridStateFormation);
  }


  private void readPropertiesFile(String locationPrefix, String propertiesFileName) throws ControllerException {
      try{
        String resourceName = locationPrefix + propertiesFileName + PROPERTIES_SUFFIX;
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
  }

  public void setNewPropertiesFile(String locationFolder, String newPropertiesFile) {
    initializeSimulation(locationFolder, newPropertiesFile);
  }

  public void saveNewCellConfiguration(Grid gridToStore) {
    try {
      Properties properties = new Properties();
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle(myBundle.getString(TITLE));
      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + FILE_NAME);
      dialog.showAndWait();
      String newFileName = dialog.getResult();

      File newCSVFile = new File(newFileName + CSV_SUFFIX);

      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + TITLE);
      dialog.showAndWait();
      String newTitle = dialog.getResult();
      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + AUTHOR);
      dialog.showAndWait();
      String newAuthorName = dialog.getResult();
      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + DESCRIPTION);
      dialog.showAndWait();
      String newDescription = dialog.getResult();


      properties.setProperty(FILE_NAME, newFileName);
      properties.setProperty(STATE_CONFIG, FILE);
      properties.setProperty(TITLE, newTitle);
      properties.setProperty(AUTHOR, newAuthorName);
      properties.setProperty(DESCRIPTION, newDescription);
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

  private State[][] createInitialGridConfiguration(String configType) {
      if (configType.equals(FILE)) {
        try {
          simulationFileLocation =
              NEW_CONFIG_LOCATION + propertiesInformation.get(FILE_NAME);
          return createStates(readCellStatesFile(), possibleStateTypes);

        } catch (Exception e) {
          String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
              getString("InvalidFileName");
          throw new ControllerException(invalidFileExceptionMessage);
        }
      }
      if (configType.equals(PROBABILITY)) {
        try {
          double probability = Double.parseDouble(propertiesInformation.get(CONFIG_PROBABILITY));
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

  abstract public StateType[] getStateTypesForSimulation();

  public StateType[] getPossibleStateTypes() {
    return possibleStateTypes;
  }

  private State[][] createStates(int[][] integerCellStates,
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

  private int[][] readCellStatesFile() throws ControllerException {
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

  private List<String[]> readAll (InputStream data) {
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

  private State[][] createRandomLocationConfig() {
    State[][] randomLocationCells = new State[randomConfigRowColNumber][randomConfigRowColNumber];
    StateType[] possibilities = getPossibleStateTypes();
    Random random = new Random();
    for (int row = 0; row < randomConfigRowColNumber; row ++) {
      for (int col = 0; col < randomConfigRowColNumber; col++) {
        int randomIndex = random.nextInt(possibleStateTypes.length);
        randomLocationCells[row][col] = new State(possibilities[randomIndex]);
      }
    }
    return randomLocationCells;
  }

  private State[][] createProbabilityBasedStateConfiguration(double probabilities) {
    State[][] probabilityConfiguration = new State[randomConfigRowColNumber][randomConfigRowColNumber];
    StateType[] possibleStates = getPossibleStateTypes();
    Random random = new Random();
    int numberOfCells = (int) (randomConfigRowColNumber * randomConfigRowColNumber * probabilities);
    for (int row = 0; row < probabilityConfiguration.length; row ++) {
      for (int col = 0; col < probabilityConfiguration.length; col++) {
        while (numberOfCells>0) {
          probabilityConfiguration[row][col] = new State(possibleStates[0]);
          numberOfCells -= 1;
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
    this.currentGrid=currentSimView.getCurrentGridInDisplay();
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
