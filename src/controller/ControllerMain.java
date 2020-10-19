package controller;

import controller.stateType.GameOfLifeState;
import java.io.File;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JFileChooser;
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
  private Scene myScene;
  private Group root;
  //private Simulation currentSimulation = new GameOfLifeSimulation();
  private Simulation currentSimulation;
  private SimulationView currentSimView;
  private boolean isPaused;
  private Stage currentStage;

  @Override
  public void start(Stage stage) {
    currentStage = stage;
    setUpStage(stage);
    KeyFrame frame = new KeyFrame(Duration.seconds(secondDelay), e -> step());
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /*
   * Sets up the stage size and title
   */
  protected void setUpStage(Stage stage) {
    setupScene(FRAME_SIZE, FRAME_SIZE);
    stage.setScene(myScene);
    stage.show();
    isPaused = true;
  }

  /*
   * Create the game's "scene": what shapes will be in the game and their starting properties
   */
  Scene setupScene(int width, int height) {
    root = new Group();
    currentSimulation = new GameOfLifeSimulation();
    //SimulationView currSimView = currentSimulation.getSimulationView();
    currentSimView = new SimulationView(currentSimulation.getCurrentGrid());
    //SimulationView currSimView = new SimulationView(currentSimulation.getCurrentGrid());
    myScene = currentSimView.setupScene("GameOfLife", currentSimulation.getPossibleStateTypes(),
        SCREEN_WIDTH, SCREEN_HEIGHT);
    //myScene = currSimView.setupScene("GameOfLife", GameOfLifeState.values(),
    //        SCREEN_WIDTH, SCREEN_HEIGHT);
    currentSimView.getMyControlButtons().getMyStep().setOnAction(event -> stepByButton());
    currentSimView.getMyControlButtons().getMyPlayPause().setOnAction(event -> unpauseOrPause());
    currentSimView.getMyFileButtons().getMySave().setOnAction(event -> saveFile());
    currentSimView.getMyFileButtons().getMyNewFile().setOnAction(event ->
            selectNewFile());
    currentSimView.getMyControlButtons().getSpeedUpButton().setOnAction(event-> increaseSpeed());
    currentSimView.getMyControlButtons().getSlowDownButton().setOnAction(event-> decreaseSpeed());
    //currSimView.getMySimulationButtons().
    return myScene;

  }

  void step () {
    //System.out.println("stepping");
    updateShapes(!isPaused);
    checkChangeSimulation();
  }

  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun, currentSimView);
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

  public static void main (String[] args) {
    launch(args);
  }

}
