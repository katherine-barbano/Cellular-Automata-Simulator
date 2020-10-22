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

/**
 * @author Priya Rathinavelu
 * The purpose of this class is to serve as the abstract class that defines the various simulations
 * that can be displayed. When initializing a new simulation, that class will need to extend this
 * abstract class so that it can inherit all of its methods needed for defining and organizing the
 * parts of the simulation. The main assumptions for this class are the locations of the different
 *  files associated with the simulation. For example, there needs to be a properties file correctly
 * associated with the simulation placed in the right location (under simulationProperties folder).
 * In addition, because the simulation names for the view are made from filing through that simulation
 *  properties folder, it is essential that any properties files associated with a simulation must have
 *  the correct name matching the simulation class's name as well. This class has instance of other
 *  classes found within
 *  the model package, specifically the Grid class. This is because the simulation class is
 *  responsible for creating the grid from the configuration file. This is because the simulation
 *  reads from its specific properties file, and based on the value of the stateConfiguration key,
 *  the configuration of states will be created accordingly. An example of how to use this class
 *  is when you want to create a new simulation. It will extend this abstract class, and will need
 *  to override the abstract method getStateTypesForSimulation to return the appropriate state types
 *  associated with the unique situation. This also means that in order for this class to work,
 *  there needs to be an enum associated with the simulation that implements the stateType interface.
 *
 */

public abstract class Simulation {

  private State[][] gridStateFormation;
  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private String simulationFileLocation;
  private final static String ERRORS_LOCATION = "resources/ControllerErrors";
  private final static String SETTINGS_LOCATION = "resources/SimulationSettings";
  private final static String NEW_CONFIG_LOCATION = "data/initialConfigurations/";
  private final static String RANDOM_SIZE = "randomConfigurationSize";
  private final static String CSV_SUFFIX = ".csv";
  private final static String PROPERTIES_SUFFIX = ".properties";
  private final static String NEIGHBOR = "neighborPolicy";
  private final static String EDGE = "edgePolicy";
  private final static String STATE_CONFIG = "stateConfiguration";
  private final static String PROBABILITY = "probability";
  private final static String CONFIG_PROBABILITY = "configProbability";
  private final static String PROPERTIES_PREFIX = "simulationProperties/";
  private final static String FILE_NAME = "fileName";
  private final static String FILE = "file";
  private final static String AUTHOR = "author";
  private final static String DESCRIPTION = "description";
  private final static String TITLE = "title";
  private final static String DIALOG_PREFIX = "dialogPrefix";
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

  /*
  Constructor for setting up the simulation with its necessary properties and grid
   */
  public Simulation(String newSimulationName){
      this.simulationName = newSimulationName;
      this.randomConfigRowColNumber = Integer.parseInt(myBundle.getString(RANDOM_SIZE));
      initializeSimulation(PROPERTIES_PREFIX, newSimulationName);
  }

  /*
  This method initializes the simulation by reading in the appropriate properties file to
  determine which configuration of states should be used as well as uses the states associated with
  each simulation to create that configuration
   */
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

  /*
  This method determines which grid should be created to sent to the model. It can either create
  a grid with probability (if defined within the properties file) or the grid that doesn't use
  probability (if not define within the properties file)
   */
  private Grid createCorrectGrid() {
    if (propertiesInformation.containsKey(PROBABILITY)) {
      return new Grid(simulationName, propertiesInformation.get(EDGE), propertiesInformation.get(NEIGHBOR),
          this.gridStateFormation, Double.parseDouble(propertiesInformation.get(PROBABILITY)));
    }
    return new Grid(simulationName, propertiesInformation.get(EDGE),
        propertiesInformation.get(NEIGHBOR),this.gridStateFormation);
  }


  /*
  This method reads in the properties file and stores all of the information into a properties
  map to keep track of the appropriate values for the simulation
   */
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

  /*
  This method is able to set the new file that will be used to create a new configuration of states
  for the simulation.
   */
  public void setSimulationFileLocation(String newFileLocation) {
    simulationFileLocation = STORING_FILE_NAME + newFileLocation;
    currentGrid = new Grid(simulationName, propertiesInformation.get(EDGE),
        propertiesInformation.get(NEIGHBOR), createStates(readCellStatesFile(), getStateTypesForSimulation()));
    nextGrid = currentGrid.getNextGrid();
  }

  /*
  This method sets a new properties file instead of using the main properties file associated
  with the simulation (the properties file with the same name of the simulation and is set as the
  default)
   */
  public void setNewPropertiesFile(String locationFolder, String newPropertiesFile) {
    initializeSimulation(locationFolder, newPropertiesFile);
  }

  /*
  This method saves the current cell configuration by creating a new csv file as well as a new
  properties file that can still maintain some of the original keys within the default original
  properties file for the simulation. It takes in a grid so that it can create the correct new
  grid csv file.
   */
  public void saveNewCellConfiguration(Grid gridToStore) {
    try {
      Properties properties = new Properties();
      TextInputDialog dialog = new TextInputDialog();
      dialog.setTitle(myBundle.getString(TITLE));
      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + FILE_NAME);
      dialog.showAndWait();
      String newFileName = dialog.getResult();
      properties.setProperty(FILE_NAME, newFileName);

      File newCSVFile = new File(newFileName + CSV_SUFFIX);

      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + TITLE);
      dialog.showAndWait();
      properties.setProperty(TITLE, dialog.getResult());
      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + AUTHOR);
      dialog.showAndWait();
      properties.setProperty(AUTHOR, dialog.getResult());

      dialog.setContentText(myBundle.getString(DIALOG_PREFIX) + DESCRIPTION);
      dialog.showAndWait();
      properties.setProperty(DESCRIPTION, dialog.getResult());

      properties.setProperty(STATE_CONFIG, FILE);
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

  /*
  This helper method is what actually writes the csv file of the states by opening a csv writer
   */
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

  /*
  This method determines which intial grid configuration should be used for the simulation. It is
  determined by which value is found for the key "stateConfiguration" within the properties file
  for the simulation. If it is file, then the method will try to find the key "filename" to get the
  right file to read in the state configuration. If it is set to random, then a random configuration
  of the states will be created. If the configuration type is set to probability, then a probability
  based configuration will be created.
   */
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

  /*
  This abstract method is used so that the simulation can determine which enum of statetypes is
  associated with each implementation of simulation.
   */
  abstract public StateType[] getStateTypesForSimulation();

  /*
  getter method for state types array
   */
  public StateType[] getPossibleStateTypes() {
    return possibleStateTypes;
  }

  /*
  This method creates a 2d array of States (which is needed for the grid), by taking in an integer
  2d array (either created from reading in the csv file or based on randomness or probability) and
  also takes in the state types available for the simulation to create a 2d array of states because
  each state type is mapped to an integer.
   */
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

  /*
  This method creates a map of the state types where each state type is mapped to an integer, which
  is how the csv files are created (with the intention of each integer representing a state)
   */
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

  /*
  getter method for the map storing all of the information found in the properties file
   */
  public HashMap<String, String> getPropertiesInformation() {
    return propertiesInformation;
  }

  /*
  this method is what reads in the main simulation file listed in the properties file for each
  location and uses it to create the 2d array of integers based on directly what integers are within
  the csv file
   */
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

  /*
  the method reads the input stream of data from the file and can use a csv reader to read in the
  information from the csv file and store it
   */
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

  /*
  This method is what creates a random configuration of states if the state configuration key within
  the properties file is listed as random. It randomly selects a state for the simulation, and makes
  it a size that is read in from a properties file
   */
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

  /*
  this method creates an intial configuration of states based on probability if the state
  configuration key indicates that the initial set up should be based on probability. It needs a
  probability to be read in as well. For this method, the probability indicates which percentage of
  cells should be the first state in the state types for the simulation.
   */
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


  /*
  This method is what updates the simulation grid to reflect the new grid for the next stage by taking
  the grid and getting the next grid for the simulation
   */
  public void updateSimulationGrid(boolean shouldRun, SimulationView simulationView) {
    if (shouldRun) {
      checkGridUpdatesInDisplay(simulationView);
      updateSimulation();
      simulationView.updateGridDisplay(currentGrid);
    }
  }

  /*
  This method uses the simulation view to get the current based on what the grid is in the simulation
  This is used so that when the user updates the grid by clicking on cells, the simulation can
  still account for it by updating what the current grid is wihtin simulation
   */
  public void checkGridUpdatesInDisplay(SimulationView currentSimView){
    this.currentGrid=currentSimView.getCurrentGridInDisplay();
    this.nextGrid = currentGrid.getNextGrid();
  }

  /*
  Method that switches the current grid to its next grid and sets the next grid to its next stage
   */
  public void updateSimulation() {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getNextGrid();
  }

  /*
  method used for testing
   */
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
