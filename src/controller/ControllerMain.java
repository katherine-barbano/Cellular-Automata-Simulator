package controller;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import view.GraphElements.GraphView;
import view.LanguageScreen.LanguageScreen;
import view.SimulationView;

/**@author Priya Rathinavelu
 * This class is the main class within the controller that starts the simulation and general
 * display of the project. It handles starting the animation and scene as well as starts the
 * simulation and its associated grid. In addition, it handles setting up the actions of some of the
 * buttons that relate to user interface like being able to swtich between simulations and step and
 * pause/play. This class assumes that the default simulation to start off with is the game of life
 * simulation first. This class does not have any connection with the model package and strictly
 * works with the controller and the graph/simulation views created within the View package. This
 * class is never really instantiated, and instead is really just used to run the simulations
 * and display them. This class is responsible for connecting the view and the model (but does not
 * directly work with the model).
 */
public class ControllerMain extends Application {
  public double secondDelay = 1.0;
  public static final int FRAME_SIZE = 400;
  public final String SIMULATION_SETTINGS = "resources/SimulationSettings";
  public static final int SCREEN_WIDTH = 500;
  public static final int SCREEN_HEIGHT = 500;
  public static final int LANGUAGE_SCREEN_HEIGHT = 80;
  public static final String ENGLISH_LANGUAGE="English";
  public static final String SPANISH_LANGUAGE="Spanish";
  public static final String FRENCH_LANGUAGE="French";
  private static final String ERRORS_LOCATION = "resources/ControllerErrors";
  private static final String SIMULATION_PREFIX = "controller.";
  private static final String SIMULATION_SUFFIX = "Simulation";
  private static final String FILE_TITLE = "fileChooserTitle";
  private static final String FILE_DESCRIPTION = "fileChooserDescription";
  private static final String FILE_SUFFIX_CSV = "*.csv";
  private static final String FILE_SUFFIX_SIM = "*.sim";
  private Scene myScene;
  private Group root;
  private LanguageScreen myLanguageScreen;
  private String myLanguageChoice;
  private final String STARTING_SIMULATION_TYPE = "GameOfLife";
  private Simulation currentSimulation = new GameOfLifeSimulation(); //default starts with Game of Life
  private SimulationView currentSimView;
  private boolean isPaused;
  private Stage currentStage;
  private Stage secondStage= new Stage();
  private GraphView myGraphView;
  private Scene myGraphScene;
  private boolean viewGraph = false;
  private int stepCount=0;
  private ResourceBundle myBundle = ResourceBundle.getBundle(SIMULATION_SETTINGS);
  private double minSpeed;
  private double maxSpeed;
  private double speedShiftAmount;

  @Override
  public void start(Stage stage) {
    currentStage = stage;
    chooseLanguageAndSetupStage();
    startAnimation(secondDelay);
    minSpeed = Double.parseDouble(myBundle.getString("minSpeed"));
    maxSpeed = Double.parseDouble(myBundle.getString("maxSpeed"));
    speedShiftAmount = Double.parseDouble(myBundle.getString("speedShiftAmount"));
  }

  /*
  Method for starting the animation using timeline and indicates that for a certain speed, the
  step function should be called
   */
  private void startAnimation(double speedAmount) {
    KeyFrame frame = new KeyFrame(Duration.seconds(speedAmount), e -> step());
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }


  /*
  This method is responsible for handling the first splash screen shown that allows the user
  to indicate with language they would like the game to played and depending on which language is
  selected, the screen will be set up accordingly
   */
  private void chooseLanguageAndSetupStage(){
    myLanguageScreen = new LanguageScreen();
    currentStage.setScene(myLanguageScreen.setupScene(SCREEN_WIDTH,LANGUAGE_SCREEN_HEIGHT));
    currentStage.show();

    myLanguageScreen.getMyEnglishButton().setOnAction(event -> setupSimulationScenes(currentStage,ENGLISH_LANGUAGE));
    myLanguageScreen.getMySpanishButton().setOnAction(event -> setupSimulationScenes(currentStage,SPANISH_LANGUAGE));
    myLanguageScreen.getMyFrenchButton().setOnAction(event -> setupSimulationScenes(currentStage,FRENCH_LANGUAGE));
  }

  /*
  This method is responsible for setting up the scene based on the language selected - it uses
   the initial set up assuming that game of life simulation is the first simulation to be shown
   */
  private void setupSimulationScenes(Stage stage, String language){
    this.myLanguageChoice=language;
    setupScene(FRAME_SIZE, FRAME_SIZE, currentSimulation, STARTING_SIMULATION_TYPE);
    setUpStage(stage);
  }

  /*
   * Sets up the stage size and title and can add the graph view to the scene
   */
  protected void setUpStage(Stage stage) {
      try {
        stage.setScene(myScene);
        stage.show();
        isPaused = true;

        if(viewGraph && myGraphScene !=null){
          secondStage.setScene(myGraphScene);
          secondStage.show();
        }

      } catch (ControllerException e) {
        displayError(e.getMessage());
      }
  }

  /*
   * Create the game's "scene": what shapes will be in the game and their starting properties
   */
  public Scene setupScene(int width, int height, Simulation currSim, String newSimType) {
    root = new Group();
      currentSimulation = currSim;
      currentSimView = new SimulationView(currentSimulation.getCurrentGrid(),myLanguageChoice);
      myScene = currentSimView.setupScene(newSimType, currentSimulation.getPossibleStateTypes(),
          SCREEN_WIDTH, SCREEN_HEIGHT);
      setUpButtons();
    return myScene;
  }

  /*
  This method sets up the graph view by taking in the simulation type and can display a new
  graph view and new graph scene
   */
  private void setupGraph(String simType){
    myGraphView = new GraphView(currentSimulation.getCurrentGrid(), myLanguageChoice);
    myGraphScene = myGraphView.setupScene(simType, stepCount, currentSimulation.getPossibleStateTypes(),FRAME_SIZE,FRAME_SIZE);
  }

  /*
  This method is responsible for taking some of the buttons created in the view package and
  associating them with the actions that need to happen if the button is actually selected. The actions
  are the ones that are unique to the controller main class
   */
  public void setUpButtons() {
    currentSimView.getMyControlButtons().getMyStep().setOnAction(event -> stepByButton());
    currentSimView.getMyControlButtons().getMyPlayPause().setOnAction(event -> unpauseOrPause());
    currentSimView.getMyFileButtons().getMySave().setOnAction(event -> saveFile());
    currentSimView.getMyFileButtons().getMyNewFile().setOnAction(event ->
        selectNewFile());
    currentSimView.getMyControlButtons().getSpeedUpButton().setOnAction(event -> increaseSpeed());
    currentSimView.getMyControlButtons().getSlowDownButton()
        .setOnAction(event -> decreaseSpeed());
    try {
      currentSimView.getMySimulationButtons().getMySimulationButton()
          .setOnAction(event -> checkChangeSimulation());
    } catch (ControllerException e) {
      displayError(e.getMessage());
    }
    currentSimView.getMyOpenGraphViewBar().getMyStartGraphViewButton().setOnAction(event -> showGraphOnClick());
    secondStage.setOnHidden(event ->{   viewGraph = false; });
    }

    /*
    This method is what updates the grid by calling the update shapes method
     */
  void step () {
    if(currentSimulation!=null && currentSimView != null){
      updateShapes(!isPaused);
    }

  }

  /*
  This method updates the simulation by updating the simulation grid and updates the graph view
   */
  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun, currentSimView);
    if(shouldRun){
      stepCount++;
      if(myGraphView != null){
        myGraphView.updateCurrentGrid(currentSimulation.getCurrentGrid(),stepCount);
      }
    }

  }

  /*
  This method saves the file of the current simulation that contains the current grid
  configuration
   */
  void saveFile() {
    try {
      isPaused = true;
      currentSimulation.saveNewCellConfiguration(currentSimulation.getCurrentGrid());
    } catch (ControllerException e) {
      displayError(e.getMessage());
    }
  }

  /*
  This method displays the graph but setting up the scene for a second stage
   */
  void showGraphOnClick(){
    viewGraph=true;
    setupGraph(currentSimulation.getSimulatonName());
    secondStage.setScene(myGraphScene);
    secondStage.show();
  }

  /*
  This method checks if the simulation needs to be changed based on the values chosen from the drop
  down menu on the display, so based on which simulation is chosen and set, that simulation will
  then be displayed
   */
  private void checkChangeSimulation() {
    String simulationChosen = currentSimView.getMySimulationButtons().getSimulationChooser()
        .getMyChosenType();
    if (simulationChosen != null) {
      try {
        stepCount=0;
        String fullClassName = String.format(SIMULATION_PREFIX + simulationChosen + SIMULATION_SUFFIX);
        Class<?> cl = Class.forName(fullClassName);
        Constructor<?> cons = cl.getConstructor();
        cons.newInstance();
        Simulation test = (Simulation) cons.newInstance();
        currentSimulation = test;
        setupScene(FRAME_SIZE, FRAME_SIZE, currentSimulation, simulationChosen);
        if(viewGraph){
          setupGraph(simulationChosen);
        }
        setUpStage(currentStage);
          } catch (Exception e) {
        String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
            getString("InvalidSimulationName");
        throw new ControllerException(invalidFileExceptionMessage);
      }
    }
  }

  /*
  This method increases the speed until it reaches a maximum speed
   */
  private void increaseSpeed() {
    if (secondDelay-speedShiftAmount > minSpeed) {
      secondDelay -= speedShiftAmount;
      setUpStage(currentStage);
      isPaused = false;
    }
  }

  /*
  This method decreases the speed until it reaches a minimum speed
   */
  private void decreaseSpeed() {
    if (secondDelay + speedShiftAmount < maxSpeed) {
      secondDelay += speedShiftAmount;
      setUpStage(currentStage);
      isPaused = false;
    }
  }

  /*
  This method is related to the step button and updates the cells as needed
   */
  void stepByButton() {
    updateShapes(isPaused);
  }

  /*
  This method pauses/unpauses the simulation
   */
  void unpauseOrPause() {
    isPaused = !isPaused;
  }

  /*
  This method allows the user to select a new file to be displayed by the view so whatever simulation
  is currently chosen will start displaying a new configuration read in from the selected csv file
   */
  void selectNewFile() throws ControllerException {
    try {
      isPaused = true;
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle(myBundle.getString(FILE_TITLE));
      fileChooser.getExtensionFilters().addAll(new ExtensionFilter(myBundle.getString(FILE_DESCRIPTION),
          FILE_SUFFIX_CSV, FILE_SUFFIX_SIM));
      File selectedFile = fileChooser.showOpenDialog(currentStage);
      if (selectedFile != null) {
        currentSimulation.setSimulationFileLocation(selectedFile.getName());
        currentSimView.updateGridDisplay(currentSimulation.getCurrentGrid());
      }

    } catch(Exception e) {
      String noFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("NoFileSelectedError");
      displayError(noFileExceptionMessage);
      }
  }

  /*
  This method displays a string message error to the user and is used to display any exceptions
   */
  public void displayError(String message){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /*
  getter method to see the value of the isPaused variable
   */
  public boolean getIsPaused() {
    return isPaused;
  }

  /*
  Method for getting the current simulation
   */
  public Simulation getCurrentSimulation() {
    return currentSimulation;
  }
  /*
  Method for getting the current simulationview
   */
  public SimulationView getSimulationView() {
    return currentSimView;
  }

  public static void main (String[] args) {
    launch(args);
  }

}
