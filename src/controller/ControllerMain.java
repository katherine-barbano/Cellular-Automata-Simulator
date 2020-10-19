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

public class ControllerMain extends Application {

  //public static final int FRAMES_PER_SECOND = 60;
  //public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static double secondDelay = 1.0;
  public final double SPEED_CHANGE_AMOUNT = .50;
  public static final int FRAME_SIZE = 400;
  public final String SPEED_VALUES = "resources/SimulationSpeeds";
 // public static final Paint BACKGROUND = Color.AZURE;
  public static final int SCREEN_WIDTH = 500;
  public static final int SCREEN_HEIGHT = 500;
  public static final int LANGUAGE_SCREEN_HEIGHT = 80;
  public static final String ENGLISH_LANGUAGE="English";
  public static final String SPANISH_LANGUAGE="Spanish";
  public static final String FRENCH_LANGUAGE="French";
  private final String ERRORS_LOCATION = "resources/ControllerErrors";
  private Scene myScene;
  private Group root;
  private LanguageScreen myLanguageScreen;
  private String myLanguageChoice;
  private Simulation currentSimulation = new GameOfLifeSimulation();
  //private Simulation currentSimulation;
  //private SimulationView currentSimView = new SimulationView();
  private SimulationView currentSimView;
  private boolean isPaused;
  private Stage currentStage;
  private Stage secondStage= new Stage();
  private GraphView myGraphView;
  private Scene myGraphScene;
  private int stepCount;
  private ResourceBundle myBundle = ResourceBundle.getBundle(SPEED_VALUES);
  private double minSpeed;
  private double maxSpeed;
  private double speedShiftAmount;

  @Override
  public void start(Stage stage) {
    currentStage = stage;
    chooseLanguageAndSetupStage();
    //currentSimulation = new GameOfLifeSimulation();
    startAnimation(secondDelay);
    minSpeed = Double.parseDouble(myBundle.getString("minSpeed"));
    maxSpeed = Double.parseDouble(myBundle.getString("maxSpeed"));
    speedShiftAmount = Double.parseDouble(myBundle.getString("speedShiftAmount"));
  }

  private void startAnimation(double speedAmount) {
    KeyFrame frame = new KeyFrame(Duration.seconds(speedAmount), e -> step());
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }


  private void chooseLanguageAndSetupStage(){
    myLanguageScreen = new LanguageScreen();
    currentStage.setScene(myLanguageScreen.setupScene(SCREEN_WIDTH,LANGUAGE_SCREEN_HEIGHT));
    currentStage.show();

    myLanguageScreen.getMyEnglishButton().setOnAction(event -> setupSimulationScenes(currentStage,ENGLISH_LANGUAGE));
    myLanguageScreen.getMySpanishButton().setOnAction(event -> setupSimulationScenes(currentStage,SPANISH_LANGUAGE));
    myLanguageScreen.getMyFrenchButton().setOnAction(event -> setupSimulationScenes(currentStage,FRENCH_LANGUAGE));
  }

  private void setupSimulationScenes(Stage stage, String language){
    this.myLanguageChoice=language;
    setupScene(FRAME_SIZE, FRAME_SIZE, currentSimulation, "GameOfLife");
    setupGraph();
    setUpStage(stage);
  }

  /*
   * Sets up the stage size and title
   */
  protected void setUpStage(Stage stage) {
      try {
        stage.setScene(myScene);
        stage.show();
        isPaused = true;

        secondStage.setScene(myGraphScene);
        secondStage.show();

      } catch (ControllerException e) {
        displayError(e.getMessage());
      }
  }

  /*
   * Create the game's "scene": what shapes will be in the game and their starting properties
   */
  Scene setupScene(int width, int height, Simulation currSim, String newSimType) {
    root = new Group();
    //currentSimView = new SimulationView();
   // try {
      currentSimulation = currSim;
    //currentSimulation = currSim;
      //SimulationView currSimView = currentSimulation.getSimulationView();
      currentSimView = new SimulationView(currentSimulation.getCurrentGrid(),myLanguageChoice);
      //SimulationView currSimView = new SimulationView(currentSimulation.getCurrentGrid());
      myScene = currentSimView.setupScene(newSimType, currentSimulation.getPossibleStateTypes(),
          SCREEN_WIDTH, SCREEN_HEIGHT);
    System.out.println("set up");
      //myScene = currSimView.setupScene("GameOfLife", GameOfLifeState.values(),
      //        SCREEN_WIDTH, SCREEN_HEIGHT);
      setUpButtons();
    return myScene;
  }

  private void setupGraph(){
    myGraphView = new GraphView(currentSimulation.getCurrentGrid(), myLanguageChoice);
    myGraphScene = myGraphView.setupScene("GameOfLife", currentSimulation.getPossibleStateTypes(),FRAME_SIZE,FRAME_SIZE);
  }

  private void setUpButtons() {
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
    }

  void step () {
    //System.out.println("stepping");
    //if (currentSimulation != null) {
    if(currentSimulation!=null && currentSimView != null){
      updateShapes(!isPaused);
      //checkChangeSimulation();
    }

  }

  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun, currentSimView);
    if(shouldRun){
      stepCount++;
      myGraphView.updateCurrentGrid(currentSimulation.getCurrentGrid(),stepCount);
    }

  }

  void saveFile() {
    try {
      System.out.println("saving");
      isPaused = true;
      //currentSimulation.storeNewCellConfig(currentSimulation.getCurrentGrid());
      currentSimulation.saveNewCellConfiguration(currentSimulation.getCurrentGrid());
      System.out.println("finished");
    } catch (ControllerException e) {
      displayError(e.getMessage());
    }
  }

  void checkChangeSimulation() {
    String simulationChosen = currentSimView.getMySimulationButtons().getSimulationChooser()
        .getMyChosenType();
    if (simulationChosen != null) {
      try {
        String fullClassName = String.format("controller." + simulationChosen + "Simulation");
        Class<?> cl = Class.forName(fullClassName);
        Constructor<?> cons = cl.getConstructor();
        cons.newInstance();
        Simulation test = (Simulation) cons.newInstance();
        currentSimulation = test;
        System.out.println(currentSimulation.getPropertiesInformation().get("kind"));
        setupScene(FRAME_SIZE, FRAME_SIZE, currentSimulation, simulationChosen);
        setupGraph();
        setUpStage(currentStage);
        //Constructor<?> cons = PercolationSimulation.class.getConstructor("");

      } catch (Exception e) {
        String invalidFileExceptionMessage = ResourceBundle.getBundle(ERRORS_LOCATION).
            getString("InvalidSimulationName");
        throw new ControllerException(invalidFileExceptionMessage);
      }
    }
  }

  void increaseSpeed() {
    if (secondDelay-speedShiftAmount > minSpeed) {
      secondDelay -= speedShiftAmount;
      System.out.println("increasing");
     // startAnimation(secondDelay);
      setUpStage(currentStage);
      isPaused = false;
    }
  }

  void decreaseSpeed() { //CHECK need min speed and max speed - read in values?
    if (secondDelay + speedShiftAmount < maxSpeed) {
      secondDelay += speedShiftAmount;
      System.out.println("decreasing");
      setUpStage(currentStage);
      System.out.println(secondDelay);
      //startAnimation(secondDelay);
      isPaused = false;
    }
  }

  void stepByButton() {
    updateShapes(isPaused);
  }

  void unpauseOrPause() {
    isPaused = !isPaused;
  }

  void selectNewFile() throws ControllerException {
    try {
      isPaused = true;
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Resource File");
      fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Simulation Files", "*.sim", "*.csv"));
      File selectedFile = fileChooser.showOpenDialog(currentStage);
      if (selectedFile != null) {
        currentSimulation.setSimulationFileLocation(selectedFile.getName());
        currentSimView.updateGridDisplay(currentSimulation.getCurrentGrid());
      }

    } catch(Exception e) {
      String noFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("NoFileSelectedError");
      displayError(noFileExceptionMessage);
      //throw new ControllerException(noFileExceptionMessage);
      //currentSimulation.getSimulationView().addExceptionMessage(noFileExceptionMessage);
    }
  }

  public void displayError(String message){
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setContentText(message);
    alert.showAndWait();
  }

  public static void main (String[] args) {
    launch(args);
  }

}
