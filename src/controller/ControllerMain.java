package controller;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import javax.swing.JFileChooser;
import model.SimulationType;
import view.SimulationView;

public class ControllerMain extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY_LONG = 1.0;
  public static final int FRAME_SIZE = 400;
 // public static final Paint BACKGROUND = Color.AZURE;
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 400;
  private Scene myScene;
  private Group root;
  private Simulation currentSimulation;
  private boolean isPaused;

  @Override
  public void start(Stage stage) {
    setUpStage(stage);
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY_LONG), e -> step());
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
    currSimView.getMyFileButtons().getMySave().setOnAction(event ->
        currentSimulation.storeNewCellConfig(isPaused, currentSimulation.getCurrentGrid()));
    //currSimView.getMyControlButtons().getMySave().setOnAction(event ->
    //        selectNewFile());
    //currentSimulation.readCellStatesFile();
    //currSimView.getMyControlButtons().getChooseFile().setOnAction(event -> selectNewFile());
    return myScene;
  }


  void step () {
    updateShapes(!isPaused);
  }

  private void updateShapes(boolean shouldRun) {
    currentSimulation.updateSimulationGrid(shouldRun);
  }

  void stepByButton() {
    updateShapes(isPaused);
  }

  void unpauseOrPause() {
    isPaused = !isPaused;
  }

  void selectNewFile() {
    JFileChooser j = new JFileChooser();
    j.showSaveDialog(null);
    File file = j.getSelectedFile();
    System.out.println(file.getName());
    currentSimulation.setSimulationFileLocation(file.getName());
    currentSimulation.getSimulationView().setupScene(SimulationType.GAME_OF_LIFE, SCREEN_WIDTH, SCREEN_HEIGHT);
    //System.out.println(currentSimulation.getSimulationView());
  }

  public static void main (String[] args) {
    launch(args);
  }



}