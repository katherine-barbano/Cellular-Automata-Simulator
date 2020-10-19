package controller;

import controller.stateType.GameOfLifeState;
import java.io.File;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
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
import javafx.stage.Window;
import javafx.util.Duration;
import view.GraphView;
import view.LanguageScreen.LanguageScreen;
import view.SimulationView;

public class ControllerMain extends Application {

  //public static final int FRAMES_PER_SECOND = 60;
  //public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static double secondDelay = 1.0;
  public final double SPEED_CHANGE_AMOUNT = .25;
  public static final int FRAME_SIZE = 400;
 // public static final Paint BACKGROUND = Color.AZURE;
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 400;
  public static final int LANGUAGE_SCREEN_HEIGHT = 80;
  public static final String ENGLISH_LANGUAGE="English";
  public static final String SPANISH_LANGUAGE="Spanish";
  public static final String FRENCH_LANGUAGE="French";
  private Scene myScene;
  private Group root;
  private LanguageScreen myLanguageScreen;
  private String myLanguageChoice;
  //private Simulation currentSimulation = new GameOfLifeSimulation();
  private Simulation currentSimulation;
  private SimulationView currentSimView = new SimulationView();
  private boolean isPaused;
  private Stage currentStage;
  private Stage secondStage= new Stage();
  private GraphView myGraphView;
  private Scene myGraphScene;
  private int stepCount;

  @Override
  public void start(Stage stage) {
    currentStage = stage;
      chooseLanguageAndSetupStage();
      KeyFrame frame = new KeyFrame(Duration.seconds(secondDelay), e -> step());
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
    setupScene(FRAME_SIZE, FRAME_SIZE);
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
  Scene setupScene(int width, int height) {
    root = new Group();
    currentSimView = new SimulationView();
   // try {
      currentSimulation = new GameOfLifeSimulation();
      //SimulationView currSimView = currentSimulation.getSimulationView();
      currentSimView = new SimulationView(currentSimulation.getCurrentGrid(),myLanguageChoice);
      //SimulationView currSimView = new SimulationView(currentSimulation.getCurrentGrid());
      myScene = currentSimView.setupScene("GameOfLife", currentSimulation.getPossibleStateTypes(),
          SCREEN_WIDTH, SCREEN_HEIGHT);
      //myScene = currSimView.setupScene("GameOfLife", GameOfLifeState.values(),
      //        SCREEN_WIDTH, SCREEN_HEIGHT);
      setUpButtons();

      //currSimView.getMySimulationButtons().
    //} catch (Exception e) {
    //  throw new ControllerException("set up scene method not working");
      //currentSimView.addExceptionMessage("nope");
    //  }
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
  }

  void step () {
    //System.out.println("stepping");
    if(currentSimulation!=null){
      updateShapes(!isPaused);
      checkChangeSimulation();
    }

  }

  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun, currentSimView);
    if(shouldRun){
      stepCount++;
      myGraphView.updateCurrentGrid(currentSimulation.getCurrentGrid(),stepCount);
    }

    //currentSimView.updateGridDisplay(currentSimulation.getCurrentGrid());
    //System.out.println("updating");
  }

  void saveFile() {
    System.out.println("saving");
    isPaused = true;
    //currentSimulation.storeNewCellConfig(currentSimulation.getCurrentGrid());
    currentSimulation.saveNewCellConfiguration(currentSimulation.getCurrentGrid());
    System.out.println("finished");
  }

  void checkChangeSimulation() {
    if (currentSimView.getMySimulationButtons().getSimulationChooser().getMyChosenType() != null) {
      if (currentSimView.getMySimulationButtons().getSimulationChooser().getMyChosenType()
          .equals("GameOfLife")) {
        currentSimulation = new GameOfLifeSimulation();
        setupScene(SCREEN_WIDTH, SCREEN_WIDTH);
        System.out.println("game now");
        currentStage.setScene(myScene);
        currentStage.show();
      }

      if (currentSimView.getMySimulationButtons().getSimulationChooser().getMyChosenType()
          .equals("Percolation")) {
        currentSimulation = new PercolationSimulation();
        setupScene(SCREEN_WIDTH, SCREEN_WIDTH);
        System.out.println("percolation now");
        currentStage.setScene(myScene);
        currentStage.show();
      }
    }
  }

  void increaseSpeed() {
    secondDelay-= SPEED_CHANGE_AMOUNT;
    System.out.println("increasing");
    setUpStage(currentStage);
    isPaused = false;
  }

  void decreaseSpeed() { //CHECK need min speed and max speed - read in values?
    secondDelay += SPEED_CHANGE_AMOUNT;
    System.out.println("decreasing");
    setUpStage(currentStage);
    isPaused = false;
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
     /* FileChooser f = new FileChooser();
      f.showSaveDialog(null);
      File file = f.getSelectedFile();*/
      //System.out.println(file.getName());
      //currentSimulation.setSimulationFileLocation(file.getName());
      //SimulationView currSimView = currentSimulation.getSimulationView();
/*      SimulationView currSimView = new SimulationView(currentSimulation.getCurrentGrid());
      myScene = currSimView.setupScene("GameOfLife", currentSimulation.getPossibleStateTypes(),
          SCREEN_WIDTH, SCREEN_HEIGHT);
      //myScene = currSimView.setupScene("GameOfLife", GameOfLifeState.values(),
      //    SCREEN_WIDTH, SCREEN_HEIGHT);
      currSimView.getMyControlButtons().getMyPlayPause().setOnAction(event -> unpauseOrPause());
      currSimView.getMyFileButtons().getMySave().setOnAction(event ->saveFile());
      currSimView.getMyFileButtons().getMyNewFile().setOnAction(event ->
          selectNewFile());
      currentStage.setScene(myScene);
      currentStage.show();*/
    } catch(Exception e) {
      String noFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("NoFileSelectedError");
      //throw new ControllerException(noFileExceptionMessage);
      //currentSimulation.getSimulationView().addExceptionMessage(noFileExceptionMessage);
    }
  }

  public void displayError(String message){
    //Text ExceptionText = new Text(message);
    //myRoot.getChildren().add(ExceptionText);
    Alert alert = new Alert(AlertType.ERROR);
    //alert.setTitle(myResources.getString("ErrorTitle"));
    alert.setContentText(message);
    alert.showAndWait();

  }

  public static void main (String[] args) {
    launch(args);
  }

}
