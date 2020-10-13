package controller;

import java.io.File;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import model.Neighborhood;
import model.SimulationType;
import view.SimulationView;

public class ControllerMain extends Application {

  //public static final int FRAMES_PER_SECOND = 60;
  //public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static double secondDelay = 1.0;
  public final double SPEED_CHANGE_AMOUNT = .1;
  public static final int FRAME_SIZE = 400;
 // public static final Paint BACKGROUND = Color.AZURE;
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 400;
  private Scene myScene;
  private Group root;
  private Simulation currentSimulation;
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
    SimulationView currSimView = currentSimulation.getSimulationView();
    myScene = currSimView.setupScene(SimulationType.GAME_OF_LIFE, SCREEN_WIDTH, SCREEN_HEIGHT);
    currSimView.getMyControlButtons().getMyStep().setOnAction(event -> stepByButton());
    currSimView.getMyControlButtons().getMyPlayPause().setOnAction(event -> unpauseOrPause());
    currSimView.getMyFileButtons().getMySave().setOnAction(event -> saveFile());
    currSimView.getMyFileButtons().getMyNewFile().setOnAction(event ->
            selectNewFile());
    currSimView.getMyControlButtons().getSpeedUpButton().setOnAction(event-> increaseSpeed());
    currSimView.getMyControlButtons().getSlowDownButton().setOnAction(event-> decreaseSpeed());
    return myScene;
  }

  void step () {
    updateShapes(!isPaused);
    catchError();
  }

  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun);
  }

  void saveFile() {
    isPaused = true;
    currentSimulation.storeNewCellConfig(currentSimulation.getCurrentGrid());
  }

  void increaseSpeed() {
    secondDelay-= SPEED_CHANGE_AMOUNT;
    System.out.println("increasing");
    //start(currentStage);
  }

  void decreaseSpeed() { //CHECK need min speed and max speed - read in values?
    secondDelay += SPEED_CHANGE_AMOUNT;
    System.out.println("decreasing");
  }

  void catchError() {
    try {}
    catch(ControllerException e) {
      System.out.println("caught the error!");
    }
  }

  void stepByButton() {
    updateShapes(isPaused);
  }

  void unpauseOrPause() {
    isPaused = !isPaused;
  }

  void selectNewFile() {
    try {
      isPaused = true;
      JFileChooser j = new JFileChooser();
      j.showSaveDialog(null);
      File file = j.getSelectedFile();
      System.out.println(file.getName());
      //setUpStage(currentStage);
      currentSimulation.setSimulationFileLocation(file.getName());
      SimulationView currSimView = currentSimulation.getSimulationView();
      myScene = currSimView.setupScene(SimulationType.GAME_OF_LIFE, SCREEN_WIDTH, SCREEN_HEIGHT);
      currSimView.getMyControlButtons().getMyStep().setOnAction(event -> stepByButton());
      currSimView.getMyControlButtons().getMyPlayPause().setOnAction(event -> unpauseOrPause());
      currSimView.getMyFileButtons().getMySave().setOnAction(event ->saveFile());
      currSimView.getMyFileButtons().getMyNewFile().setOnAction(event ->
          selectNewFile());
      currentStage.setScene(myScene);
      currentStage.show();
    } catch(Exception e) {
      String noFileExceptionMessage = ResourceBundle.getBundle("resources/ControllerErrors").
          getString("NoFileSelectedError");
      throw new ControllerException(noFileExceptionMessage);
    }
  }

  public static void main (String[] args) {
    launch(args);
  }



}
